#include "NewOrderScreen.h"
#include "AppMananger.h"

NewOrderScreen::NewOrderScreen(AppManager* appManager)
{
	manager = appManager;
	QQmlContext* context = manager->getEngine()->rootContext();
	context->setContextProperty("newOrderScreen",this);
}

void NewOrderScreen::Load()
{
	QStringList dataList;
	QMap<int, InventoryItem*> stock =  manager->inv.getStock();
	for (int SKU : stock.keys())
	{
		dataList << QString::fromStdString(stock[SKU]->name);
	}
	manager->getEngine()->setInitialProperties({{ "model", QVariant::fromValue(dataList) }});
	
	emit loaded();

}
void NewOrderScreen::ConfirmOrder(){}
