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

/**
 *
 * @author ben
 */
public class TimedTransition extends AbstractPetriNetElement {
    double layoutY, layoutX;
    double width;
    double height;
    
    public TimedTransition(double x, double y, double w, double h, AnchorPane a, CommandProcessor c) {
        super(new Rectangle(x, y, w, h), Color.BLACK, a, c);
        
        layoutX = x;
        layoutY = y;
        this.width = w;
        this.height = h;
        name = this.getLabel();
        getChildren().addAll(this.shape, name);
    }

    @Override
    public boolean isPointInElement(double x, double y) {
        return false;
    }

    @Override
    public Label getLabel() {
        name = new Label("Timed Transition");
        name.setLayoutX(layoutX - width);
        name.setLayoutY(layoutY - height * 0.25);    
        return name;
    }
}
