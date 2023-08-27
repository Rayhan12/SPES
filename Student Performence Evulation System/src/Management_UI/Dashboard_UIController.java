/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management_UI;

import DataBase.Course_PLO;
import DataBase.PLO;
import DataBase.Sem_PLO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author rayha
 */
public class Dashboard_UIController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label NumOfStudentLable;
    @FXML
    private ImageView AddStudent;
    @FXML
    private Button StudentView;
    @FXML
    private Label NumOfFacultyLable;
    @FXML
    private Button AddFaculty;
    @FXML
    private Button ViewFaculty;
    @FXML
    private Label NumOfCoursesLable;
    @FXML
    private Button AddCourse;
    @FXML
    private Button ViewCourses;
    @FXML
    private Label NumOfPLOLable;
    @FXML
    private Button AddPLO;
    @FXML
    private Button ViewPLO;
    @FXML
    private Label AvgPloLable;
    @FXML
    private Label PSFacultyLable;
    @FXML
    private Label AvgPLOmapLable;
    @FXML
    private Label PLOAchived;
    @FXML
    private BarChart<String, Float> AveragePloBarChart;
    @FXML
    private ComboBox<String> SelectPLO;
    @FXML
    private LineChart<String, Integer> PLObySemesterLineChart;
    @FXML
    private ComboBox<String> SelectCourse;
    @FXML
    private BarChart<String,Float> PLObyCourseBarChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Database db = new Database();
       
        //Lable initialize Section ==========================================================================
        try {
            NumOfStudentLable.setText(db.GetTotalNumberOfStudents().toString());
            NumOfFacultyLable.setText(db.GetTotalNumberOfFacultys().toString());
            NumOfCoursesLable.setText(db.GetTotalNumberOfCourses().toString());
            NumOfPLOLable.setText(db.GetTotalNumberOfPLOs().toString());
            AvgPloLable.setText(db.GetAvgPLOLable().toString());
            AvgPLOmapLable.setText(db.GetPLOmapingAVG().toString());
            PLOAchived.setText(db.Get_Average_PLO_Achieved().toString());
            db.GetAllPloinList();
            db.GetAllCourseinList();
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Select PLO comboBox Create ==========================================================================
        for(String plo : db.PLOArray )
        {
            SelectPLO.getItems().add(plo);
        }
        SelectPLO.setValue(db.PLOArray.get(0));
        
        //Select Course ComboBox Create ==========================================================================
        for(String course : db.CourseArray )
        {
            SelectCourse.getItems().add(course);
        }
        SelectCourse.setValue(db.CourseArray.get(0));
        
        
        
        
        
        //Average PLO Barchart Setup==================================================================
        try {
            db.GetAveragePLO();
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        XYChart.Series<String,Float> series = new XYChart.Series<String,Float>();
        for(PLO p : db.getAveragePLO)
        {
            series.getData().add(new XYChart.Data<String,Float>(p.name,p.point));
        }
        series.setName("Average PLO");
        AveragePloBarChart.setAnimated(false);
        AveragePloBarChart.getData().add(series);
        
        //Semester Wise plo Line Chart Setup =================================================================
        PLObySemesterLineChart.setAnimated(false);
        try {
            db.GetSemesterWiseAvgPloCount("plo1");
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        XYChart.Series<String,Integer> PLObySemesterLineChartAVG = new XYChart.Series<String,Integer>();
        for(Sem_PLO p : db.getSemesterWisePLOAverage())
        {
            String semester = p.getSem()+p.getYear().toString();
            PLObySemesterLineChartAVG.getData().add(new XYChart.Data<String,Integer>(semester,p.getCount()));
        }
        PLObySemesterLineChartAVG.setName("Expected");
        PLObySemesterLineChart.getData().add(PLObySemesterLineChartAVG);
        
        //Semester Wise plo Line Chart Setup (Compare) =================================================================
        try {
            db.GetSemesterWiseAvgAchievedPloCount("plo1");
        
        XYChart.Series<String,Integer> PLObySemesterLineChartAVGAchieved = new XYChart.Series<String,Integer>();
        for(Sem_PLO p : db.getSemesterWisePLOAverageAchieved())
        {
            String semester = p.getSem()+p.getYear().toString();
            PLObySemesterLineChartAVGAchieved.getData().add(new XYChart.Data<String,Integer>(semester,p.getCount()));
        }
        PLObySemesterLineChartAVGAchieved.setName("Actual");
        PLObySemesterLineChart.getData().add(PLObySemesterLineChartAVGAchieved);
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Course Wise plo Line Chart Setup =================================================================
        PLObyCourseBarChart.setAnimated(false);
        try {
            db.GetExpectedPLObyCourse("CSE101");
        
        XYChart.Series<String,Float> AchievablePLObyCourse = new XYChart.Series<String,Float>();
        for(Course_PLO p : db.getCourseWiseAchievablePLO())
        {
            AchievablePLObyCourse.getData().add(new XYChart.Data<String,Float>(p.getPLO(),p.getAchi()));
        }
        AchievablePLObyCourse.setName("Expected");
        PLObyCourseBarChart.getData().add(AchievablePLObyCourse);
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Course Wise plo Line Chart Setup (Compare)=================================================================
        try {
            db.GetAchivedPLObyCourse("CSE101");
        
        XYChart.Series<String,Float> AchievedPLObyCourse = new XYChart.Series<String,Float>();
        for(Course_PLO p : db.getCourseWiseAchievedPLO())
        {
            AchievedPLObyCourse.getData().add(new XYChart.Data<String,Float>(p.getPLO(),p.getAchi()));
        }
        AchievedPLObyCourse.setName("Achieved");
        PLObyCourseBarChart.getData().add(AchievedPLObyCourse);
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }    

    @FXML
    private void AddStudentOnClick(MouseEvent event) {
        
    }

    @FXML
    private void StudentViewOnClick(ActionEvent event) {
    }

    @FXML
    private void AddFacultyOnClick(ActionEvent event) {
    }

    @FXML
    private void ViewFacultyOnClick(ActionEvent event) {
    }

    @FXML
    private void AddCourseOnClick(ActionEvent event) {
    }

    @FXML
    private void ViewCoursesOnClick(ActionEvent event) {
    }

    @FXML
    private void AddPLOOnClick(ActionEvent event) {
    }

    @FXML
    private void ViewPLOOnClick(ActionEvent event) {
    }

    @FXML
    private void SelectPLOOnSelect(ActionEvent event) throws SQLException {
        
        //Semester Wise plo Line Chart Setup =================================================================
        PLObySemesterLineChart.getData().clear();
        PLObySemesterLineChart.setAnimated(false);
        Database db = new Database();
        db.GetSemesterWiseAvgPloCount(SelectPLO.getValue().toString());
        
        XYChart.Series<String,Integer> PLObySemesterLineChartAVG = new XYChart.Series<String,Integer>();
        for(Sem_PLO p : db.getSemesterWisePLOAverage())
        {
            String semester = p.getSem()+p.getYear().toString();
            PLObySemesterLineChartAVG.getData().add(new XYChart.Data<String,Integer>(semester,p.getCount()));
        }
        PLObySemesterLineChartAVG.setName("Expected");
        PLObySemesterLineChart.getData().add(PLObySemesterLineChartAVG);
        
        //Semester Wise plo Line Chart Setup(Compare) =================================================================
        
        db.GetSemesterWiseAvgAchievedPloCount(SelectPLO.getValue().toString());
        XYChart.Series<String,Integer> PLObySemesterLineChartAVGAchived = new XYChart.Series<String,Integer>();
        for(Sem_PLO p : db.getSemesterWisePLOAverageAchieved())
        {
            String semester = p.getSem()+p.getYear().toString();
            PLObySemesterLineChartAVGAchived.getData().add(new XYChart.Data<String,Integer>(semester,p.getCount()));
        }
        PLObySemesterLineChartAVGAchived.setName("Actual");
        PLObySemesterLineChart.getData().add(PLObySemesterLineChartAVGAchived);
        
    }

    @FXML
    private void SelectCourseOnSelect(ActionEvent event) throws SQLException {
        PLObyCourseBarChart.getData().clear();
        PLObyCourseBarChart.setAnimated(false);
        //Course Wise plo Line Chart Setup =================================================================
        Database db = new Database();
        db.GetExpectedPLObyCourse(SelectCourse.getValue().toString());
        
        XYChart.Series<String,Float> AchievablePLObyCourse = new XYChart.Series<String,Float>();
        for(Course_PLO p : db.getCourseWiseAchievablePLO())
        {
            AchievablePLObyCourse.getData().add(new XYChart.Data<String,Float>(p.getPLO(),p.getAchi()));
        }
        AchievablePLObyCourse.setName("Expected");
        PLObyCourseBarChart.getData().add(AchievablePLObyCourse);
       
        
        //Course Wise plo Line Chart Setup (Compare)=================================================================
        
        db.GetAchivedPLObyCourse(SelectCourse.getValue().toString());
        XYChart.Series<String,Float> AchievedPLObyCourse = new XYChart.Series<String,Float>();
        for(Course_PLO p : db.getCourseWiseAchievedPLO())
        {
            AchievedPLObyCourse.getData().add(new XYChart.Data<String,Float>(p.getPLO(),p.getAchi()));
        }
        AchievedPLObyCourse.setName("Achieved");
        PLObyCourseBarChart.getData().add(AchievedPLObyCourse);
        
    }
    
}
