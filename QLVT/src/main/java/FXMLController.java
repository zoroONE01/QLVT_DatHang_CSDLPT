/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *  
 * As the FXMLLoader creates the View first, 
 * it’s tempting to believe that you can access them immediately. 
 * However, they can’t be connected to the @FXML-annotated fields in the Controller until the controller has been created
 * ----
 * As the FXMLLoader creates the Controller, it fires off the controller’s constructor. 
 * Any code you fire in the controller’s constructor will not know about the Java objects that constitute the View. 
 * If you try to access the View at this point, you’ll get a NullPointerException.
 * -----
 * After that, the FXMLLoader uses reflection to inject the view nodes into the Controller’s fields.
    -----
  Then, once the View nodes have been connected with the controller’s fields, 
* the initialize() method (if it’s present) is invoked. 
* At this point, the view’s nodes can be accessed from the Controller.
 * @author MinhTo
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override   //The view's node can be accessed from here
    public void initialize(URL url, ResourceBundle rb) { 
        // TODO
        
    }    
    
}
