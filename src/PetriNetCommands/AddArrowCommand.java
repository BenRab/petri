/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PetriNetCommands;

import CommandSystem.AbstractReversebleCommand;
import petrinet.PetriNetPane;
import petrinetelements.AbstractPetriNetElement;
import petrinetelements.Arrow;

/**
 *
 * @author ben
 */
public class AddArrowCommand extends AbstractReversebleCommand{
    Arrow arrow;
    PetriNetPane pane;
    AbstractPetriNetElement startElement;
    AbstractPetriNetElement endElement;

    public AddArrowCommand(PetriNetPane p, Arrow a, AbstractPetriNetElement s, AbstractPetriNetElement e) {
        super("Arrow");
        pane = p;
        arrow = a;
        startElement = s;
        endElement = e;
    }

    @Override
    public void execute() {
        System.out.println(arrow);
        arrow.setVisible(true);
        endElement.addEndArrow(arrow);
        startElement.addStartArrow(arrow);
        pane.add(arrow);
    }

    @Override
    public void undo() {
        endElement.deleteEndArrow(arrow);
        startElement.deleteStartArrow(arrow);
        arrow.setVisible(false);
    }
    
}
