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
public abstract class AbstractCommand implements ComandIF {
    final String Name;
    
    public AbstractCommand(final String n) {
        Name = n;
    }
    
    @Override
    public String getName() {
        return Name;
    }

    @Override
    abstract public void execute();

}
