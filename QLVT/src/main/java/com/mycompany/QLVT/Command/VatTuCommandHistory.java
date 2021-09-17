/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.VatTu;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author zoroONE01
 */
public class VatTuCommandHistory {

    private final Stack<VatTuCommand> commandStack = new Stack<>();
//    private final Stack<VatTuCommand> databaseStack = new Stack<>();
    private final Stack<VatTuCommand> subStack = new Stack<>();
//    private int undoRedoPointer = 0;

    public Stack<VatTuCommand> getSubStack() {
        return subStack;
    }

    public Stack<VatTuCommand> getCommandStack() {
        return commandStack;
    }

//    public Stack<VatTuCommand> getDatabaseStack() {
//        return databaseStack;
//    }

    public List<VatTu> addInsertCommand(List<VatTu> currentList, VatTu vatTu) {
//        deleteElementsAfterPointer(undoRedoPointer);
        VatTuCommand command = new VatTuInsert(currentList, vatTu);
        command.execute();
        commandStack.push(command);
//        undoRedoPointer++;
//        databaseStack.push(new VatTuDatabaseInsert(vatTu));
        return command.getList();
    }

    public List<VatTu> addDeleleCommand(List<VatTu> currentList, VatTu vatTu) {
//        deleteElementsAfterPointer(undoRedoPointer);
        VatTuCommand command = new VatTuDelete(currentList, vatTu);
        command.execute();
        commandStack.push(command);
//        undoRedoPointer++;
//        databaseStack.push(new VatTuDatabaseDelete(vatTu));
        return command.getList();
    }

    public List<VatTu> addUpdateCommand(List<VatTu> currentList, VatTu oldVatTu, VatTu newVatTu) {
//        deleteElementsAfterPointer(undoRedoPointer);
        VatTuCommand command = new VatTuUpdate(currentList, oldVatTu, newVatTu);
        command.execute();
        commandStack.push(command);
//        undoRedoPointer++;
//        databaseStack.push(new VatTuDatabaseUpdate(newVatTu));
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
    public List<VatTu> undo() {
        VatTuCommand command = commandStack.pop();
        subStack.push(command);
//        subStack.push(databaseStack.pop());
        command.unExecute();
        return command.getList();
    }

    public List<VatTu> redo() {
//        if (undoRedoPointer == 0) {
//            return null;
//        }
//        undoRedoPointer--;
//        databaseStack.push(subStack.pop());
        VatTuCommand command = subStack.pop();
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
        commandStack.forEach(vtCommand -> {
            vtCommand.exectteToDatabase();
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
