/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package petrinet;

/**
 *
 * @author ben
 */
public class LineConnectHelper {
    double startX;
    double startY;
    boolean startSetted;
    
    public LineConnectHelper(double x, double y) {
        startX = x;
        startY = y;
        startSetted = true;
    }
    
    public void addPoint(double x, double y) {
        if (startSetted) {
            
        }
        else {
            startX = x;
            startY = y;
        }
        startSetted = !startSetted;
    }
}
