/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author ben
 */
public abstract class PetriNetPane extends Pane {
    Circle c1;
    Circle c2;
    Circle c3;
    Circle c4;
    Rectangle r;
    Shape s;
    
    public final int RADIUS = 3;
    public final String COLOR = "PERU";
    
    public PetriNetPane(double x, double y, double width, double height, Shape s) {
        s.setStroke(Color.BLACK);
        r = new Rectangle(x, y, height, width);
        c1 = initCircle(c1, x, y, 0);
        c2 = initCircle(c2, x + width, y, 1);
        c3 = initCircle(c3, x, y + height, 2);
        c4 = initCircle(c4, x + width, y + height, 3);
        
        //c1.setVisible(true);
        
        getChildren().addAll(c1, c2, c3, c4, s);
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
    
    private double dragDeltaXS, dragDeltaYS, dragDeltaXC1, dragDeltaYC1, dragDeltaXC2, dragDeltaYC2, dragDeltaXC3, dragDeltaYC3, dragDeltaXC4, dragDeltaYC4; 
    
    private void setDragHandler( final Shape circle, final int type) {
    circle.setOnMousePressed( new EventHandler<MouseEvent>() {
      @Override public void handle( MouseEvent mouseEvent ) {
        dragDeltaXC1 = c1.getCenterX() - mouseEvent.getSceneX();
        dragDeltaYC1 = c1.getCenterY() - mouseEvent.getSceneY();
        dragDeltaXC2 = c2.getCenterX() - mouseEvent.getSceneX();
        dragDeltaYC2 = c2.getCenterY() - mouseEvent.getSceneY();
        dragDeltaXC3 = c3.getCenterX() - mouseEvent.getSceneX();
        dragDeltaYC3 = c3.getCenterY() - mouseEvent.getSceneY();
        dragDeltaXC4 = c4.getCenterX() - mouseEvent.getSceneX();
        dragDeltaYC4 = c4.getCenterY() - mouseEvent.getSceneY();
        //dragDeltaXS = s.getLayoutX() - mouseEvent.getSceneX();
        //dragDeltaYS = s.getLayoutY() - mouseEvent.getSceneY();
      }
    } );

    circle.setOnMouseDragged( new EventHandler<MouseEvent>() {
      @Override public void handle( MouseEvent mouseEvent ) {
          if (type == 0 ) {
            c1.setCenterX(mouseEvent.getSceneX() + dragDeltaXC1);
            c1.setCenterY(mouseEvent.getSceneY() + dragDeltaYC1);
            c2.setCenterY(mouseEvent.getSceneY() + dragDeltaYC2);
            c3.setCenterX(mouseEvent.getSceneX() + dragDeltaXC3);
          }
          else if (type == 1) {
            c1.setCenterY(mouseEvent.getSceneY() + dragDeltaYC1);
            c2.setCenterX(mouseEvent.getSceneX() + dragDeltaXC2);
            c2.setCenterY(mouseEvent.getSceneY() + dragDeltaYC2);
            c4.setCenterX(mouseEvent.getSceneX() + dragDeltaXC4);
          }
          else if (type == 2) {
            c1.setCenterX(mouseEvent.getSceneX() + dragDeltaXC1);
            c3.setCenterX(mouseEvent.getSceneX() + dragDeltaXC3);
            c3.setCenterY(mouseEvent.getSceneY() + dragDeltaYC3);
            c4.setCenterY(mouseEvent.getSceneY() + dragDeltaYC4);
          }
          else {
            c4.setCenterX(mouseEvent.getSceneX() + dragDeltaXC4);
            c4.setCenterY(mouseEvent.getSceneY() + dragDeltaYC4);
            c3.setCenterY(mouseEvent.getSceneY() + dragDeltaYC3);
            c2.setCenterX(mouseEvent.getSceneX() + dragDeltaXC2);
          }
                    
          if (s instanceof Circle) {
              Circle c = (Circle) s;
              c.setRadius(45);
          }
          
        //s.setLayoutX(mouseEvent.getSceneX()  + dragDeltaXS);
        //s.setLayoutY(mouseEvent.getSceneY() + dragDeltaYS);
          circle.setCursor( Cursor.MOVE );
        }
    } );

    circle.setOnMouseEntered( new EventHandler<MouseEvent>() {
      @Override public void handle( MouseEvent mouseEvent ) {
        circle.setCursor( Cursor.HAND );
      }
    } );

    circle.setOnMouseReleased( new EventHandler<MouseEvent>() {
      @Override public void handle( MouseEvent mouseEvent ) {
        circle.setCursor( Cursor.HAND );
      }
    } );
    
    circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
            c1.setVisible(true);
            c2.setVisible(true);
            c3.setVisible(true);
            c4.setVisible(true);
            }
        });
  }
}
