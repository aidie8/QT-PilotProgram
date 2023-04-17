#pragma once
#include "Inventory.h"
#include <QQmlApplicationEngine>
class AppManager : QObject
{
	Q_OBJECT;


public:
	AppManager(QQmlApplicationEngine* engine)
	{
	QObject *rootObject = engine->findChild<QObject*>("rect");
	qDebug() << &rootObject;
	QObject::connect(rootObject, SIGNAL(login(QString,QString)),
                     this, SLOT(onLogin(QString,QString)));
	inv = Inventory();
	
	}
	~AppManager(){}
	Inventory inv;

public slots:
	Q_SLOT void onLogin(QString username, QString pass);
};

