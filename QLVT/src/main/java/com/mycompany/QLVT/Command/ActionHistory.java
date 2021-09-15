/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import java.util.Stack;

/**
 *
 * @author MinhTo
 */
public class ActionHistory {

    private Stack<ActionListenerCommand> history = new Stack<>();

    public void push(ActionListenerCommand command) {
        history.push(command);
    }

    public ActionListenerCommand pop() {
        return history.pop();
    }

    public ActionListenerCommand peek() {
        return history.peek();
    }

    public Stack<ActionListenerCommand> getHistory() {
        return history;
    }

}
