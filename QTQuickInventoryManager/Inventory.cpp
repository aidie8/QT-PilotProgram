#include "Inventory.h"
#include <string>
#include <QSqlError>
#include <QSQLRecord>
void Inventory()
{

}

void Inventory::connect(const QString username, const QString password)
{

	db = QSqlDatabase::addDatabase("QODBC");
	db.setDatabaseName("Driver={MySQL ODBC 8.0 Unicode Driver};Server=localhost;Database=mydb;"); // "WorkDatabase" is the name of the database we want
	db.setUserName(username);
	db.setPassword(password);

	if (!db.open())
	{
		qDebug() << db.lastError();
	}else
	{
	PopulateInventory();
	}
	
}





void Inventory::PopulateInventory()
{
	QSqlQuery query;	
	query.prepare("SELECT `item name`, items.SKU, `description`,price,`Count` FROM Items,Inventory where inventory.Sku = items.SKU");
	if(!query.exec())
	{
	qDebug() << "Can't Execute Query !";
	}
	else
	{
		qDebug() << "Query Executed Successfully !";
		qDebug() << query.isSelect();
			
		
		int index = 0;
		while(query.next())
		{
				int descriptionIndex = query.record().indexOf("description");
				int skuIndex = query.record().indexOf("SKU");
				int nameIndex = query.record().indexOf("item name");
				int priceIndex = query.record().indexOf("price");
				int countIndex = query.record().indexOf("Count");
				qDebug() << "Query Executed Successfully !";
				InventoryItem* item = new InventoryItem();
				item->description = query.value(descriptionIndex).toString().toStdString();
				item->SKU = query.value(skuIndex).toInt();
				item->name = query.value(nameIndex).toString().toStdString();
				item->price = query.value(priceIndex).toInt();
				item->stock = query.value(countIndex).toInt();
				items[item->SKU] = item;
		} 
	}
}
	
QSqlQuery* Inventory::sendQueryToDB(QSqlQuery &query)
{
	if(!query.exec())
	{
	qDebug() << "Can't Execute Query !";
	return NULL;
	}
	else
	{
		qDebug() << "Query Executed Successfully !";
		return &query;
	}

}


void Inventory::closeConnection(){}

bool Inventory::addStockToInventory(int SKU, int count)
{
	InventoryItem* item = items[SKU];
	if (item != NULL){
	QSqlQuery query;
	query.prepare("UPDATE `mydb`.`inventory` SET `Count` = ':count' WHERE (`Sku` = ':sku');");
	query.bindValue(":count",item->stock + count);
	query.bindValue(":sku",SKU);
	sendQueryToDB(query);
	if (query.numRowsAffected() > 0){
		item->stock = item->stock - count;
		return true;
	}
	else 
	{
		qDebug() << query.lastError();
	}
	}
	return false;
}

bool Inventory::removeStockFromInventory(int SKU, int count)
{

	InventoryItem* item = items[SKU];
	if (item->stock < count)return false;
	QSqlQuery query;
	query.prepare("UPDATE `mydb`.`inventory` SET `Count` = ':count' WHERE (`Sku` = ':sku');");
	query.bindValue(":count",item->stock - count);
	query.bindValue(":sku",SKU);
	sendQueryToDB(query);
	if (query.numRowsAffected() > 0){
		item->stock = item->stock - count;
		return true;
	}
	else {qDebug() << query.lastError();}
	return false;
}

bool Inventory::removeItemFromInventory(int SKU)
{
	db.transaction();
	QSqlQuery queryRemoveFromItems;
	queryRemoveFromItems.prepare("DELETE FROM `mydb`.`Items` WHERE (`SKU` = ':SKU')");
	queryRemoveFromItems.bindValue(":SKU",SKU);
	QSqlQuery queryRemoveFromInventory;
	queryRemoveFromItems.prepare("DELETE FROM `mydb`.`Inventory` WHERE (`SKU` = ':SKU')");
	queryRemoveFromItems.bindValue(":SKU",SKU);
	if (sendQueryToDB(queryRemoveFromItems)->numRowsAffected() > 0){
	
		if(sendQueryToDB(queryRemoveFromInventory)->numRowsAffected() > 0){
			db.commit();
			return true;
		}
	}
	db.rollback();
	return false;
}

QMap<int, InventoryItem*> Inventory::getStock()
{
	return items;
}

bool Inventory::addNewItemToInventory(std::string name, int SKU, std::string description, int price)
{
	db.transaction();
	InventoryItem newItem = InventoryItem(name,SKU,description,price);
	QSqlQuery QueryInserIntoInventory;
	QueryInserIntoInventory.prepare("INSERT INTO `mydb`.`inventory` (`SKU`, `:Count`) VALUES (':skuvalue', ':countLabel');");
	QueryInserIntoInventory.bindValue(":skuvalue", SKU);
	QueryInserIntoInventory.bindValue(":countLabel", 0);
	QSqlQuery queryInsertIntoItems;
	QueryInserIntoInventory.prepare("INSERT INTO `mydb`.`items` (`ItemName`,`SKU`, `Description`,price) VALUES (':itemName', ':Sku',`:description`,`:price`);");
	QueryInserIntoInventory.bindValue(":itemName", QString::fromStdString(name));
	QueryInserIntoInventory.bindValue(":Sku", SKU);
	QueryInserIntoInventory.bindValue(":description", QString::fromStdString(description));
	QueryInserIntoInventory.bindValue(":price", price);
	if (sendQueryToDB(QueryInserIntoInventory)->numRowsAffected() > 0){
	
		if(sendQueryToDB(queryInsertIntoItems)->numRowsAffected() > 0)
		{
			db.commit();
			return true;
		}
	}
	db.rollback();
	return false;


}