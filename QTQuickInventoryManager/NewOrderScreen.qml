import QtQuick 2.3
import QtQuick.Layouts
import QtQuick.Controls
StackView  {
    id:newOrder
    Rectangle {
        id: newOrderPage
        anchors.fill : parent


        Connections
        {
            target :newOrderScreen
            function onLoaded(dataList){ console.log("test")}
        }

        Rectangle{
            id: inventoryListView
            color: "gray"
            anchors.left: parent.left
            anchors.top : parent.top
            anchors.bottom: parent.bottom
            anchors.right : parent.horizontalCenter
            anchors.topMargin: 100
            anchors.leftMargin: 100
            anchors.bottomMargin: 150
            anchors.rightMargin: 100
            implicitWidth: 400
           
        }
        Rectangle{
            id: orderListView
            color: "gray"
            anchors.right: parent.right
            anchors.top : parent.top
            anchors.bottom: parent.bottom
            anchors.left :parent.horizontalCenter
            anchors.topMargin: 100
            anchors.rightMargin: 100
            anchors.bottomMargin: 150
            anchors.leftMargin : 100
            implicitWidth: 400

            Text {
      
                text: "new order"
                anchors.centerIn: parent
            }
        }
        Button
        {

        text:"create Order"
        anchors.horizontalCenter: orderListView.right
        anchors.top: orderListView.bottom
        anchors.bottom: parent.bottom
        anchors.bottomMargin: 50
        anchors.right: parent.right
        anchors.rightMargin: 50
        anchors.topMargin :20
        }
    }
}
