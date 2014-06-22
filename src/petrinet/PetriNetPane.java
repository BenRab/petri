/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinet;

import CommandSystem.CommandIF;
import CommandSystem.CommandProcessor;
import PetriNetCommands.AddArrowCommand;
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
}
