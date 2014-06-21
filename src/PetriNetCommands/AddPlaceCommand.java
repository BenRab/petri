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
import petrinetelements.Place;

/**
 *
 * @author ben
 */
public class AddPlaceCommand extends AbstractReversebleCommand {
    Place p;
    PetriNetPane pane;

    public AddPlaceCommand(PetriNetPane pa) {
        super("Place");
        pane = pa;
        p = new Place(100, 100, 30, pa);
        p.setTokens(2);
    }

    @Override
    public void execute() {
        pane.getAnchor().getChildren().addAll(p);    
    }

    @Override
    public void undo() {
        pane.getAnchor().getChildren().remove(p);
    }
    
}
