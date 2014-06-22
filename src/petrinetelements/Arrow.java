/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;

/**
 *
 * @author ben
 */
public class Arrow extends Pane {
    Line line;
    
    public Arrow(double startX, double startY, double endX, double endY) {
        line = LineBuilder.create()
            .startX(startX)
            .startY(startY)
            .endX(endX)
            .endY(endY)
            .build();
        getChildren().add(line);
    }
    
    public void draw() {
        
    }
    
    public void setStartX(double x) {
        line.setStartX(x);
    }
    
    public void setStartY(double y) {
        line.setStartY(y);
    }
    
    public void setEndX(double x) {
        line.setEndX(x);
    }
    
    public void setEndY(double y) {
        line.setEndY(y);
    }
    
    public void setEndElement(double endX, double endY) {
        
    }
}
