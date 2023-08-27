/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management_UI;

import DataBase.Course_PLO_Mark;
import DataBase.PLO;
import DataBase.PLO_Gained_From_Course;
import DataBase.SeePLOAA;
import DataBase.SemYear;
import DataBase.Sem_PLO;
import DataBase.Student;
import DataBase.Student_All_Course_Mark;
import DataBase.Temp_student;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import static sun.plugin.javascript.navig.JSType.Image;

/**
 * FXML Controller class
 *
 * @author rayha
 */
public class Student_ProfileController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private ImageView ProfileImage;
    @FXML
    private Label StudentName;
    @FXML
    private Label IDLable;
    @FXML
    private Label SchoolLable;
    @FXML
    private Label DepartmentLable;
    @FXML
    private Label EmailLable;
    @FXML
    private Label SemesterLable;
    @FXML
    private Label GenderLable;
    @FXML
    private Label BirthdayLable;
    @FXML
    private Label AgeLable;
    @FXML
    private Label MostAchivedPloLable;
    @FXML
    private Label LestAchivedPloLable;
    @FXML
    private Tab AchvsAvgPLO_BarChart;
    @FXML
    private BarChart<String, Float> SemWisePLO_BarChart;
    @FXML
    private LineChart<String, Integer> ExpvsAchPLO_LineChart;
    @FXML
    private TableColumn<Student_All_Course_Mark, String> CourseID;
    @FXML
    private TableColumn<Student_All_Course_Mark, String> PLO1;
    @FXML
    private TableColumn<Student_All_Course_Mark, String> PLO2;
    @FXML
    private TableColumn<Student_All_Course_Mark, String> PLO3;
    @FXML
    private TableColumn<Student_All_Course_Mark, String> PLO4;
    @FXML
    private TableColumn<Student_All_Course_Mark, String> PLO5;
    @FXML
    private TableColumn<Student_All_Course_Mark, String> PLO6;
    @FXML
    private TableColumn<Student_All_Course_Mark, String> PLO7;
    @FXML
    private TableColumn<Student_All_Course_Mark, String> PLO8;
    @FXML
    private TableColumn<Student_All_Course_Mark, String> PLO9;
    @FXML
    private TableColumn<Student_All_Course_Mark, String> PLO10;
    @FXML
    private TableColumn<Student_All_Course_Mark, String> PLO11;
    @FXML
    private TableColumn<Student_All_Course_Mark, String> PLO12;
    @FXML
    private Label MostAchivedPloMarksLable;
    @FXML
    private Label LestAchivedPloMarksLable;    
    @FXML
    private BarChart<String, Float> AchvsAverageBarChart;
    @FXML
    private PieChart PLOGainedFromCourses_PaiChart;
    @FXML
    private ComboBox<String> SelectSemester_ComboBox;
    @FXML
    private ComboBox<String> SelectPLO_ComboBox;
    /**
     * Initializes the controller class.
     */
    
    private Student selectedStudent;
    private TextArea TextArea;
    @FXML
    private Label IndPLOAchivedPer;
    @FXML
    private TableView<Student_All_Course_Mark> TableView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Database db = new Database();
        String SemesterYear;
        try {
            db.GetAllYear();
            db.GetAllPloinList();
                for(SemYear p : db.GetYear )
                {
                    SemesterYear = p.semester+","+p.year.toString();
                    SelectSemester_ComboBox.getItems().add(SemesterYear);
                }
                SemesterYear =db.GetYear.get(0).semester+","+db.GetYear.get(0).year.toString(); 
                SelectSemester_ComboBox.setValue(SemesterYear);
        
        } catch (SQLException ex) {
            Logger.getLogger(Student_ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        for(String plo : db.PLOArray )
        {
            SelectPLO_ComboBox.getItems().add(plo);
        }
        SelectPLO_ComboBox.setValue(db.PLOArray.get(0));
        
        
        
        // Making the student for deploying
        
        try {
            db.Basic_Student.clear();
            db.GetAllStudents();   
            for(Temp_student s : db.getBasic_Student())
            {
                if(Objects.equals(s.getId(), db.getStudentID()))
                {
                // Making Actual Student
                selectedStudent = new Student(s.getId(),s.getName(),s.getGender(),s.getEmail(),s.getBirthday(),"Engineering",db.GetstudentSemester(s.getId()),
                        db.GetstudentAveragePLO(s.getId()),db.GetstudentMAXPLO(s.getId()).name,db.GetstudentMINPLO(s.getId()).name);
                }
            }
            db.DeleteStudentID(selectedStudent.getId());
            Float max = db.GetstudentMAXPLO(selectedStudent.getId()).point;
            Float min = db.GetstudentMINPLO(selectedStudent.getId()).point;
            MostAchivedPloLable.setText(db.GetstudentMAXPLO(selectedStudent.getId()).name.toUpperCase());
            MostAchivedPloMarksLable.setText(max.toString());
            LestAchivedPloLable.setText(db.GetstudentMINPLO(selectedStudent.getId()).name.toUpperCase());
            LestAchivedPloMarksLable.setText(min.toString());
        } catch (SQLException ex) {
            Logger.getLogger(Student_Evulation_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            StudentName.setText(selectedStudent.getName());
            IDLable.setText(selectedStudent.getId().toString());
            SchoolLable.setText("CSE");
            DepartmentLable.setText(selectedStudent.getDepartment());
            EmailLable.setText(selectedStudent.getEmail());
            SemesterLable.setText(selectedStudent.getSemester().toString());
            GenderLable.setText(selectedStudent.getGender());
            BirthdayLable.setText(selectedStudent.getBirthday().toString());
            AgeLable.setText(selectedStudent.getAge().toString());
        
    // Implementing Charts---------------------------------------------------------------------------------------------------------
    
    //Average VS achieved BarChart-------------------------------------------------------------------------------------------------
    //Achived PLO
    try {
            db.GetAverageVSAchievedPLOByStudent(selectedStudent.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        XYChart.Series<String,Float> AchievedPLO = new XYChart.Series<String,Float>();
        for(PLO p : db.getAverageVSAchievedPLOByStudent())
        {
            AchievedPLO.getData().add(new XYChart.Data<String,Float>(p.name,p.point));
        }
        AchievedPLO.setName("Achieved PLO");
        AchvsAverageBarChart.getData().add(AchievedPLO);
    //Average PLO
    try {
            db.GetAveragePLO();
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        XYChart.Series<String,Float> AveragePLO = new XYChart.Series<String,Float>();
        for(PLO p : db.getAveragePLO)
        {
            AveragePLO.getData().add(new XYChart.Data<String,Float>(p.name,p.point));
        }
        AveragePLO.setName("Average PLO");
        AchvsAverageBarChart.getData().add(AveragePLO);
        
        
    //Student wise PLO attempt and achieved --------------------------------------------------------------------------------
    SemWisePLO_BarChart.setAnimated(false);
    try {
            db.GetStudentwisePloAttemptCount(selectedStudent.getId());
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        XYChart.Series<String,Integer> PLObySemesterLineChartAVG = new XYChart.Series<String,Integer>();
            for(Sem_PLO p : db.StudentwisePLOAttempt)
            {
                String semester = p.getSem()+p.getYear().toString();
                PLObySemesterLineChartAVG.getData().add(new XYChart.Data<String,Integer>(semester,p.getCount()));
            }
        PLObySemesterLineChartAVG.setName("Expected");
        ExpvsAchPLO_LineChart.getData().add(PLObySemesterLineChartAVG);
        
        //Student wise achived plo by semester (Compare) =================================================================
        try {
            db.GetStudentwisePloAchievedCount(selectedStudent.getId());
        
        XYChart.Series<String,Integer> PLObySemesterLineChartAVGAchieved = new XYChart.Series<String,Integer>();
        for(Sem_PLO p : db.StudentwisePLOAchieved)
        {
            String semester = p.getSem()+p.getYear().toString();
            PLObySemesterLineChartAVGAchieved.getData().add(new XYChart.Data<String,Integer>(semester,p.getCount()));
        }
        PLObySemesterLineChartAVGAchieved.setName("Actual");
        ExpvsAchPLO_LineChart.getData().add(PLObySemesterLineChartAVGAchieved);
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    // Semester wise plo achived and achievable by student
    
        try {
            db.GetSemesterwisePloAchiveableByStudent(selectedStudent.getId(),"Summer" , 2019);
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        XYChart.Series<String,Float> StAchievablePLO = new XYChart.Series<String,Float>();
                for(SeePLOAA p : db.SemesterwisePLOAchievablebyStudent)
                {
                    
                    StAchievablePLO.getData().add(new XYChart.Data<String,Float>(p.plo,p.achievable));
                }
        StAchievablePLO.setName("Expected");
        SemWisePLO_BarChart.getData().add(StAchievablePLO);
        
       try {
            db.GetSemesterwisePloAchievedByStudent(selectedStudent.getId(),"Summer" , 2019);
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        XYChart.Series<String,Float> StAchievedPLO = new XYChart.Series<String,Float>();
            for(SeePLOAA p : db.SemesterwisePLOAchievedbyStudent)
            {
                StAchievedPLO.getData().add(new XYChart.Data<String,Float>(p.plo,p.achieved));
            }
        StAchievedPLO.setName("Achieved");
        SemWisePLO_BarChart.getData().add(StAchievedPLO);
        
        
            //PLO Gained from Cources-------------------------------------------------------------------------------------------
        try {
            db.GetPloGainedFromCourse(selectedStudent.getId(), "plo1");
        } catch (SQLException ex) {Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);}
        
        ObservableList <PieChart.Data> DataList = FXCollections.observableArrayList();
        for(PLO_Gained_From_Course pg : db.PLOGained_from_Cources)
        {
            IndPLOAchivedPer.setText(pg.pper.toString()+"%");
            DataList.add(new PieChart.Data(pg.course+" = "+pg.cper.toString()+"%",pg.cper));
        }
        PLOGainedFromCourses_PaiChart.setData(DataList);
            
        
        
            //Coure Table Implementation_--------------------------------------------------------------------------------------------
            //Coure Table Implementation_--------------------------------------------------------------------------------------------
            //Coure Table Implementation_--------------------------------------------------------------------------------------------
        
        
        
        try {
            ArrayList<Student_All_Course_Mark> TotalMarkList = new ArrayList<Student_All_Course_Mark>();
            db.GetAllCourseinList(); // getting all course in list
            for(String s : db.CourseArray)
            {
                
                db.Get_A_Single_CourseWisePloMark(selectedStudent.getId(), s); // now markArray has this students one course and there plo mark 
                
                String courseName = null;
                String PLO1 =null;
                String PLO2 =null;
                String PLO3=null;
                String PLO4=null;
                String PLO5=null;
                String PLO6=null;
                String PLO7=null;
                String PLO8=null;
                String PLO9=null;
                String PLO10=null;
                String PLO11=null;
                String PLO12=null;
                courseName = s; 
                        for(Course_PLO_Mark cp : db.markList) 
                        {
                            
                                if(cp.ploName.equals("plo1") ) { PLO1 = cp.mark.toString()+"%";} 
                        else    if(cp.ploName.equals("plo2") ) { PLO2 = cp.mark.toString()+"%";} 
                        else    if(cp.ploName.equals("plo3") ) { PLO3 = cp.mark.toString()+"%";} 
                        else    if(cp.ploName.equals("plo4") ) { PLO4 = cp.mark.toString()+"%";} 
                        else    if(cp.ploName.equals("plo5") ) { PLO5 = cp.mark.toString()+"%";} 
                        else    if(cp.ploName.equals("plo6") ) { PLO6 = cp.mark.toString()+"%";} 
                        else    if(cp.ploName.equals("plo7") ) { PLO7 = cp.mark.toString()+"%";} 
                        else    if(cp.ploName.equals("plo8") ) { PLO8 = cp.mark.toString()+"%";} 
                        else    if(cp.ploName.equals("plo9") ) { PLO9 = cp.mark.toString()+"%";} 
                        else    if(cp.ploName.equals("plo10") ) { PLO10 = cp.mark.toString()+"%";} 
                        else    if(cp.ploName.equals("plo11") ) { PLO11 = cp.mark.toString()+"%";} 
                        else    if(cp.ploName.equals("plo12") ) { PLO12 = cp.mark.toString()+"%";} 
                        }
            TotalMarkList.add(new Student_All_Course_Mark(courseName,PLO1,PLO2,PLO3,PLO4,PLO5,PLO6,PLO7,PLO8,PLO9,PLO10,PLO11,PLO12));
            //Objects Created

            }
       
        
        ObservableList<Student_All_Course_Mark> CourseMarksTable = FXCollections.observableArrayList();
        
        //Setting up column
        CourseID.setCellValueFactory(new PropertyValueFactory<Student_All_Course_Mark,String>("courseName"));
        PLO1.setCellValueFactory(new PropertyValueFactory<Student_All_Course_Mark,String>("plo1"));
        PLO2.setCellValueFactory(new PropertyValueFactory<Student_All_Course_Mark,String>("plo2"));
        PLO3.setCellValueFactory(new PropertyValueFactory<Student_All_Course_Mark,String>("plo3"));
        PLO4.setCellValueFactory(new PropertyValueFactory<Student_All_Course_Mark,String>("plo4"));
        PLO5.setCellValueFactory(new PropertyValueFactory<Student_All_Course_Mark,String>("plo5"));
        PLO6.setCellValueFactory(new PropertyValueFactory<Student_All_Course_Mark,String>("plo6"));
        PLO7.setCellValueFactory(new PropertyValueFactory<Student_All_Course_Mark,String>("plo7"));
        PLO8.setCellValueFactory(new PropertyValueFactory<Student_All_Course_Mark,String>("plo8"));
        PLO9.setCellValueFactory(new PropertyValueFactory<Student_All_Course_Mark,String>("plo9"));
        PLO10.setCellValueFactory(new PropertyValueFactory<Student_All_Course_Mark,String>("plo10"));
        PLO11.setCellValueFactory(new PropertyValueFactory<Student_All_Course_Mark,String>("plo11"));
        PLO12.setCellValueFactory(new PropertyValueFactory<Student_All_Course_Mark,String>("plo12"));
        
        //Show on Table
        TableView.setItems(CourseMarksTable);
        
        //Getting Data for Table from ArrayList        
        for(Student_All_Course_Mark  Object : TotalMarkList)
         {
            TableView.getItems().add(Object);
          }
        TableView.setItems(CourseMarksTable);

        } catch (SQLException ex) {Logger.getLogger(Student_ProfileController.class.getName()).log(Level.SEVERE, null, ex);}
        
        
        
        

    }    


    @FXML
    private void SelectSemester_ComBoxOnSelect(ActionEvent event) throws SQLException {
        SemWisePLO_BarChart.getData().clear();
        SemWisePLO_BarChart.setAnimated(false);
        
        Database db = new Database();
        SemYear item = Spliter(SelectSemester_ComboBox.getValue().toString());
        db.GetSemesterwisePloAchiveableByStudent(selectedStudent.getId(),item.semester , item.year);
 
        XYChart.Series<String,Float> StAchievablePLO = new XYChart.Series<String,Float>();
                for(SeePLOAA p : db.SemesterwisePLOAchievablebyStudent)
                {
                    
                    StAchievablePLO.getData().add(new XYChart.Data<String,Float>(p.plo,p.achievable));
                }
        StAchievablePLO.setName("Expected");
        SemWisePLO_BarChart.getData().add(StAchievablePLO);
    
        db.GetSemesterwisePloAchievedByStudent(selectedStudent.getId(),item.semester , item.year);
        XYChart.Series<String,Float> StAchievedPLO = new XYChart.Series<String,Float>();
            for(SeePLOAA p : db.SemesterwisePLOAchievedbyStudent)
            {
                StAchievedPLO.getData().add(new XYChart.Data<String,Float>(p.plo,p.achieved));
            }
        StAchievedPLO.setName("Achieved");
        SemWisePLO_BarChart.getData().add(StAchievedPLO);
        
        
    }

    @FXML
    private void SelectPLO_ComboBoxOnSelect(ActionEvent event) throws SQLException {
        
        Database db = new Database();
        db.GetPloGainedFromCourse(selectedStudent.getId(), SelectPLO_ComboBox.getValue().toString());
        
        ObservableList <PieChart.Data> DataList = FXCollections.observableArrayList();
        for(PLO_Gained_From_Course pg : db.PLOGained_from_Cources)
        {
            IndPLOAchivedPer.setText(pg.pper.toString()+"%");
            DataList.add(new PieChart.Data(pg.course+" = "+pg.cper.toString()+"%",pg.cper));
        }
        PLOGainedFromCourses_PaiChart.setData(DataList);
        PLOGainedFromCourses_PaiChart.legendVisibleProperty();
    }


    
    
    
    public SemYear Spliter(String str)
    {
        SemYear item = new SemYear();
        String token[];
        token = str.split(",");
        item.semester = token[0];
        item.year = Integer.parseInt(token[1]);
        return item;
    }
    
}
