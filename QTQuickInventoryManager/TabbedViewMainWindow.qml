import QtQuick 2.3
import QtQuick.Controls 2.6
import QtQuick.Layouts

Rectangle{
    id: tabbedView
    anchors.fill: parent

    TabBar
    {

        onCurrentIndexChanged: 
        {
        
        if(currentIndex == 0)
        {
        console.log("Summary Loaded")
        }
        if(currentIndex == 1)
        {
        console.log("Statistics Loaded")
        }
        if (currentIndex == 2) {
            newOrderScreen.Load()
            console.log("New Order Screen Loaded")
        }
        if(currentIndex == 3)
        {
        console.log("Inventory Screen Loaded")
        }

        }
        width: parent.width
        id:bar
        TabButton
        {
            text: "Summary"
            font.pointSize: 25
        }
        TabButton
        {
            text: "Statistics"
            font.pointSize: 25
        }
        TabButton
        {
            text: "New Order"
            
            font.pointSize: 25
        }
        TabButton
        {
            text: "Inventory"
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