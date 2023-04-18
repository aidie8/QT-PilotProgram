#include "AppMananger.h"



void AppManager::onLogin(QString username, QString pass)
{
	qDebug() << "TESTTEOUJKSHDKJIHFJKIHHIUSADHIUHSADUI";
	qDebug() << pass;
	qDebug() << username;
	QObject *screenLoader = engine->rootObjects().first()->findChild<QObject*>("screenLoader");
	qDebug() << screenLoader;
	if (screenLoader)
	{
		qDebug() << screenLoader->objectName();
	screenLoader->setProperty("source", "TabbedViewMainWindow.qml");
	}
  
	inv.connect(username,pass);
	

}