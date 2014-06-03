/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PetriNetCommands;

import CommandSystem.AbstractReversebleCommand;
import CommandSystem.ComandProcessor;
import javafx.scene.layout.AnchorPane;
import petrinetelements.Transition;

/**
 *
 * @author ben
 */
public class AddTransitionCommand extends AbstractReversebleCommand {

    AnchorPane a;
    Transition t;

    public AddTransitionCommand(AnchorPane anchor, ComandProcessor c) {
        super("Transition");
        a = anchor;
        t = new Transition (100, 100, 25, 90, a, c);
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
