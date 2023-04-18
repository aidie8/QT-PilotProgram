#include "AppMananger.h"



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
	

}