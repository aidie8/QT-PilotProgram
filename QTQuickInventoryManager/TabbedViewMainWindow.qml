import QtQuick 2.3
import QtQuick.Controls 2.0
import QtQuick.Layouts

Rectangle{
    anchors.fill: parent
    TabBar
    {
        id:bar
        TabButton
        {
            text: "Summary"
            width: 400
            font.pointSize: 25
        }
        TabButton
        {
            text: "Statistics"
            width: 400
            font.pointSize: 25
        }
        TabButton
        {
            text: "New Order"
            width: 400
            font.pointSize: 25
        }
        TabButton
        {
            text: "Inventory"
            width: 400
            font.pointSize: 25
        }

   
    }
    StackLayout{
        currentIndex :bar.currentIndex
        width: parent.width
        height: parent.height - bar.height
        anchors
        {
            top: bar.bottom
        }
        SummaryScreen
        {
       
        }
        StatisticsScreen
        {

        }
        NewOrderScreen
        {

        }
        InventoryOverview
        {
        }
    }
}