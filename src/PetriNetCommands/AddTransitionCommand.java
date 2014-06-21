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
import petrinetelements.Transition;

/**
 *
 * @author ben
 */
public class AddTransitionCommand extends AbstractReversebleCommand {
    PetriNetPane pane;
    Transition t;

    public AddTransitionCommand(PetriNetPane p) {
        super("Transition");
        pane = p;
        t = new Transition (100, 100, 20, 85, p);
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
