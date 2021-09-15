/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

import com.mycompany.QLVT.Command.KhoCommand;
import java.util.Collections;
import java.util.Stack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author zoroONE01
 */
public class KhoCommandModel {

    private final ObservableList<String> commandList = FXCollections.observableArrayList();

    public KhoCommandModel() {
    }

    public void setCommandList(Stack<KhoCommand> stack) {
        if (stack.isEmpty()) {
            return;
        }
        stack.forEach(khoCommand -> {
            commandList.add(khoCommand.toString());
        });

    }

    public ObservableList<String> getCommandList() {
        Collections.reverse(commandList);
        return commandList;
    }

}
