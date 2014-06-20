/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PetriNetCommands;

import CommandSystem.AbstractReversebleCommand;
import CommandSystem.CommandProcessor;
import javafx.scene.layout.AnchorPane;
import petrinetelements.TimedTransition;

/**
 *
 * @author ben
 */
public class AddTimedTransition extends AbstractReversebleCommand {
    AnchorPane a;
    TimedTransition t;


    public AddTimedTransition(AnchorPane anchor, CommandProcessor c) {
        super("Timed Transition");
        a = anchor;
        t = new TimedTransition(100, 100, 25, 90, a, c);
    }
    
    @Override
    public void execute() {
        a.getChildren().addAll(t);
    }

    @Override
    public void undo() {
        a.getChildren().remove(t);
    }
    
}
