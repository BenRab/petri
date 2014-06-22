/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinetelements;

import javafx.scene.paint.Color;

/**
 *
 * @author ben
 */
public interface PetriNetElement {
    public void setName(String n);
    
    public String getName();
    
    public boolean isSelected();
    
    public void setSelected(boolean b);
    
    public boolean isPointInElement(double x, double y);
}
