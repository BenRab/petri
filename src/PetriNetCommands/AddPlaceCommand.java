/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PetriNetCommands;

import CommandSystem.AbstractReversebleCommand;
import CommandSystem.CommandProcessor;
import javafx.scene.layout.AnchorPane;
import petrinetelements.Place;

/**
 *
 * @author ben
 */
public class AddPlaceCommand extends AbstractReversebleCommand {
    AnchorPane a;
    Place p;

    public AddPlaceCommand(String n, AnchorPane anchor, CommandProcessor c) {
        super(n);
        a = anchor;
        p = new Place(100, 100, 30, a, c);
        p.setTokens(2);
    }

    @Override
    public void execute() {
        a.getChildren().addAll(p);    
    }

    @Override
    public void undo() {
        a.getChildren().remove(p);
    }
    
}
