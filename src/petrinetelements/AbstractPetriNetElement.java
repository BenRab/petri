/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import CommandSystem.CommandIF;
import PetriNetCommands.DragAndDropCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import petrinet.PetriNetPane;

/**
 *
 * @author ben
 */
public abstract class AbstractPetriNetElement extends Pane implements PetriNetElement {
    boolean selected;
    protected Shape shape;
    PetriNetPane pane;
    private double dragDeltaY, dragDeltaX;
    protected double x, y;
    Label name;
    public Color color;
        
    public final int RADIUS = 3;
    public final String COLOR = "PERU";
    
    public AbstractPetriNetElement(final Shape s, Color col, PetriNetPane p) {
        shape = s;
        pane = p;
        s.setFill(col);
        color = col;
        s.setStroke(Color.BLACK);
        setDragHandler(s, this);
        setContextMenu(s, this);
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
    
    @Override
    public void setName(String n) {
        name.setText(n);
    }
    
    @Override
    public String getName() {
        return name.getText();
    }
    
    @Override
    public boolean isSelected() {
        return selected;
    }
    
    @Override
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
    
    private void setContextMenu(final Shape shape, final PetriNetElement e) {
        final MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(new EventHandler<ActionEvent>() {
          @Override public void handle(ActionEvent event) {
            shape.setVisible(false);
          }
        });

        final ContextMenu menu = new ContextMenu(
          deleteItem
        );
        
        shape.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (pane.arrowModus()) {
                    shape.setFill(Color.LIGHTGREEN);
                }
            }
        });
        
        shape.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (pane.arrowModus()) {
                    shape.setFill(color);
                }
            }
        });

        shape.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                if (pane.arrowModus()) {
                    Arrow a = new Arrow(e);
                    a.setStartX(event.getX());
                    a.setStartY(event.getY());
                }
                else {
                    pane.deselectAllElements();
                    setSelected(true);
                    if (MouseButton.SECONDARY.equals(event.getButton())) {
                      menu.show(pane.getAnchor(), event.getScreenX(), event.getScreenY());
                    }
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
            pane.getProcessor().setCommandExecuted(c);
          }
        } );
    }

    @Override
    public abstract boolean isPointInElement(double x, double y);
}
