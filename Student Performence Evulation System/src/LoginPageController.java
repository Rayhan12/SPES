/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author rayha
 */
public class LoginPageController implements Initializable {
    
    
    @FXML
    private Button SingIn;
    @FXML
    private TextField UserID;
    @FXML
    private PasswordField Password;
    @FXML
    private Label text;
    
    @FXML
    private void SingInOnClick(ActionEvent event) throws IOException, SQLException {
        
       /* 
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("Management_UI/Management_main_UI.fxml"));                
            Scene scene2 = new Scene(scene2Parent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene2);
            window.show();
        
       */
        Database db = new Database();
        db.Get_ALL_Users();
        for(UserInfo user: db.users)
        {
            
            
            if(user.ID.equals(UserID.getText()) && user.Password.equals(Password.getText()))
            {
            Parent scene2Parent = FXMLLoader.load(getClass().getResource("Management_UI/Management_main_UI.fxml"));                
            Scene scene2 = new Scene(scene2Parent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene2);
            window.show();
            break;
            }
            else if(UserID.getText().equals(""))
            {
                text.setText("Imput User ID");
            }
            else if(Password.getText().equals(""))
            {
                text.setText("Imput Password");
            }
            else
            {
                text.setText("This user dose not exist in the system");
            }
        }

        
        
        
        
            

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
