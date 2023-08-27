/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management_UI;

import DataBase.Student;
import DataBase.Temp_student;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayha
 */
public class Student_Evulation_UIController implements Initializable {

    @FXML
    private Button ViewDetailes;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TableColumn<Student, String> Name;
    @FXML
    private TableColumn<Student, Integer> StudentID;
    @FXML
    private TableColumn<Student,String> Department;
    @FXML
    private TableColumn<Student, Integer> Semester;
    @FXML
    private TableColumn<Student, Float> AveragePLO;
    @FXML
    private TableColumn<Student, String> MostAchievedPlo;
    @FXML
    private TableColumn<Student, String> LestAchievedPLO;
    @FXML
    private TextField SearchBox;
    @FXML
    private Button AddStudent;
    @FXML
    private TableView<Student> TableView;
    @FXML
    private Label NumberOfStudentLable;
    @FXML
    private TextArea TextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Seting up Student ArrayList for Showing on TableView
        ArrayList<Student> StudentList = new ArrayList<Student>();
        
        Database db = new Database();
        try {
            db.GetAllStudents();
            NumberOfStudentLable.setText(db.GetTotalNumberOfStudents().toString());
            
            for(Temp_student s : db.getBasic_Student())
            {
                // Making Actual Student
                StudentList.add(new Student(s.getId(),s.getName(),s.getGender(),s.getEmail(),s.getBirthday(),"Engineering",db.GetstudentSemester(s.getId()),
                        db.GetstudentAveragePLO(s.getId()),db.GetstudentMAXPLO(s.getId()).name,db.GetstudentMINPLO(s.getId()).name));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_Evulation_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        //Creating Table View
        
        ObservableList<Student> StudentTable = FXCollections.observableArrayList();
        
        //Setting up column
        Name.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
        StudentID.setCellValueFactory(new PropertyValueFactory<Student,Integer>("id"));
        Department.setCellValueFactory(new PropertyValueFactory<Student,String>("department"));
        Semester.setCellValueFactory(new PropertyValueFactory<Student,Integer>("semester"));
        AveragePLO.setCellValueFactory(new PropertyValueFactory<Student,Float>("average_plo"));
        MostAchievedPlo.setCellValueFactory(new PropertyValueFactory<Student,String>("most_plo"));
        LestAchievedPLO.setCellValueFactory(new PropertyValueFactory<Student,String>("lest_pli"));
        //Show on Table
        TableView.setItems(StudentTable);
        //Sorting The List By ID
        Collections.sort(StudentList, (Student st1, Student st2) -> st1.getId().compareTo(st2.getId()));
        //Getting Data for Table from ArrayList        
        for(Student student : StudentList)
         {
            TableView.getItems().add(student);
          }
        TableView.setItems(StudentTable);
        
        //Implementing Searching Options 
        
        FilteredList<Student> filteredData = new FilteredList<>(StudentTable, b -> true);
		// 2. Set the filter Predicate whenever the filter changes.
		SearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Student -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Student.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				}
                                else if (String.valueOf(Student.getId()).indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                else if (Student.getDepartment().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                else if (Student.getMost_plo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                 else if (Student.getLest_pli().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else  return false; 
                                });
                            });  
        SortedList<Student> sortedData = new SortedList<>(filteredData);
	sortedData.comparatorProperty().bind(TableView.comparatorProperty());
	TableView.setItems(sortedData);
        
        
    }    

    @FXML
    private void ViewDetailesOnClick(ActionEvent event) throws IOException, SQLException {
        
        Database db = new Database();
        db.setStudentID(TableView.getSelectionModel().getSelectedItem().getId());
        

       AnchorPane pane = FXMLLoader.load(getClass().getResource("Student_Profile.fxml"));
       AnchorPane.getChildren().setAll(pane);

        
        
    }

    @FXML
    private void AddStudentOnClick(ActionEvent event) {
    }


    
}
