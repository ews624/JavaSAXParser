/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author enoch
 */
public class FXMLDocumentController implements Initializable {
    
    
    Stage stage;
    @FXML
    private Button button;
    @FXML
    public TextField textArea;
    public void ready(Stage stage){
        this.stage = stage;
    }
    private XMLNode root;
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files (*.xml)", "*.xml"));
        File file = fileChooser.showOpenDialog(stage);
         if (file != null) {
        try
        {
            textArea.setText("");
            root = Parser.parse(file);
          
            
           
            
                
        } catch (Exception ex) {
           Alert alert = new Alert(AlertType.ERROR, "Error encountered while parsing: " + ex + " ?", ButtonType.OK);
alert.showAndWait();
        }
        
        
        
}
        XMLNode root = Parser.parse(file);
        
     
        textArea.setText(Parser.returnInput());
        
        
    }
    
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
