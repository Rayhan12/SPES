/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management_UI;

import DataBase.Course;
import DataBase.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author rayha
 */
public class Course_Evulation_UIController implements Initializable {

    @FXML
    private TextField SearchBox;
    @FXML
    private Button AddCourse;
    @FXML
    private TableColumn<Course, String> CourseID;
    @FXML
    private TableColumn<Course, String> Depertment;
    @FXML
    private TableColumn<Course, Integer> NumberOfPLO;
    @FXML
    private TableColumn<Course, Float> AvgPloCon;
    @FXML
    private TableColumn<Course, Integer> TotalSectionOffered;
    @FXML
    private Button ViewDetailes;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label NumberOfCourse;
    @FXML
    private TableView<Course> TableView;
    private TextArea TextArea;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Database db = new Database();
        try {
            NumberOfCourse.setText(db.GetTotalNumberOfCourses().toString());
        } catch (SQLException ex) {Logger.getLogger(Course_Evulation_UIController.class.getName()).log(Level.SEVERE, null, ex);}
    
        ArrayList<Course> CourseList = new ArrayList<Course>();
        
        try {
            db.GetAllCourseinList();
            for(String c : db.CourseArray )
            {
                CourseList.add(new Course(c,"Engineering",db.Get_Course_Wise_PLO_Count(c),db.Get_Course_Section_offered(c),db.Get_Course_Average_PLO_Contribution(c)));
            }
        } catch (SQLException ex) {Logger.getLogger(Course_Evulation_UIController.class.getName()).log(Level.SEVERE, null, ex);}
    
        
        
        ObservableList<Course> CourseTable = FXCollections.observableArrayList();
        
        //Setting up column
        CourseID.setCellValueFactory(new PropertyValueFactory<Course,String>("courseID"));
        Depertment.setCellValueFactory(new PropertyValueFactory<Course,String>("depertment"));
        NumberOfPLO.setCellValueFactory(new PropertyValueFactory<Course,Integer>("NumberOfPLO"));
        AvgPloCon.setCellValueFactory(new PropertyValueFactory<Course,Float>("AvgPloCon"));
        TotalSectionOffered.setCellValueFactory(new PropertyValueFactory<Course,Integer>("TotalSectionOffered"));

        //Show on Table
        TableView.setItems(CourseTable);
        
        //Getting Data for Table from ArrayList        
        for(Course course : CourseList)
         {
            TableView.getItems().add(course);
          }
        TableView.setItems(CourseTable);
    
        //Implementing Searching Options 
        
        FilteredList<Course> filteredData = new FilteredList<>(CourseTable, b -> true);
		// 2. Set the filter Predicate whenever the filter changes.
		SearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Course -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Course.getCourseID().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				}
                                
				else  return false; 
                                });
                            });  
        SortedList<Course> sortedData = new SortedList<>(filteredData);
	sortedData.comparatorProperty().bind(TableView.comparatorProperty());
	TableView.setItems(sortedData);    
    
    
    
    
    
    
    }    

    @FXML
    private void AddCourseOnClick(ActionEvent event) {
    }

    @FXML
    private void ViewDetailesOnClick(ActionEvent event) throws IOException, SQLException {
        
        Database db = new Database();
        db.setCourseID(TableView.getSelectionModel().getSelectedItem().getCourseID());
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Course_Profile.fxml"));
        AnchorPane.getChildren().setAll(pane);
    }
    
}
