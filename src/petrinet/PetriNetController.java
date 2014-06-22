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
import PetriNetCommands.AddTimedTransitionCommand;
import PetriNetCommands.AddTransitionCommand;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import petrinetelements.PetriNetElement;
import petrinetelements.Place;

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
    
    PetriNetPane currentPane;
    
    private ArrayList<AbstractPetriNetElement> copiedElements;
    
    private ArrayList<PetriNetPane> panes;
    @FXML
    private Label datails;
    @FXML
    private GridPane dets;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final double MAX_FONT_SIZE = 30.0; // define max font size you need
        datails.setFont(new Font(MAX_FONT_SIZE));
        processor = new CommandProcessor(undoMenu, redoMenu, redoButton, undoButton);
        panes = new ArrayList<>();
        copiedElements = new ArrayList<>();
        currentPane = new PetriNetPane(a, processor, dets);
        panes.add(currentPane);
               
        //at the moment not included: DESELECTION ON CLICK ON FIELD
        /*a.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        
        });*/
    }

    @FXML
    private void handleNew(ActionEvent event) throws IOException {
        //Stage stage = new Stage(); 
        //stage.setScene(new Scene(new Group(new Text(10,10, "my second window")))); 
        //stage.show();
        Tab t = new Tab("New Tab");

        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(new Place(100, 100, 30, this.panes.get(0)));
        t.setContent(pane);
        tabs.getTabs().addAll(t);
        
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
        Parent root = FXMLLoader.load(getClass().getResource("/petrinet/fxml/Preferences.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Preferences - Petri Netz Editor");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleQuit(ActionEvent event) {
        Stage stage = (Stage) undoButton.getScene().getWindow();
        stage.close();
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
    public void handleCut(ActionEvent event) {
        for (Node n : currentPane.getAnchor().getChildren()) {
            if (n instanceof AbstractPetriNetElement) {
                AbstractPetriNetElement e = (AbstractPetriNetElement) n;
                if (e.isSelected()) {
                    e.setVisible(false);
                    copiedElements.add(e);
                }
            }
        }
    }

    @FXML
    public void handleCopy(ActionEvent event) {
        copiedElements.clear();
        for (Node n : currentPane.getAnchor().getChildren()) {
            if (n instanceof AbstractPetriNetElement) {
                AbstractPetriNetElement e = (AbstractPetriNetElement) n;
                if (e.isSelected()) {
                    copiedElements.add(e);
                }
            }
        }
    }

    @FXML
    private void handlePast(ActionEvent event) {
        for (AbstractPetriNetElement e : copiedElements) {
            AbstractPetriNetElement copied = e.copy();
            currentPane.add(copied);
            e.setVisible(true);
        }
    }

    @FXML
    public void handleDelete(ActionEvent event) {
        for (Node n : currentPane.getAnchor().getChildren()) {
            if (n instanceof PetriNetElement) {
                PetriNetElement e = (PetriNetElement) n;
                if (e.isSelected()) {
                    e.setVisible(false);
                }
            }
        }
    }

    @FXML
    private void handleSelectAll(ActionEvent event) {
        for (Node n : currentPane.getAnchor().getChildren()) {
            if (n instanceof PetriNetElement) {
                PetriNetElement e = (PetriNetElement) n;
                e.setSelected(true);
            }
        }
    }

    @FXML
    private void handleUnselectAll(ActionEvent event) {
        for (Node n : currentPane.getAnchor().getChildren()) {
            if (n instanceof PetriNetElement) {
                PetriNetElement e = (PetriNetElement) n;
                e.setSelected(false);
            }
        }
    }

    @FXML
    private void handlePlace(ActionEvent event) {
        CommandIF c = new AddPlaceCommand(currentPane);
        processor.executeCommand(c);
    }

    @FXML
    private void handleTransition(ActionEvent event) {
        CommandIF c = new AddTransitionCommand(currentPane);
        processor.executeCommand(c);
    }

    @FXML
    private void handleTimedTransition(ActionEvent event) {
        CommandIF c = new AddTimedTransitionCommand(currentPane);
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
        if (currentPane.arrowModus()) {
            currentPane.setArrowModus(false);
        } else {
            currentPane.setArrowModus(true);
        }
    }

    @FXML
    private void handleAbout(ActionEvent event) throws IOException {
        Stage stage = new Stage(); 
        Parent root = FXMLLoader.load(getClass().getResource("/petrinet/fxml/About.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("About - Petri Netz Editor");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleHelp(ActionEvent event) throws IOException {
        Stage stage = new Stage(); 
        Parent root = FXMLLoader.load(getClass().getResource("/petrinet/fxml/Help.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Help - Petri Netz Editor");
        stage.setScene(scene);
        stage.show();
    }
}
