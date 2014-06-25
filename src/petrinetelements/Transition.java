/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import CommandSystem.CommandProcessor;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import petrinet.PetriNetPane;

/**
 *
 * @author ben
 */
public class Transition extends AbstractTransition {

    public Transition(double x, double y, double width, double height, PetriNetPane p) {
        super(new Rectangle(x, y, width, height), Color.WHITE, p);
    }

    @Override
    public String getName() {
        return "Transition";
    }

    @Override
    public String getHelpText() {
                return "Transitionen stellen die Übergänge \n "
                + "zwischen Zuständen dar.  Diese \n"
                + "Übergänge werden automatisch aktiviert, \n"
                + " wenn bestimmte Voraussetzungen getroffen \n "
                + "sind. Im einfachsten Fall wird eine \n "
                + "Transition aktiviert, sobald alle Plätze, \n"
                + " die mit einem Pfeil auf die Transition \n "
                + "weisen, einen Token besitzen. Es besteht \n"
                + " die Möglichkeit, mehr als einen Token \n "
                + "pro Platz zu verlangen, damit eine \n "
                + "Transition aktiviert werden kann. \n "
                + "Die einfache Transition sorgt dafür, dass die \n "
                + "Transition zu einem zufälligen Zeitpunkt \n "
                + "feuert, während die Bedingungen erfüllt sind, \n "
                + "so wählen sie das unausgefüllt Rechteck aus.";
    }
    
}
