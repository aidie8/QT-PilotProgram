#pragma once
#include <string>
#include <QDebug>
#include <QSqlQuery>
class Inventory
{
public:
	QSqlDatabase db;
	Inventory()
	{

		
	}
	void connect(QString username, QString password);
	~Inventory(){}
private:
	
	
	void closeConnection(){}
};

