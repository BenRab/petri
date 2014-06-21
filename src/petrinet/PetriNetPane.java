/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinet;

import CommandSystem.CommandProcessor;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author ben
 */
public class PetriNetPane {
    AnchorPane pane;
    CommandProcessor processor;
    
    public PetriNetPane(AnchorPane p, CommandProcessor pr) {
        pane = p;
        processor = pr;
    }
    
    public AnchorPane getAnchor() {
        return pane;
    }
    
    public CommandProcessor getProcessor() {
        return processor;
    }
}
