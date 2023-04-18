#pragma once
#include "Inventory.h"
#include <QQmlApplicationEngine>
class AppManager : QObject
{
	Q_OBJECT;


public:
	AppManager(QQmlApplicationEngine* engine)
	{
	QObject *rootObject = engine->rootObjects().first();
	qDebug() << rootObject;
	qDebug() << "TEST";
	qDebug() << "TEST";
	qDebug() << "TEST";
	qDebug() << "TEST";
	QObject::connect(rootObject, SIGNAL(login(QString,QString)),
                     this, SLOT(onLogin(QString,QString)));
	inv = Inventory();
	this->engine = engine;
	}
	~AppManager(){}
	Inventory inv;


private:
	QQmlApplicationEngine* engine;
public slots:
	Q_SLOT void onLogin(QString username, QString pass);
};

