/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinet;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author ben
 */
public class Place extends PetriNetPane {   
    public Place(double x, double y, double radius) {
        super(x - radius, y - radius, 2*radius, 2*radius, new Circle(x, y, radius, Color.WHITE));
    }
    //public Place(double x, double y) {
      //  super(x - 30, y - 30, 2*30, 2*30, new Circle(x, y, 30, Color.WHITE));
    //}
}
