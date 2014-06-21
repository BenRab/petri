/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import CommandSystem.CommandProcessor;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import petrinet.PetriNetPane;

/**
 *
 * @author ben
 */
public class Transition extends AbstractTransition {

    public Transition(double x, double y, double width, double height, PetriNetPane p) {
        super(new Rectangle(x, y, width, height), Color.WHITE, p);
    }

    @Override
    public String getName() {
        return "Transition";
    }
    
}
