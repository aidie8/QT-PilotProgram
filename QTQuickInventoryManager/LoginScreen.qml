import QtQuick 2.6

Rectangle {
    width :800  
    height:800
    color: "white"

     Text {
        text: "Username"
        color: "red"
        font.pointSize: 30

        Rectangle {
            color: "gray"
            width: 200
            height: 60
            anchors.left: parent.right
            anchors.margins: 50
            clip: true
            TextInput 
            {
                wrapMode: TextInput.Wrap
                leftPadding : 20
                anchors.left: parent.center
                font.pointSize: 30  
                color: "black"
                cursorVisible :true
                autoScroll: false
                width: 200
                height: 50
            }
        }
    }
    
    
    
}
