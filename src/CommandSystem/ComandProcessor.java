/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CommandSystem;

import java.util.Stack;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

/**
 *
 * @author ben
 */
public class ComandProcessor {
    private final Stack<ComandIF> undoStack;
    private final Stack<ComandIF> redoStack;
    private final MenuItem undoMenu;
    private final MenuItem redoMenu;
    private final Button undoButton;
    private final Button redoButton;
    
    public ComandProcessor(MenuItem u, MenuItem r, Button rB, Button uB) {
        undoStack = new Stack<>();
        redoStack = new Stack<>();    
        
        undoMenu = u;
        redoMenu = r;
        undoMenu.setDisable(true);
        redoMenu.setDisable(true);
        
        undoButton = uB;
        redoButton = rB;
        undoButton.setDisable(true);
        redoButton.setDisable(true);
    }
    
    public void executeCommand(ComandIF c) {
        c.execute();
        if (c instanceof ReversebleComandIF) {
            undoStack.push(c);
        }
        setMenus();
    }
    
    public void undoLastCommand() {
        if (!undoStack.empty()) {
            ComandIF c = undoStack.pop();
            if (c instanceof ReversebleComandIF) {
                ((ReversebleComandIF) c).undo();
                redoStack.push(c);
            }
        }
        setMenus();
    }
    
    public void redoLastCommand() {
        if (!redoStack.empty()) {
            ComandIF c = redoStack.pop();
            c.execute();
            undoStack.push(c);
        }
        setMenus();
    }
    
    public void setCommandExecuted(ComandIF c) {
        undoStack.add(c);
    }

    private void setMenus() {
        undoMenu.setDisable(true);
        undoButton.setDisable(true);
        if (!undoStack.isEmpty()) {
            undoMenu.setDisable(false);
            undoButton.setDisable(false);
        }
        redoMenu.setDisable(true);
        redoButton.setDisable(true);
        if (!redoStack.isEmpty()) {
            redoMenu.setDisable(false);
            redoButton.setDisable(false);
        }
    }
}
