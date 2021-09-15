/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.Kho;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author zoroONE01
 */
public class KhoCommandHistory {

    private final Stack<KhoCommand> modelStack = new Stack<>();
    private final Stack<KhoCommand> databaseStack = new Stack<>();
    private final Stack<KhoCommand> subStack = new Stack<>();
//    private int undoRedoPointer = 0;

    public Stack<KhoCommand> getSubStack() {
        return subStack;
    }

    public Stack<KhoCommand> getModelStack() {
        return modelStack;
    }

    public Stack<KhoCommand> getDatabaseStack() {
        return databaseStack;
    }

    public List<Kho> addInsertCommand(List<Kho> currentList, Kho kho) {
//        deleteElementsAfterPointer(undoRedoPointer);
        KhoCommand command = new KhoInsert(currentList, kho);
        command.execute();
        modelStack.push(command);
//        undoRedoPointer++;
        databaseStack.push(new KhoDatabaseInsert(kho));
        return command.getList();
    }

    public List<Kho> addDeleleCommand(List<Kho> currentList, Kho kho) {
//        deleteElementsAfterPointer(undoRedoPointer);
        KhoCommand command = new KhoDelete(currentList, kho);
        command.execute();
        modelStack.push(command);
//        undoRedoPointer++;
        databaseStack.push(new KhoDatabaseDelete(kho));
        return command.getList();
    }

    public List<Kho> addUpdateCommand(List<Kho> currentList, Kho oldKho, Kho newKho) {
//        deleteElementsAfterPointer(undoRedoPointer);
        KhoCommand command = new KhoUpdate(currentList, oldKho, newKho);
        command.execute();
        modelStack.push(command);
//        undoRedoPointer++;
        databaseStack.push(new KhoDatabaseUpdate(newKho));
        return command.getList();
    }

//    public void deleteElementsAfterPointer(int undoRedoPointer) {
//        if (modelStack.size() < 1) {
//            return;
//        }
//        for (int i = modelStack.size() - 1; i > undoRedoPointer; i--) {
//            modelStack.remove(i);
//        }
//    }
    public List<Kho> undo() {
        KhoCommand command = modelStack.pop();
        subStack.push(command);
        subStack.push(databaseStack.pop());
        command.unExecute();
        return command.getList();
    }

    public List<Kho> redo() {
//        if (undoRedoPointer == 0) {
//            return null;
//        }
//        undoRedoPointer--;
        databaseStack.push(subStack.pop());
        KhoCommand command = subStack.pop();
        modelStack.push(command);
        command.execute();
//        command.execute();
//        databaseStack.push(subStack.pop());
        return command.getList();
    }

    public void ExectueAllToDatebase() {
        if (databaseStack.isEmpty()) {
            return;
        }
        databaseStack.forEach(khoCommand -> {
            khoCommand.execute();
        });
        clearAllStack();
    }

    public void clearAllStack() {
        modelStack.clear();
        databaseStack.clear();
        subStack.clear();
    }

    public boolean isModelStackEmpty() {
        return modelStack.isEmpty();
    }

    public boolean isDatabaseStackEmpty() {
        return databaseStack.isEmpty();
    }

    public boolean isSubStackEmpty() {
        return subStack.isEmpty();
    }

}
