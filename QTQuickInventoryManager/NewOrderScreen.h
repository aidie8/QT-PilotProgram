#pragma once

#include <QQmlContext>
class AppManager;
class NewOrderScreen : QObject
{
	Q_OBJECT
	



public:
	NewOrderScreen(AppManager* appManager);
	
	~NewOrderScreen(){}

	Q_INVOKABLE 
	void Load();
	void ConfirmOrder();

private:
	AppManager* manager;

signals:
	void loaded();
};

