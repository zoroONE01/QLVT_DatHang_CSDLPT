/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Label;

/**
 *
 * @author zoroONE01
 */
public class Clock extends Thread{

    private Label lbTime;
    private Label lbDate;

    public Clock(Label lbTime, Label lbDate) {
        this.lbTime = lbTime;
        this.lbDate = lbDate;
    }

    @Override
    public void run() {
        SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss aa");
        SimpleDateFormat sdfDate = new SimpleDateFormat("EEEE, dd/MM/yyyy");
        while (true) {
            Date now = new Date();
            String timeNow = sdfTime.format(now);
            String dateNow = sdfDate.format(now);
            lbTime.setText(timeNow);
            lbDate.setText(dateNow);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("TimeAndDate: " + e.getMessage());
            }
        }
    }
}
