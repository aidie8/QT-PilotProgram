#include "Inventory.h"
#include <string>
#include <QSqlError>
void Inventory()
{

}

void Inventory::connect(const QString username, const QString password)
{

	db = QSqlDatabase::addDatabase("QODBC");
	db.setDatabaseName("Driver={MySQL ODBC 8.0 Unicode Driver};Server=localhost;Database=mydb;"); // "WorkDatabase" is the name of the database we want
	db.setUserName(username);
	db.setPassword(password);
		

	qDebug() << "Test";
	qDebug() << db.password();

	if (!db.open())
	{
		qDebug() << db.lastError();
	}else{
		QSqlQuery query;
		query.prepare("SELECT * FROM INVENTORY");
		if(!query.exec())
		{
		qDebug() << "Can't Execute Query !";
		}
		else
		{
			qDebug() << "Query Executed Successfully !";
			while(query.next())
			{
				qDebug() << "Sku: " << query.value(0).toString();
			} 
		
		}
	}
}


