/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2.service;

import com.mycompany.mavenproject2.dao.NumberDao;

/**
 *
 * @author MinhTo
 */
public class NumberService {  //Xử lí logic trước khi lưu 
    
    public NumberDao numberDao=new NumberDao();

    public NumberService() {
        
    }

    public NumberService(NumberDao numberDao) {
        this.numberDao = numberDao;
    }
    
    public int getNumber()
    {
        return numberDao.getNumber();
    }
    public void save(int n)
    {//
         numberDao.save(n);
    }
    public void delete()
    {
        numberDao.delete();
    }
    public void increase()
    {
        int newNum=numberDao.getNumber();
        newNum++;
        numberDao.save(newNum);
    }
       public void deincrease()
    {
        int newNum=numberDao.getNumber();
        newNum--;
        numberDao.save(newNum);
    }
}
