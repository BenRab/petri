/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PetriNetCommands;

import CommandSystem.AbstractReversebleCommand;
import petrinetelements.AbstractPetriNetElement;

/**
 *
 * @author ben
 */
public class DragAndDropCommand extends AbstractReversebleCommand {
    double prevX;
    double prevY;
    double endedX;
    double endedY;
    AbstractPetriNetElement petriNetElement;

    public DragAndDropCommand(AbstractPetriNetElement p, double x, double y) {
        super("Drag and Drop");
        petriNetElement = p;
        prevX = x;
        prevY = y;
        endedX = p.getLayoutX();
        endedY = p.getLayoutY();
    }
    
    @Override
    public void execute() {
        petriNetElement.setLayoutX(endedX);
        petriNetElement.setLayoutY(endedY);
        
    }

    @Override
    public void undo() {
        petriNetElement.setLayoutX(prevX);
        petriNetElement.setLayoutY(prevY);
    }
}
