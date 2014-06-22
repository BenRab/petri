/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinet;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ben
 */
public class HelpController implements Initializable {
    @FXML
    private Button HelpCloseButton;
    @FXML
    private Button HelpCloseButton2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleCloseHelp(ActionEvent event) {
        Stage stage = (Stage) HelpCloseButton.getScene().getWindow();
        stage.close();
    }
    
}
