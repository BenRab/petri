/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import CommandSystem.ComandProcessor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author ben
 */
public class Place extends PetriNetPane {   
    public Place(double x, double y, double radius, AnchorPane a, ComandProcessor c) {
        super(new Circle(x, y, radius), Color.WHITE, a, c);
    }
    //public Place(double x, double y) {
      //  super(x - 30, y - 30, 2*30, 2*30, new Circle(x, y, 30, Color.WHITE));
    //}

    @Override
    public boolean isPointInElement(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
