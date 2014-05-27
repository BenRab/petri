/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author ben
 */
public abstract class PetriNetPane extends Pane {
    boolean selected;
    Shape shape;
    AnchorPane anchor;
    
    public final int RADIUS = 3;
    public final String COLOR = "PERU";
    
    public PetriNetPane(final Shape s, Color p, AnchorPane a) {
        shape = s;
        anchor = a;
        s.setFill(p);
        s.setStroke(Color.BLACK);
        setDragHandler(s);
        setContextMenu(s);
        getChildren().addAll(s);
        selected = false;
        anchor.getChildren().addAll(s);
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
    
    private double dragDeltaY, dragDeltaX;
    
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
    
    private void setDragHandler( final Shape shape ) {
        
        shape.setOnMousePressed( new EventHandler<MouseEvent>() {
          @Override public void handle( MouseEvent mouseEvent ) {
            dragDeltaX = shape.getLayoutX()- mouseEvent.getSceneX();
            dragDeltaY = shape.getLayoutY() - mouseEvent.getSceneY();
          }
        } );

        shape.setOnMouseDragged( new EventHandler<MouseEvent>() {
          @Override public void handle( MouseEvent mouseEvent ) {
            shape.setLayoutX(mouseEvent.getSceneX() + dragDeltaX );
            shape.setLayoutY( mouseEvent.getSceneY() + dragDeltaY );
            shape.setCursor( Cursor.MOVE );
          }
        } );

        shape.setOnMouseEntered( new EventHandler<MouseEvent>() {
          @Override public void handle( MouseEvent mouseEvent ) {
            shape.setCursor( Cursor.HAND );
          }
        } );

        shape.setOnMouseReleased( new EventHandler<MouseEvent>() {
          @Override public void handle( MouseEvent mouseEvent ) {
            shape.setCursor( Cursor.HAND );
          }
        } );
    }

    abstract boolean isPointInElement(double x, double y);
}
