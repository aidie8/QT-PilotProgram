#pragma once
#include <string>
#include <QDebug>
#include <QSqlQuery>
#include "InventoryItem.h"
class Inventory
{
public:
	
	Inventory(){}
	~Inventory(){}
	QSqlDatabase db;
	void connect(QString username, QString password);
	bool addNewItemToInventory(std::string name,int SKU,std::string,int price);
	bool addStockToInventory(int SKU,int count);
	bool removeStockFromInventory(int SKU,int count);
	bool removeItemFromInventory(int SKU);
	QMap<int, InventoryItem*> getStock();
	
private:
	QMap<int, InventoryItem*> items;
	void PopulateInventory();
	QSqlQuery* sendQueryToDB(QSqlQuery &query);
	void closeConnection();
};

