/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import CommandSystem.ComandIF;
import CommandSystem.ComandProcessor;
import PetriNetCommands.DragAndDropCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
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
public abstract class PetriNetPane extends Pane {
    boolean selected;
    Shape shape;
    AnchorPane anchor;
    ComandProcessor processor;
    private double dragDeltaY, dragDeltaX;
    double x, y;
    
    public final int RADIUS = 3;
    public final String COLOR = "PERU";
    
    public PetriNetPane(final Shape s, Color p, AnchorPane a, ComandProcessor c) {
        shape = s;
        anchor = a;
        processor = c;
        s.setFill(p);
        s.setStroke(Color.BLACK);
        setDragHandler(s, this);
        setContextMenu(s);
        getChildren().addAll(s);
        selected = false;
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
    
    private Circle initCircle(Circle c, double x, double y, int type) {
        c = new Circle(x, y, RADIUS);
        c.setFill( Color.web( COLOR, 0.8 ) );
        c.setStroke( Color.PERU );
        c.setStrokeWidth( 2 );
        c.setVisible(true);
        //setDragHandler(c, type);
        return c;
    }
    
    
    private void setContextMenu(Shape shape) {
        final MenuItem resizeItem = new MenuItem("Resize");
        resizeItem.setOnAction(new EventHandler<ActionEvent>() {
          @Override public void handle(ActionEvent event) {
            System.out.println("Resize requested");
          }
        });

        final ContextMenu menu = new ContextMenu(
          resizeItem
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
    
    private void setDragHandler( final Shape shape, final PetriNetPane p ) {  
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
            ComandIF c = new DragAndDropCommand(p, x, y);
            processor.setCommandExecuted(c);
          }
        } );
    }

    public abstract boolean isPointInElement(double x, double y);
}
