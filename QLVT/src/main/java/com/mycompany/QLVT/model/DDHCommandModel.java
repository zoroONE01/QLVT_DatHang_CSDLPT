/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

import com.mycompany.QLVT.Command.DDHCommand;
import java.util.Collections;
import java.util.Stack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author zoroONE01
 */
public class DDHCommandModel {

    private final ObservableList<String> commandList = FXCollections.observableArrayList();

    public DDHCommandModel() {
    }

    public void setCommandList(Stack<DDHCommand> stack) {
        if (stack.isEmpty()) {
            return;
        }
        stack.forEach(DDHCommand -> {
            commandList.add(DDHCommand.toString());
        });

    }

    public ObservableList<String> getCommandList() {
        Collections.reverse(commandList);
        return commandList;
    }

}
