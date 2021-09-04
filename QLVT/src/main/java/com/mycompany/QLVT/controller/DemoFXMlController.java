package com.mycompany.QLVT.controller;

import com.mycompany.QLVT.model.NumberModel;
import com.mycompany.QLVT.service.NumberService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DemoFXMlController {
    
    public NumberService numService; 
    public NumberModel numberModel;

    public DemoFXMlController() {
        numService=new NumberService();
        numberModel=new NumberModel();
    }

    public DemoFXMlController(NumberService numService, NumberModel numberModel) {
        this.numService = numService;
        this.numberModel = numberModel;
    }

    public NumberService getNumService() {
        return numService;
    }

    public void setNumService(NumberService numService) {
        this.numService = numService;
    }

    public NumberModel getNumberModel() {
        return numberModel;
    }

    public void setNumberModel(NumberModel numberModel) {
        this.numberModel = numberModel;
    }
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button increaseButton;

    @FXML
    private TextField numberField;

    @FXML
    private Button decreaseButton;

    @FXML
    void decreaseNumber(ActionEvent event) {
           
        numberModel.setNum(numberModel.getNum()-1);
         int newValue=Integer.valueOf(numberField.getText());
         newValue=newValue-1;    
         numService.save(newValue);
         event.consume();
          
         //show lên lại
         
         numberField.setText(String.valueOf(numService.getNumber()));
         
    }

    @FXML
    void increaseNumber(ActionEvent event) {
         numberModel.setNum(numberModel.getNum()+1);
        int newValue=Integer.valueOf(numberField.getText());
         newValue=newValue+1;    
         numService.save(newValue);
          event.consume();
         //show lên lại
         
         numberField.setText(String.valueOf(numService.getNumber()));
    }

    @FXML
    void initialize() {
            
        System.out.println("INITIALIZE CALLED");
        numberModel.setNum(numService.getNumber());
       numberField.setText(String.valueOf(numberModel.getNum()));
       // numberField.textProperty().bind(numberModel.getAccountNumber().asString());
      //  numberField.setText(String.valueOf(numService.getNumber()));
        
        assert increaseButton != null : "fx:id=\"increaseButton\" was not injected: check your FXML file 'demoFXMl.fxml'.";
        assert numberField != null : "fx:id=\"numberField\" was not injected: check your FXML file 'demoFXMl.fxml'.";
        assert decreaseButton != null : "fx:id=\"decreaseButton\" was not injected: check your FXML file 'demoFXMl.fxml'.";
           
    }
}

