/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Exception;

/**
 *
 * @author MinhTo
 */
public class LoginNameExistException extends RuntimeException{
    private String message;

    public LoginNameExistException(String message) {
        super(message);
    }
    
}
