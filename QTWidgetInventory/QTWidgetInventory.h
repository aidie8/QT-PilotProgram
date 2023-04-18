#pragma once

#include <QtWidgets/QMainWindow>

#include "ui_QTWidgetInventory.h"
#include "ui_MainWindow.h"
#include "Inventory.h"
#include "ui_QtWidgetsClass.h"
#include "ui_StatisticScreenClass.h"
class QTWidgetInventory : public QMainWindow
{
    Q_OBJECT

public:
    QTWidgetInventory(QWidget *parent = nullptr);
    ~QTWidgetInventory();
    Inventory inventory;

private:
    Ui::QTWidgetInventoryClass LoginPage;


public slots:
    void OnLogin();

};
