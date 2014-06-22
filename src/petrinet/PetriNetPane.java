/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinet;

import CommandSystem.CommandProcessor;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
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
    
    boolean isArrowButtonPressed;
    
    public PetriNetPane(AnchorPane p, CommandProcessor pr) {
        pane = p;
        processor = pr;
        isArrowButtonPressed = false;
        startSetted = false;
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
    
    public void setStart(double x, double y, AbstractPetriNetElement e) {
        startX = x;
        startY = y;
        startElement = e;
        startSetted = true;
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
    
    public void deselectAllElements() {
        for (Node n : pane.getChildren()) {
            if (n instanceof AbstractPetriNetElement) {
                AbstractPetriNetElement e = (AbstractPetriNetElement) n;
                e.setSelected(false);
            }
        }
    }

    public void delete(Arrow arrow) {
        pane.getChildren().remove(arrow);
    }

    public Arrow setEnd(double x, double y, AbstractPetriNetElement e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
