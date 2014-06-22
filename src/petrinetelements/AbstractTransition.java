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
abstract public class AbstractTransition extends AbstractPetriNetElement {
    Rectangle rectangle;
    
    public AbstractTransition(Rectangle r, Color col, PetriNetPane p) {
        super(r, col, p);
        rectangle = r;
        name = this.getLabel();
        getChildren().addAll(this.shape, name);
    }
    
    public abstract String getName();

    @Override
    public Label getLabel() {
        name = new Label(getName());
        name.setLayoutX(rectangle.getX() - rectangle.getWidth());
        name.setLayoutY(rectangle.getY() - 0.25 * rectangle.getHeight());    
        return name;
    }

    @Override
    public boolean isPointInElement(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public double getCenterOffsetX() {
        return rectangle.getWidth();
    }

    @Override
    public double getCenterOffsetY() {
        return rectangle.getHeight();
    }
    
}
