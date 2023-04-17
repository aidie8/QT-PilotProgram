import QtQuick 2.6
import QtQuick.Controls 2.0
Rectangle {

    property alias inputLabelText: inputLabel.text
    property alias inputplaceholderText: textInputBox.placeholderText

    implicitHeight: 60  // < This
    implicitWidth: 300
    Text {
        id: inputLabel
        text: "Username: "
        color: "black"
        font.pointSize: 30
        width:200
        

        TextField 
        {
            id:textInputBox
         
            width : 200
            height : parent.height
            anchors.left: parent.right          
            font.pointSize: text.length > 0 ? 30: 15
            horizontalAlignment: Text.AlignHRight
            verticalAlignment: Text.AlignVCenter
            color: text.length > 0 ? "black" : "gray"
            cursorVisible :true
            autoScroll: true
        }
    }
    
}