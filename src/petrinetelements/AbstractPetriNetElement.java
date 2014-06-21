/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import CommandSystem.CommandIF;
import CommandSystem.CommandProcessor;
import PetriNetCommands.DragAndDropCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 *
 * @author ben
 */
public abstract class AbstractPetriNetElement extends Pane {
    boolean selected;
    protected Shape shape;
    AnchorPane anchor;
    CommandProcessor processor;
    private double dragDeltaY, dragDeltaX;
    protected double x, y;
    Label name;
    
    public final int RADIUS = 3;
    public final String COLOR = "PERU";
    
    public AbstractPetriNetElement(final Shape s, Color p, AnchorPane a, CommandProcessor c) {
        shape = s;
        anchor = a;
        processor = c;
        s.setFill(p);
        s.setStroke(Color.BLACK);
        setDragHandler(s, this);
        setContextMenu(s);
        selected = false;
    }
    
    /**
     * @return
     */
    public abstract Label getLabel();

    
    public double getX() {
        return shape.getLayoutX();
    }
    
    public double getY() {
        return shape.getLayoutY();
    }
    
    public void setName(String n) {
        name.setText(n);
    }
    
    public String getName() {
        return name.getText();
    }
    
    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean b) {
        selected = b;
        if (selected) {
            shape.setStroke(Color.RED);
            shape.setStrokeDashOffset(5);
        }
        else {
            shape.setStroke(Color.BLACK);
        }
    }
    
    private void setContextMenu(final Shape shape) {
        final MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(new EventHandler<ActionEvent>() {
          @Override public void handle(ActionEvent event) {
            shape.setVisible(false);
          }
        });

        final ContextMenu menu = new ContextMenu(
          deleteItem
        );

        shape.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent event) {
            setSelected(true);
            if (MouseButton.SECONDARY.equals(event.getButton())) {
              menu.show(anchor, event.getScreenX(), event.getScreenY());
            }  
          }
        });
    }
    
    private void setDragHandler( final Shape shape, final AbstractPetriNetElement p ) {  
        x = p.getLayoutX();
        y = p.getLayoutY();
        
        shape.setOnMousePressed( new EventHandler<MouseEvent>() {
          @Override public void handle( MouseEvent mouseEvent ) {
            x = p.getLayoutX();
            y = p.getLayoutY();
            dragDeltaX = getLayoutX()- mouseEvent.getSceneX();
            dragDeltaY = getLayoutY() - mouseEvent.getSceneY();
          }
        } );
        
        shape.setOnMouseDragged( new EventHandler<MouseEvent>() {
          @Override public void handle( MouseEvent mouseEvent ) {
            setLayoutX(mouseEvent.getSceneX() + dragDeltaX );
            setLayoutY( mouseEvent.getSceneY() + dragDeltaY );
            setCursor( Cursor.MOVE );
          }
        } );

        shape.setOnMouseEntered( new EventHandler<MouseEvent>() {
          @Override public void handle( MouseEvent mouseEvent ) {
            setCursor( Cursor.HAND );
          }
        } );

        shape.setOnMouseReleased( new EventHandler<MouseEvent>() {
          @Override public void handle( MouseEvent mouseEvent ) {
            setCursor( Cursor.HAND );
            CommandIF c = new DragAndDropCommand(p, x, y);
            processor.setCommandExecuted(c);
          }
        } );
    }

    public abstract boolean isPointInElement(double x, double y);
}
