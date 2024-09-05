/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filetexteditor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 *
 * @author anas
 */
public class FXMLDocumentController implements Initializable {
    
    private Label Headerlabel;
    
    @FXML
    private Menu FileMenu;
    @FXML
    private MenuItem NewMenuItem;
    @FXML
    private MenuItem openMenuItem;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private Menu EditMenu;
    @FXML
    private MenuItem copyMenuItem;
    @FXML
    private MenuItem cutMenuItem;
    @FXML
    private MenuItem pasteMenuItem;
    @FXML
    private MenuItem selectAllMenuItem;
    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private TextArea textArea;
    
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        Headerlabel.setText("Anas Tesxt Editor");
    }    

    @FXML
    private void createNewFile(ActionEvent event) {
            textArea.clear();

    }

    @FXML
    private void openFile(ActionEvent event) {
    
        // Create a new FileChooser instance to allow the user to select files from the file system
        FileChooser fileChooser = new FileChooser();

        // Show the open file dialog and store the selected file
        // 'null' centers the dialog on the screen or on the primary stage if specified
        File selectedFile = fileChooser.showOpenDialog(null);

        // Check if a file was selected (i.e., the user did not cancel the dialog)
        if (selectedFile != null) {
            // Create a BufferedReader to read text from the selected file
            // FileReader is used to read the file as a stream of characters
            // BufferedReader provides efficient reading of text data
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                // Use StringBuilder to efficiently build the entire file content as a single string
                StringBuilder content = new StringBuilder();
                String line;

                // Read the file line by line until the end of the file is reached
                // 'readLine()' returns null when the end of the file is reached
                while ((line = br.readLine()) != null) {
                    // Append each line read from the file to the StringBuilder
                    // Add a newline character after each line to preserve the file's format
                    content.append(line).append("\n");
                }

                // Set the text of the TextArea to the content read from the file
                // 'toString()' converts the StringBuilder content to a String
                textArea.setText(content.toString());

            // Catch any IOException that occurs during file reading
            // This handles cases like file not found or read/write permission issues
            } catch (IOException e) {
                // Print the stack trace of the exception to help with debugging
                // This shows where the error occurred and its cause
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void saveFile(ActionEvent event) {
        
       FileChooser fileChooser = new FileChooser();
       File file = fileChooser.showSaveDialog(null);

       if (file != null) {
           try (FileWriter fileWriter = new FileWriter(file)) {
               fileWriter.write(textArea.getText());
           } catch (IOException e) {
               e.printStackTrace();
           }
    }
        
    }

    @FXML
    private void exitFile(ActionEvent event) {
        
            System.exit(0);
    }

    @FXML
    private void copyText(ActionEvent event) {
            textArea.copy();
    }

    @FXML
    private void cutText(ActionEvent event) {
            textArea.cut();
    }

    @FXML
    private void pasteText(ActionEvent event) {
            textArea.paste();
    }

    @FXML
    private void selectAllText(ActionEvent event) {
            textArea.selectAll();
    }

    @FXML
    private void deteleText(ActionEvent event) {
            textArea.replaceSelection(""); 
    }

    @FXML
    private void aboutDialog(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Simple Text Editor By Anas Khamees");
        alert.setContentText("This is a simple text editor built using JavaFX.");
        alert.showAndWait();
    }
    
}
