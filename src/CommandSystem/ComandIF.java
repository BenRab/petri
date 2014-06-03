/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CommandSystem;

/**
 *
 * @author ben
 */
public interface ComandIF {
    /**
     * @return name
     */
    public String getName();
    
    /**
     * run the comand
     */
    public void execute();
    
}
