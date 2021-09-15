/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.Kho;
import java.util.List;

/**
 *
 * @author zoroONE01
 */
public class KhoMemento {
    
    List<Kho> list;

    public List<Kho> getState()
    {
        return this.list;
    }

    public void setState(List<Kho> list)
    {
        this.list = list;
    }
}
