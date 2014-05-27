/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        a.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                for (Node n : a.getChildren()) {
                    if (n instanceof PetriNetPane) {
                        PetriNetPane p = (PetriNetPane) n;
                        if (p.isPointInElement(t.getX(), t.getY()));
                        p.setSelected(false);
                    }
                }
            }
        
        });
    }    

    @FXML
    private void handleNew(ActionEvent event) throws IOException {
        //Stage stage = new Stage(); 
        //stage.setScene(new Scene(new Group(new Text(10,10, "my second window")))); 
        //stage.show();
        
        
    }

    @FXML
    private void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
 
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XMI Files (*.xmi)", "*.xmi");
        fileChooser.getExtensionFilters().add(extFilter);
        File file;     
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);
    }

    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void handleSave(ActionEvent event) {
    }

    @FXML
    private void handleSaveAs(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
 
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XMI Files (*.xmi)", "*.xmi");
        fileChooser.getExtensionFilters().add(extFilter);
        File file;     
        //Show open file dialog
        file = fileChooser.showSaveDialog(null);
    }

    @FXML
    private void handleRevert(ActionEvent event) {
    }

    @FXML
    private void handlePreferences(ActionEvent event) throws IOException {
        Stage stage = new Stage(); 
        Parent root = FXMLLoader.load(getClass().getResource("Preferences.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Petri Netz Editor");
        stage.setScene(scene);
        stage.show();
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

    @FXML
    private void handlePlace(ActionEvent event) {
        Place p = new Place(100, 100, 30, a);
        a.getChildren().addAll(p);
    }

    @FXML
    private void handleTransition(ActionEvent event) {
        Transition t = new Transition(100, 100, 25, 90, a);
        a.getChildren().addAll(t);
    }

    @FXML
    private void handleTimedTransition(ActionEvent event) {
    }
    
}
