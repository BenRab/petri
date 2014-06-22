/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import petrinet.PetriNetPane;

/**
 *
 * @author ben
 */
public class TimedTransition extends AbstractTransition {
 
    public TimedTransition(double x, double y, double w, double h, PetriNetPane p) {
        super(new Rectangle(x, y, w, h), Color.BLACK, p);

    }

    @Override
    public String getName() {
        return "Timed Transition";
    }

}
