import QtQuick 2.6
import QtQuick.Controls 2.0
Rectangle {
    signal login(username: string,password:string)
    FancyTextInput
    {
        y:200
        id: usernameInput
        inputLabelText : "Username"
        anchors.horizontalCenter:parent.horizontalCenter
        inputplaceholderText: "Username"
   
    }
    FancyTextInput
    {
        id: passwordInput
        inputLabelText : "Password"
        anchors.top: usernameInput.bottom
        anchors.topMargin:20
        anchors.horizontalCenter:parent.horizontalCenter
        inputplaceholderText: "Password"
    }



    Button 
    {
        id:loginButton
        text: "Login"
        font.pointSize:20
        anchors.topMargin: 60
        anchors.horizontalCenter: passwordInput.horizontalCenter
        anchors.top: passwordInput.bottom
        onClicked: 
        function onClick() 
        {
            console.log("Pass " + passwordInput.inputText)
            login(usernameInput.inputText,passwordInput.inputText)
        }
    }
    
    
    
}
