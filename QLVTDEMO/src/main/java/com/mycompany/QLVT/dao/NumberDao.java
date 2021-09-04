/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

/**
 *
 * @author MinhTo
 */
public class NumberDao {
    
    public static int number=6;//du lieu trong DB
    
    public int getNumber()
    {
        return number;
    }
    public void save(int num)
    {
        number=num;
    }
    public void delete()
    {
        number=0;
    }
}
