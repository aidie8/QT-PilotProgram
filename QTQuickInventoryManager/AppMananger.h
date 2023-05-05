#pragma once
#include "Inventory.h"
#include <QQmlApplicationEngine>
#include "NewOrderScreen.h"

class AppManager : QObject
{
	Q_OBJECT;


public:
	AppManager(QQmlApplicationEngine* engine);
	~AppManager();
	Inventory inv;
	QQmlApplicationEngine* getEngine();

private:
	QQmlApplicationEngine* engine;
	NewOrderScreen* newOrderScreen;

	void loadMainScreen();
public slots:
	Q_SLOT void onLogin(QString username, QString pass);
};

