import QtQuick 2.15
import QtQuick.Window 2.15
import QtQuick.Controls 2.0

Window {
    width: 640
    height: 480
    visible: true
    title: qsTr("Hello World")

    // Header Text
       Text {
           id: headerText
           text: "Welcome to My App"
           font.pixelSize: 40 // Set the font size for the header
           font.bold: true // Make the font bold
           anchors.horizontalCenter: parent.horizontalCenter
           anchors.top: parent.top
           anchors.topMargin: 20 // Margin from the top
       }
    Button {
        width: 60
        height: 20
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.verticalCenter: parent.verticalCenter
        anchors.verticalCenterOffset: -50
        text: "Login"

        // Handle button click
        onClicked: {
            if (inputField.text === "anas") {
                label.visible = true
                myImg.visible=true
            } else {
                label.visible = false
                myImg.visible=false
                err.visible=true
            }
        }
    }

    Image {
        id:myImg
        source: "file:/home/anas/AndroidAutomotive/Qt/Session_2/Lab/iti-logo.png"
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.verticalCenter: parent.verticalCenter
        anchors.verticalCenterOffset: 10
        width: 200 // Set the desired width
        height: 200 // Set the desired height
        fillMode: Image.PreserveAspectFit // Ensure the image maintains its aspect ratio
        visible: false // Hide the label by default

    }

    TextField {
        id: inputField
        width: 300
        height: 40
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.verticalCenter: parent.verticalCenter
        anchors.verticalCenterOffset: -100
        placeholderText: "Enter your text here"
        font.pixelSize: 20
    }

    Label {
        id: label
        text: "Hello, From Anas"
        color: "green"
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.verticalCenter: parent.verticalCenter
        anchors.verticalCenterOffset: 100
        font.bold: true
        visible: false // Hide the label by default
    }

    Label {
        id: err
        text: "Error User !!"
        color: "red"
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.verticalCenter: parent.verticalCenter
        anchors.verticalCenterOffset: 10
        font.bold: true
        visible: false // Hide the label by default
    }

}
