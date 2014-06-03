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
public abstract class AbstractReversebleCommand extends AbstractCommand implements ReversebleComandIF {

    public AbstractReversebleCommand(String n) {
        super(n);
    }

    @Override
    abstract public void execute();

    @Override
    abstract public void undo();
}
