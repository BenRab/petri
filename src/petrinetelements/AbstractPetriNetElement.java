/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import CommandSystem.CommandIF;
import PetriNetCommands.DragAndDropCommand;
import java.util.ArrayList;
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
import petrinet.PetriNetController;
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
    public ArrayList<Arrow> startArrows;
    public ArrayList<Arrow> endArrows;
        
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
        startArrows = new ArrayList<>();
        endArrows = new ArrayList<>();
    }
    
    public AbstractPetriNetElement copy () {
        //TODO
        //return new AbstractPetriNetElement(shape.);
        return null;
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
    
    public ArrayList<Arrow> getStartArrows() {
        return startArrows;
    }
    
    public ArrayList<Arrow> getEndArrows() {
        return endArrows;
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
    
    private void setContextMenu(final Shape shape, final AbstractPetriNetElement e) {
        final MenuItem deleteItem = new MenuItem("Delete");
        final MenuItem copyItem = new MenuItem("Copy");
        final MenuItem cutItem = new MenuItem("Cut");
        deleteItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                PetriNetController p = new PetriNetController();
                p.handleDelete(event);
            }
        });
        
        copyItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                PetriNetController p = new PetriNetController();
                p.handleCopy(event);
            }
        });
        
        cutItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                PetriNetController p = new PetriNetController();
                p.handleCut(event);
            }
        });
        

        final ContextMenu menu = new ContextMenu(
            deleteItem
        );
                
        e.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (pane.arrowModus()) {
                    if (pane.startSetted() && pane.getStartElement().getClass() == e.getClass()) {
                        shape.setFill(Color.RED);
                    } else {
                        shape.setFill(Color.LIGHTGREEN);
                    }
                }
            }
        });
        
        e.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (pane.arrowModus()) {
                    shape.setFill(color);
                }
            }
        });

        e.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                if (pane.arrowModus()) {
                    if (pane.startSetted()) {
                        pane.setEnd(e.getLayoutX() + shape.getBaselineOffset() + getCenterOffsetX(), 
                                e.getLayoutY() + shape.getBaselineOffset() + getCenterOffsetY(), e);
                    } else {
                        pane.setStart(e.getLayoutX() + shape.getBaselineOffset() + getCenterOffsetX(),
                                e.getLayoutY() + shape.getBaselineOffset() + + getCenterOffsetY(), e);
                    }
                } else {
                    pane.deselectAllElements();
                    setSelected(true);
                }
                if (MouseButton.SECONDARY.equals(event.getButton())) {
                    menu.show(pane.getAnchor(), event.getScreenX(), event.getScreenY());
                }
            }
        });
    }
    
    public void addStartArrow(Arrow a) {
        startArrows.add(a);
    }
    
    public void deleteStartArrow(Arrow a) {
        startArrows.remove(a);
    }
    
    public void addEndArrow(Arrow a) {
        endArrows.add(a);
    }
    
    public void deleteEndArrow(Arrow a) {
        endArrows.remove(a);
    }
    
    private void setDragHandler( final Shape shape, final AbstractPetriNetElement p ) {  
        x = p.getLayoutX();
        y = p.getLayoutY();
        
        shape.setOnMousePressed( new EventHandler<MouseEvent>() {
          @Override public void handle( MouseEvent mouseEvent ) {
              if (!pane.arrowModus()) {
                  x = p.getLayoutX();
                  y = p.getLayoutY();
                  dragDeltaX = getLayoutX()- mouseEvent.getSceneX();
                  dragDeltaY = getLayoutY() - mouseEvent.getSceneY();
              }
          }
        } );
        
        shape.setOnMouseDragged( new EventHandler<MouseEvent>() {
          @Override public void handle( MouseEvent mouseEvent ) {
              if (!pane.arrowModus()) {
                  double newX = mouseEvent.getSceneX() + dragDeltaX;
                  double newY = mouseEvent.getSceneY() + dragDeltaY;
                  setLayoutX(newX);
                  setLayoutY(newY);
                  for (Arrow a : startArrows) {
                      a.setStartX(newX);
                      a.setStartY(newY);
                  }
                  for (Arrow a : endArrows) {
                      a.setEndX(newX);
                      a.setEndY(newY);
                  }
                  setCursor( Cursor.MOVE );
              }
          }
        } );

        shape.setOnMouseEntered( new EventHandler<MouseEvent>() {
          @Override public void handle( MouseEvent mouseEvent ) {
              if (!pane.arrowModus()) {
                  setCursor( Cursor.HAND );
              }
          }
        } );

        shape.setOnMouseReleased( new EventHandler<MouseEvent>() {
          @Override public void handle( MouseEvent mouseEvent ) {
              if (!pane.arrowModus()) {
                  setCursor( Cursor.HAND );
                  CommandIF c = new DragAndDropCommand(p, x, y);
                  pane.getProcessor().setCommandExecuted(c);
                  setSelected(false);
              }
          }
        } );
    }
    
    public abstract double getCenterOffsetX();
    public abstract double getCenterOffsetY();

    @Override
    public abstract boolean isPointInElement(double x, double y);
}
