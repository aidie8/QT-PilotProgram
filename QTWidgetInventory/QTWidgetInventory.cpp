#include "QTWidgetInventory.h"

QTWidgetInventory::QTWidgetInventory(QWidget *parent)
    : QMainWindow(parent)
{  
    LoginPage.setupUi(this);
   
    this->setCentralWidget(LoginPage.centralWidget);

}

QTWidgetInventory::~QTWidgetInventory()
{}



void QTWidgetInventory::OnLogin()
{
   inventory = Inventory();
   inventory.connect(LoginPage.Username->text(),LoginPage.Password->text());
   
   if(!inventory.db.isOpen()){
   LoginPage.SuccessLabel->setText(QString("Failed"));
   }else
   {
     

   }
}
