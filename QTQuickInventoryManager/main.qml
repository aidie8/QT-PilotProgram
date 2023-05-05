import QtQuick 2.9
import QtQuick.Window 2.2

Window {

    signal login(username: string,password:string) 
    visible: true
    width: 1024 
    height: 680
    title: qsTr("Inventory Management")
    

    Loader
    {
    objectName: "screenLoader"
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
            login(username,pass)
        }
        ignoreUnknownSignals : true
    }
    
   
}
