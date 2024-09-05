/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattingclients;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author anas
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField txtOut;
    @FXML
    private Button btnSend;
    @FXML
    private TextArea txtIn;
    Socket invitation;
    DataInputStream ear;
    PrintStream mouth;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            invitation =new Socket("127.0.0.1",5005);
            ear=new DataInputStream(invitation.getInputStream());
            mouth =new PrintStream(invitation.getOutputStream());
            new Thread()
            {
                @Override
                public void run() {
                    
                    while(true)
                    {
                    try {
                        String msg =ear.readLine();
                        txtIn.appendText(msg+"\n");
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                }
                
            }.start();
                    
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void SendAction(ActionEvent event) {
        String message =txtOut.getText();
        mouth.println(message);
        txtOut.clear();
        
    }
    
}
