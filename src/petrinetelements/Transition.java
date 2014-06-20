/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import CommandSystem.ComandProcessor;
import petrinetelements.PetriNetPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author ben
 */
public class Transition extends PetriNetPane {

    public Transition(double x, double y, double width, double height, AnchorPane a, ComandProcessor c) {
        super(new Rectangle(x, y, width, height), Color.WHITE, a, c);
    }

    @Override
    public boolean isPointInElement(double x, double y) {
        return false;
    }
    
}