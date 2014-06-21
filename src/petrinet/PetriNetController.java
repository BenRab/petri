/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinet;

import petrinetelements.AbstractPetriNetElement;
import CommandSystem.CommandIF;
import CommandSystem.CommandProcessor;
import PetriNetCommands.AddPlaceCommand;
import PetriNetCommands.AddTimedTransition;
import PetriNetCommands.AddTransitionCommand;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author ben
 */
public class PetriNetController implements Initializable {
    
    private CommandProcessor processor;
    
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
    @FXML
    private TabPane tabs;
    @FXML
    private MenuItem undoMenu;
    @FXML
    private MenuItem redoMenu;
    @FXML
    private Button redoButton;
    @FXML
    private Button undoButton;
    @FXML
    private Button arrowButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        processor = new CommandProcessor(undoMenu, redoMenu, redoButton, undoButton);
        a.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                for (Node n : a.getChildren()) {
                    if (n instanceof AbstractPetriNetElement) {
                        AbstractPetriNetElement p = (AbstractPetriNetElement) n;
                        if (p.isPointInElement(t.getSceneX(), t.getSceneY())) {
                            p.setSelected(true);
                        } else {
                            p.setSelected(false);
                        }
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
        tabs.getTabs().addAll(new Tab("New Tab"));
        
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
        processor.undoLastCommand();
    }

    @FXML
    private void handleRedo(ActionEvent event) {
        processor.redoLastCommand();
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
    private void handlePlace(ActionEvent event) {
        CommandIF c = new AddPlaceCommand("fsd", a, processor);
        processor.executeCommand(c);
    }

    @FXML
    private void handleTransition(ActionEvent event) {
        CommandIF c = new AddTransitionCommand(a, processor);
        processor.executeCommand(c);
    }

    @FXML
    private void handleTimedTransition(ActionEvent event) {
        CommandIF c = new AddTimedTransition(a, processor);
        processor.executeCommand(c);
    }

    @FXML
    private void saveTab(Event event) {
    }

    @FXML
    private void selectNewTab(Event event) {
    }

    @FXML
    private void handleNewArrow(ActionEvent event) {
    }

    @FXML
    private void handleAbout(ActionEvent event) {
    }

    @FXML
    private void handleHelp(ActionEvent event) throws IOException {
        Stage stage = new Stage(); 
        Parent root = FXMLLoader.load(getClass().getResource("Help.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Petri Netz Editor");
        stage.setScene(scene);
        stage.show();
    }

    
}
