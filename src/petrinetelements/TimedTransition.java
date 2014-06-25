/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import petrinet.PetriNetPane;

/**
 *
 * @author ben
 */
public class TimedTransition extends AbstractTransition {
 
    public TimedTransition(double x, double y, double w, double h, PetriNetPane p) {
        super(new Rectangle(x, y, w, h), Color.BLACK, p);

    }

    @Override
    public String getName() {
        return "Timed Transition";
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
                + " Die Timed Transition sorgt dafür, sie \n"
                + "sofort feuert, wenn die Bedingungen erfüllt \n"
                + " sind, wählen sie in der Werkzeugleiste \n"
                + " das schwarze gefüllte Rechteck aus. \n ";
    }

}
