/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactlist;
import DataTransferObject.Contact;
import db.DataAccessObject;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author anas
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button newButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button firstButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextbutton;
    @FXML
    private Button lastButton;
    @FXML
    private TextField IDtxtIn;
    @FXML
    private TextField FNametxtIN;
    @FXML
    private TextField LNametxtIn;
    @FXML
    private TextField EmailtxtIn;
    @FXML
    private TextField phonetxtIn;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void newBtnAction(ActionEvent event) {
        
        try {
            Contact contact =new Contact(Integer.parseInt(IDtxtIn.getText()),FNametxtIN.getText(),LNametxtIn.getText(),phonetxtIn.getText(),EmailtxtIn.getText());
            DataAccessObject.newContact(contact);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateBtnAction(ActionEvent event) {
    }

    @FXML
    private void deleteBtnAction(ActionEvent event) {
        
        
        
    }

    @FXML
    private void firstBtnAction(ActionEvent event) {
    }

    @FXML
    private void previousBtnAction(ActionEvent event) {
    }

    @FXML
    private void nextBtnAction(ActionEvent event) {
    }

    @FXML
    private void lastBtnAction(ActionEvent event) {
    }

    @FXML
    private void IDtxtInAction(ActionEvent event) {
    }

    @FXML
    private void FNametxtINAction(ActionEvent event) {
    }

    @FXML
    private void LNametxtInAction(ActionEvent event) {
    }

    @FXML
    private void EmailtxtInAction(ActionEvent event) {
    }

    @FXML
    private void phonetxtInAction(ActionEvent event) {
    }
    
}
