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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author ben
 */
public class FXMLDocumentController implements Initializable {
    
        private Label label;
    @FXML
    private MenuBar editm;
    @FXML
    private AnchorPane elements;
    @FXML
    private Label label1;
    @FXML
    private Font x1;
    @FXML
    private Color x2;
    @FXML
    private AnchorPane a;
    @FXML
    private Font x3;
    @FXML
    private Color x4;
    
        private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleNew(ActionEvent event) {
    }

    @FXML
    private void handleOpen(ActionEvent event) {
    }

    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void handleSave(ActionEvent event) {
    }

    @FXML
    private void handleSaveAs(ActionEvent event) {
    }

    @FXML
    private void handleRevert(ActionEvent event) {
    }

    @FXML
    private void handlePreferences(ActionEvent event) {
    }

    @FXML
    private void handleQuit(ActionEvent event) {
    }

    @FXML
    private void handleUndo(ActionEvent event) {
    }

    @FXML
    private void handleRedo(ActionEvent event) {
    }

    @FXML
    private void handleCut(ActionEvent event) {
    }

    @FXML
    private void handleCopy(ActionEvent event) {
    }

    @FXML
    private void handlePast(ActionEvent event) {
    }

    @FXML
    private void handleDelete(ActionEvent event) {
    }

    @FXML
    private void handleSelectAll(ActionEvent event) {
    }

    @FXML
    private void handleUnselectAll(ActionEvent event) {
    }

    @FXML
    private void handleAcout(ActionEvent event) {
    }
    
}
