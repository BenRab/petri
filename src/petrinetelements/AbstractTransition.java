/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import petrinet.PetriNetPane;

/**
 *
 * @author ben
 */
abstract public class AbstractTransition extends AbstractPetriNetElement {
    Rectangle rectangle;
    Label name;
    
    public AbstractTransition(Rectangle r, Color col, PetriNetPane p) {
        super(r, col, p);
        rectangle = r;
         name = new Label(getName());

        getChildren().addAll(this.shape, name);
        name.setLayoutX(rectangle.getX() - rectangle.getWidth());
        name.setLayoutY(rectangle.getY() - 0.25 * rectangle.getHeight());  
    }
    
    public abstract String getName();

    @Override
    public Label getLabel() {
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
    
    public abstract String getHelpText();
    
        @Override
    public void makeDetailsMenu() {
        Label lName = new Label("Name");
        
        final TextField tName = new TextField();
        tName.setText(name.getText());
        tName.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent t) {
                    getLabel().setText(tName.getText());
            }
          });
        
        this.pane.getDetails().getChildren().clear();
        this.pane.getDetails().add(lName, 0, 0);
        this.pane.getDetails().add(tName, 0,1);
        
        this.pane.getHelpLabel().setText(this.getHelpText());
  }
    
}
