/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinet;

import CommandSystem.CommandIF;
import CommandSystem.CommandProcessor;
import PetriNetCommands.AddArrowCommand;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import petrinetelements.AbstractPetriNetElement;
import petrinetelements.Arrow;

/**
 *
 * @author ben
 */
public class PetriNetPane {
    AnchorPane pane;
    CommandProcessor processor;
        
    double startX;
    double startY;
    boolean startSetted;
    AbstractPetriNetElement startElement;
    
    private ArrayList<AbstractPetriNetElement> selectedElements;
    
    boolean isArrowButtonPressed;
    
    GridPane details;
    
    public PetriNetPane(AnchorPane p, CommandProcessor pr, GridPane d) {
        pane = p;
        processor = pr;
        isArrowButtonPressed = false;
        startSetted = false;
        
        selectedElements = new ArrayList<>();
        
        details = d;
    }
    
    public AnchorPane getAnchor() {
        return pane;
    }
    
    public CommandProcessor getProcessor() {
        return processor;
    }
    
    public boolean arrowModus() {
        return isArrowButtonPressed;
    }
    
    public AbstractPetriNetElement getHeadSelectedElement() {
        if (!selectedElements.isEmpty()) {
            return selectedElements.get(0);
        } else return null;
    }
    
    public void setStart(double x, double y, AbstractPetriNetElement e) {
        startX = x;
        startY = y;
        startElement = e;
        startSetted = true;
    }
    
    public void setEnd(double x, double y, AbstractPetriNetElement e) {
        Arrow a = new Arrow(startX, startY, x, y);
        System.out.println(a);
        CommandIF c = new AddArrowCommand(this, a, startElement, e);
        this.getProcessor().executeCommand(c);
        startSetted = false;
    }
    
    public boolean startSetted() {
        return startSetted && isArrowButtonPressed;
    }
    
    public void setArrowModus(boolean m) {
        isArrowButtonPressed = m;
    }
    
    public void add(Node n) {
        pane.getChildren().add(n);
    }
    
    public AbstractPetriNetElement getStartElement() {
        return startElement;
    }
    
    public void deselectAllElements() {
        for (Node n : pane.getChildren()) {
            if (n instanceof AbstractPetriNetElement) {
                AbstractPetriNetElement e = (AbstractPetriNetElement) n;
                e.setSelected(false);
            }
        }
    }

    public void delete(Arrow arrow) {
        arrow.setVisible(false);
    }
        
    public void deselectElement(AbstractPetriNetElement e) {
        selectedElements.remove(e);
    }
    
    public void selectElement(AbstractPetriNetElement e) {
        selectedElements.add(e);
        e.makeDetailsMenu();
    }
    
    public GridPane getDetails() {
        return details;
    }
}
