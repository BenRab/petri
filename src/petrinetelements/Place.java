/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import java.util.Stack;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import petrinet.PetriNetPane;

/**
 *
 * @author ben
 */
public class Place extends AbstractPetriNetElement {   
    final double tokenSize;
    Stack<Circle> tokens = new Stack<>();
    double layoutY, layoutX;
    double r;
    Label name = new Label("Place");;
    
    public Place(double x, double y, double radius, PetriNetPane p) {
        super(new Circle(x, y, radius), Color.WHITE, p);
        tokenSize = radius / 3;
        layoutX = x;
        layoutY = y;
        
        r = radius;
        name.setLayoutX(layoutX - r * 0.75);
        name.setLayoutY(layoutY - r * 1.75); 
        getChildren().addAll(this.shape, name);
    }

    @Override
    public boolean isPointInElement(double x, double y) {
        double posX = this.getLayoutX()- r ;
        double posY = this.getLayoutY() - r ;
        double posX1 = this.getLayoutX() + r ;
        double posY1 = this.getLayoutY()  + r ;
        double x1 = x - this.getScene().getX();
        double y1 = y - this.getScene().getY();
        
        System.out.println(posX + " <  " + x1  + " < " + posX1);
        System.out.println(posY + " <  " + y1 + " < " + posY1);
        return this.getX() - r < x && this.getY() - r < y && x < this.getX() + r && y < this.getY() + r;
    }
    
    public int getNumToken() {
        return tokens.size();
    }
    
    public void setTokens(int size) {
        deleteTokens();
        if (size == 1) {
            Circle token = new Circle(r / 6, Color.BLACK);
            token.setLayoutX(layoutX - r * 0.2);
            token.setLayoutY(layoutY - r * 0.2);
            this.getChildren().addAll(token);
        } else if (size == 2) {
            Circle token = new Circle(r / 6, Color.BLACK);
            token.setLayoutX(layoutX - r * 0.2);
            token.setLayoutY(layoutY - r * 0.2);
            Circle token1 = new Circle(r / 6, Color.BLACK);
            token1.setLayoutX(layoutX + r * 0.2);
            token1.setLayoutY(layoutY + r * 0.2);
            tokens.push(token);
            tokens.push(token1);
            this.getChildren().addAll(token, token1);
        } else if (size > 2) {
            Label l = new Label(String.valueOf(size));
            l.setLayoutX(layoutX - r * 0.2);
            l.setLayoutY(layoutY - r * 0.2);
            this.getChildren().addAll(l);
        }
    }
    
    public void deleteTokens() {
        this.getChildren().remove(tokens);
    }

    @Override
    public Label getLabel() {
        return name;
    }

    @Override
    public double getCenterOffsetX() {
        return this.r;
    }

    @Override
    public double getCenterOffsetY() {
        return this.r;
    }

    @Override
    public void makeDetailsMenu() {
        Label lName = new Label("Name");
        Label lTokens = new Label("Tokens");
        
        final TextField tName = new TextField();
        tName.setOnInputMethodTextChanged(new EventHandler<InputMethodEvent>() {
               @Override
               public void handle(InputMethodEvent inputMethodEvent) {
                    System.out.println("Text changed");
               }
          });
        TextField tTokens = new TextField();
        tName.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent t) {
                    getLabel().setText(tName.getText());
                    System.out.println(tName.getText());
            }
          });
        //tName.setOnKeyReleased(x);
        this.pane.getDetails().getChildren().clear();
        this.pane.getDetails().add(lName, 0, 0);
        this.pane.getDetails().add(tName, 1, 0);
    }
}
