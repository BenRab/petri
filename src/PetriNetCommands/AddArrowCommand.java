/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PetriNetCommands;

import CommandSystem.AbstractReversebleCommand;
import javafx.scene.paint.Color;
import petrinet.PetriNetPane;
import petrinetelements.Arrow;

/**
 *
 * @author ben
 */
public class AddArrowCommand extends AbstractReversebleCommand{
    Arrow arrow;
    PetriNetPane pane;

    public AddArrowCommand(PetriNetPane p, Arrow a) {
        super("Arrow");
        pane = p;
        arrow = a;
    }

    @Override
    public void execute() {
        arrow.setEndX(100);
        arrow.setEndY(100);
        arrow.setVisible(true);
        pane.add(arrow);
    }

    @Override
    public void undo() {
        pane.delete(arrow);
    }
    
}