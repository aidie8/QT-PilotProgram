#include "AppMananger.h"

AppManager::AppManager(QQmlApplicationEngine* engine)
{
	QObject *rootObject = engine->rootObjects().first();
	QObject::connect(rootObject, SIGNAL(login(QString,QString)),
						this, SLOT(onLogin(QString,QString)));
	inv = Inventory();
	this->engine = engine;
	newOrderScreen = new NewOrderScreen(this);
}




void AppManager::onLogin(QString username, QString pass)
{
	QObject *screenLoader = engine->rootObjects().first()->findChild<QObject*>("screenLoader");
	qDebug() << screenLoader;
	if (screenLoader)
	{
		qDebug() << screenLoader->objectName();
		screenLoader->setProperty("source", "TabbedViewMainWindow.qml");
	}
  
	inv.connect(username,pass);
	loadMainScreen();	

}


void AppManager::loadMainScreen()
{
}

QQmlApplicationEngine* AppManager::getEngine()
{
	return engine;
}
AppManager::~AppManager()
{
}