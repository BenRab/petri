/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PetriNetCommands;

import CommandSystem.AbstractReversebleCommand;
import CommandSystem.CommandProcessor;
import javafx.scene.layout.AnchorPane;
import petrinet.PetriNetPane;
import petrinetelements.TimedTransition;

/**
 *
 * @author ben
 */
public class AddTimedTransitionCommand extends AbstractReversebleCommand {
    PetriNetPane pane;
    TimedTransition t;


    public AddTimedTransitionCommand(PetriNetPane p) {
        super("Timed Transition");
        pane = p;
        t = new TimedTransition(100, 100, 25, 90, p);
    }
    
    @Override
    public void execute() {
        pane.getAnchor().getChildren().addAll(t);
    }

    @Override
    public void undo() {
        pane.getAnchor().getChildren().remove(t);
    }
}
