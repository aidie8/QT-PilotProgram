import QtQuick 2.9
import QtQuick.Window 2.2

Window {
    visible: true
    width: 1024 
    height: 680
    title: qsTr("Inventory Management")
    

    Loader
    {
    id: pageLoader
    source: "LoginScreen.qml"
    width:parent.width
    height:parent.height
    
    }

    Connections 
    {
        objectName: "rect"
        target: pageLoader.item  
        function onLogin(username,pass) 
        {
            console.log("onLoad")
        }
    }
    
   
}
