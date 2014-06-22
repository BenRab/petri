/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import javafx.scene.shape.Line;

/**
 *
 * @author ben
 */
public class Arrow extends Line {
    PetriNetElement startElement;
    PetriNetElement endElement;
    
    public Arrow(PetriNetElement start) {
        startElement = start;
    }
    
    public PetriNetElement getStartElement() {
        return startElement;
    }
    
    public void setEndElement() {
        
    }
}
