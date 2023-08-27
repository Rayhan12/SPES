/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management_UI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author rayha
 */
public class Program_Management_UIController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Button ADD_CO;
    private Label CO_Lable;
    @FXML
    private ComboBox<String> Select_Course_ComboBox;
    @FXML
    private ComboBox<String> Select_PLO_Combo_Box;
    @FXML
    private Button ADD_Course;
    @FXML
    private TextField Course_Name;
    @FXML
    private TextField Course_ID;
    @FXML
    private Label CourseID_Check_Lable;
    @FXML
    private Button Add_Student;
    @FXML
    private TextField Student_ID;
    @FXML
    private TextField Student_Name;
    @FXML
    private TextField Student_Email;
    @FXML
    private ComboBox<?> Select_Gender_ComboBox;
    @FXML
    private DatePicker Birthday;
    @FXML
    private Label Check_Student_ID;
    @FXML
    private TextField Facylty_ID;
    @FXML
    private TextField Faculty_Name;
    @FXML
    private TextField Faculty_Email;
    @FXML
    private ComboBox<?> Select_Faculty_Gender_ComboBox;
    @FXML
    private DatePicker Faculty_Birthday;
    @FXML
    private Label Check_Faculty_ID;
    @FXML
    private Button Add_Faculty;
    @FXML
    private Button Upload_Student;
    @FXML
    private TextField Course_Name1;
    @FXML
    private TextField Course_Name11;
    @FXML
    private ComboBox<?> Select_PLO_Combo_Box1;
    @FXML
    private TextField Course_Name12;
    @FXML
    private TextField Course_Name111;
    @FXML
    private ComboBox<?> Select_PLO_Combo_Box11;
    @FXML
    private TextField Course_Name121;
    @FXML
    private TextField Course_Name1111;
    @FXML
    private ComboBox<?> Select_PLO_Combo_Box12;
    @FXML
    private TextField Course_Name122;
    @FXML
    private TextField Course_Name1112;
    @FXML
    private ComboBox<?> Select_PLO_Combo_Box121;
    @FXML
    private TextField Course_Name1221;
    @FXML
    private TextField Course_Name11121;
    @FXML
    private ComboBox<?> Select_PLO_Combo_Box1211;
    @FXML
    private TextField Course_Name12211;
    @FXML
    private TextField Course_Name111211;
    @FXML
    private Label CourseID_Check_Lable1;
    @FXML
    private Button Upload_Student1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Database db = new Database();
        try {
            db.GetAllPloinList();
            db.GetAllCourseinList();
            db.GetAllCOinList();
            Integer count = db.COArray.size()+1;
            CO_Lable.setText("CO"+count.toString());
            
        for(String plo : db.PLOArray )
            { Select_PLO_Combo_Box.getItems().add(plo); } 
            Select_PLO_Combo_Box.setValue(db.PLOArray.get(0));
        
        for(String Course : db.CourseArray )
            { Select_Course_ComboBox.getItems().add(Course); } 
            Select_Course_ComboBox.setValue(db.CourseArray.get(0));
            
        } catch (Exception ex) {}
    }    

    @FXML
    private void CourseID_Input_On_Input(InputMethodEvent event) throws SQLException {        
    }

    @FXML
    private void ADD_CO_On_Click(ActionEvent event) throws SQLException {
        Database db = new Database();
        //db.ADD_CO(CO_Lable.getText(), Select_Course_ComboBox.getValue(), Select_PLO_Combo_Box.getValue());
        
    }

    @FXML
    private void ADD_Course_On_Click(ActionEvent event) throws SQLException {
        String CourseID = Course_ID.getText().toUpperCase();
        Database db = new Database();
        db.GetAllCourseinList();
        
        for(String id : db.CourseArray)
        {
            if(CourseID.equals(id))
            {
                CourseID_Check_Lable.setText("This Course already exist in the system");
                break;
            }
            else if(CourseID.equals(""))
            {
                CourseID_Check_Lable.setText("Waiting For Input..... . .");
                
            }
            else if(!CourseID.equals(id))
            {
                CourseID_Check_Lable.setText("You can add this Course");
                
            }
        }
        
        if(!CourseID.equals("") && !Course_Name.getText().equals(""))
        {
            for(String id : db.CourseArray)
            {
                if(!CourseID.equals(id))
                {
                   //db.ADD_Course(CourseID, Course_Name.getText()); 
                }
            }
            
        }
        
    }

    @FXML
    private void CourseID_Input_Check(KeyEvent event) throws SQLException {
        String CourseID = Course_ID.getText().toUpperCase();
        Database db = new Database();
        db.GetAllCourseinList();
        
        for(String id : db.CourseArray)
        {
            
            if(CourseID.equals(""))
            {
                CourseID_Check_Lable.setText("Waiting For Input..... . .");
                
            }
            else if(!CourseID.equals(id))
            {
                CourseID_Check_Lable.setText("You can add this Course");
                
            }
        }
    }

    @FXML
    private void Add_Student_On_Click(ActionEvent event) {
    }

    @FXML
    private void Add_Faculty_On_Click(ActionEvent event) {
    }

    @FXML
    private void Upload_Student_On_Click(ActionEvent event) {
    }
    
}
