/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.DDH;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author zoroONE01
 */
public class DDHCommandHistory {

    private final Stack<DDHCommand> commandStack = new Stack<>();
//    private final Stack<DDHCommand> databaseStack = new Stack<>();
    private final Stack<DDHCommand> subStack = new Stack<>();
//    private int undoRedoPointer = 0;

    public Stack<DDHCommand> getSubStack() {
        return subStack;
    }

    public Stack<DDHCommand> getCommandStack() {
        return commandStack;
    }

//    public Stack<DDHCommand> getDatabaseStack() {
//        return databaseStack;
//    }

    public List<DDH> addInsertCommand(List<DDH> currentList, DDH donDatHang) {
//        deleteElementsAfterPointer(undoRedoPointer);
        DDHCommand command = new DDHInsert(currentList, donDatHang);
        command.execute();
        commandStack.push(command);
//        undoRedoPointer++;
//        databaseStack.push(new DDHDatabaseInsert(DDH));
        return command.getList();
    }

    public List<DDH> addDeleleCommand(List<DDH> currentList, DDH donDatHang) {
//        deleteElementsAfterPointer(undoRedoPointer);
        DDHCommand command = new DDHDelete(currentList, donDatHang);
        command.execute();
        commandStack.push(command);
//        undoRedoPointer++;
//        databaseStack.push(new DDHDatabaseDelete(DDH));
        return command.getList();
    }

    public List<DDH> addUpdateCommand(List<DDH> currentList, DDH oldDDH, DDH newDDH) {
//        deleteElementsAfterPointer(undoRedoPointer);
        DDHCommand command = new DDHUpdate(currentList, oldDDH, newDDH);
        command.execute();
        commandStack.push(command);
//        undoRedoPointer++;
//        databaseStack.push(new DDHDatabaseUpdate(newDDH));
        return command.getList();
    }

//    public void deleteElementsAfterPointer(int undoRedoPointer) {
//        if (commandStack.size() < 1) {
//            return;
//        }
//        for (int i = commandStack.size() - 1; i > undoRedoPointer; i--) {
//            commandStack.remove(i);
//        }
//    }
    public List<DDH> undo() {
        DDHCommand command = commandStack.pop();
        subStack.push(command);
//        subStack.push(databaseStack.pop());
        command.unExecute();
        return command.getList();
    }

    public List<DDH> redo() {
//        if (undoRedoPointer == 0) {
//            return null;
//        }
//        undoRedoPointer--;
//        databaseStack.push(subStack.pop());
        DDHCommand command = subStack.pop();
        commandStack.push(command);
        command.execute();
//        command.execute();
//        databaseStack.push(subStack.pop());
        return command.getList();
    }

    public void ExectueAllToDatebase() {
        if (commandStack.isEmpty()) {
            return;
        }
        commandStack.forEach(ddhCommand -> {
            ddhCommand.exectteToDatabase();
        });
        clearAllStack();
    }

    public void clearAllStack() {
        commandStack.clear();
//        databaseStack.clear();
        subStack.clear();
    }

    public boolean isCommandStackEmpty() {
        return commandStack.isEmpty();
    }

//    public boolean isDatabaseStackEmpty() {
//        return databaseStack.isEmpty();
//    }

    public boolean isSubStackEmpty() {
        return subStack.isEmpty();
    }

}
