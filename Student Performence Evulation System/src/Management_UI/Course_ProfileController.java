/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management_UI;

import DataBase.Course;
import DataBase.PLO;
import DataBase.PLO_Gained_From_Course;
import DataBase.Sem_PLO;
import DataBase.V_Table;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author rayha
 */
public class Course_ProfileController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private ImageView ProfileImage;
    @FXML
    private Label IDLable;
    @FXML
    private Label SchoolLable;
    @FXML
    private Label DepartmentLable;
    @FXML
    private Label CourseName;
    @FXML
    private Label ConnectedPLOLable;
    @FXML
    private Label SectionLable;
    @FXML
    private Label AvgCon;
    @FXML
    private ComboBox<String> SelectPLOComboBox;
    @FXML
    private LineChart<String, Integer> PLOprobysemLineChart;
    @FXML
    private PieChart PLOContributionPieChart;
    @FXML
    private TableColumn<V_Table, String> COandPLO;
    @FXML
    private TableColumn<V_Table, Integer> SuccessfullyAchieved;
    @FXML
    private TableColumn<V_Table, Float> SuccessfullyAchieved_P;
    @FXML
    private TableColumn<V_Table, Integer> FailedToAchieved;
    @FXML
    private TableColumn<V_Table, Float> FailedToAchieved_P;
    @FXML
    private Label NumberOfStudentsLable;
    @FXML
    private TableView<V_Table> TableView;
    private Course course;
    private Integer Snumber;
    private String ploname;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Database db = new Database();
        try {
            course = new Course(db.getCourseID(),"Engineering",db.Get_Course_Wise_PLO_Count(db.getCourseID()),db.Get_Course_Section_offered(db.getCourseID()),db.Get_Course_Average_PLO_Contribution(db.getCourseID()));
        IDLable.setText(course.getCourseID());
        DepartmentLable.setText(course.getDepertment());
        CourseName.setText(course.getCourseID());
        ConnectedPLOLable.setText(course.getNumberOfPLO().toString());
        SectionLable.setText(course.getTotalSectionOffered().toString());
        db.Get_Course_PLO_Contribution_BY_plo(course.getCourseID());
        String total="";
        for(PLO p : db.PLO_For_Course)
        {
            total = total+p.toString();
        }
        AvgCon.setText(total);
        
        db.DeleteCourseID(course.getCourseID());
        

        } catch (SQLException ex) {Logger.getLogger(Course_ProfileController.class.getName()).log(Level.SEVERE, null, ex);}
        
        
        db.PLO_For_Course.clear();
        try {
            db.Get_Course_Related_PLO_List(course.getCourseID());
            
        } catch (SQLException ex) {
            Logger.getLogger(Course_ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(String plo : db.Related_PLO_List )
        {
            SelectPLOComboBox.getItems().add(plo);
        }
        SelectPLOComboBox.setValue(db.Related_PLO_List.get(0));
        ploname = db.Related_PLO_List.get(0);
        
        
        //Implementing PIE chart
        try {
            db.Get_Course_PLO_Contribution(course.getCourseID());
        } catch (SQLException ex) {Logger.getLogger(Dashboard_UIController.class.getName()).log(Level.SEVERE, null, ex);}
        
        ObservableList <PieChart.Data> DataList = FXCollections.observableArrayList();
        for(PLO pg : db.Course_PLO_Contribution)
        {
            
            DataList.add(new PieChart.Data(pg.name+" = "+pg.point+"%",pg.point));
        }
        PLOContributionPieChart.setData(DataList);
        
        
        
        
        
        //Course wise Semester Wise plo Line Chart Setup =================================================================
        PLOprobysemLineChart.setAnimated(false);
        PLOprobysemLineChart.getData().clear();
        try {
            //db.Get_Course_Related_PLO_List(course.getCourseID());
            db.Get_Course_Semester_Wise_Avg_Plo_Count(ploname,course.getCourseID());
        } catch (SQLException ex) {
            Logger.getLogger(Course_ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        XYChart.Series<String,Integer> PLObySemesterLineChartAVG = new XYChart.Series<String,Integer>();
        for(Sem_PLO p : db.getSemesterWisePLOAverage())
        {
            String semester = p.getSem()+p.getYear().toString();
            PLObySemesterLineChartAVG.getData().add(new XYChart.Data<String,Integer>(semester,p.getCount()));
        }
        PLObySemesterLineChartAVG.setName("Expected");
        PLOprobysemLineChart.getData().add(PLObySemesterLineChartAVG);
        
        //Semester Wise plo Line Chart Setup (Compare) =================================================================
        try {
            //db.Get_Course_Related_PLO_List(course.getCourseID());
            db.Get_Course_Semester_Wise_Avg_Achieved_Plo_Count(ploname,course.getCourseID());
        
        XYChart.Series<String,Integer> PLObySemesterLineChartAVGAchieved = new XYChart.Series<String,Integer>();
        for(Sem_PLO p : db.getSemesterWisePLOAverageAchieved())
        {
            String semester = p.getSem()+p.getYear().toString();
            PLObySemesterLineChartAVGAchieved.getData().add(new XYChart.Data<String,Integer>(semester,p.getCount()));
        }
        PLObySemesterLineChartAVGAchieved.setName("Actual");
        PLOprobysemLineChart.getData().add(PLObySemesterLineChartAVGAchieved);
        } catch (SQLException ex) {
            Logger.getLogger(Course_ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        try {
            //Verdict - table implementation---------------------------------------------------------------------------
            //Verdict - table implementation---------------------------------------------------------------------------
            //Verdict - table implementation---------------------------------------------------------------------------
            //Verdict - table implementation---------------------------------------------------------------------------
            //Verdict - table implementation---------------------------------------------------------------------------
            
            NumberOfStudentsLable.setText(db.Get_V_table_total_students(course.getCourseID()).toString());
            Snumber = db.Get_V_table_total_students(course.getCourseID());
        } catch (SQLException ex) {Logger.getLogger(Course_ProfileController.class.getName()).log(Level.SEVERE, null, ex);}
        
        try {
            db.Get_V_table_sub_data_one(course.getCourseID());
            db.Get_V_table_sub_data_two(course.getCourseID());
            ArrayList <V_Table> ItemList = new ArrayList <V_Table>();
            
            
            int size = db.partone.size();
            
            for(int i = 0 ; i<size ; i++)
            {
                Float pass =(float) db.partone.get(i).count;
                Float Faile =(float) db.parttwo.get(i).count;
                ItemList.add(new 
                V_Table(db.partone.get(i).name,
                        db.partone.get(i).count,
                        db.parttwo.get(i).count,
                        (pass/Snumber)*100,
                        (Faile/Snumber)*100
                ));          
            }
                            
            
        ObservableList<V_Table> VerdictTable = FXCollections.observableArrayList();
        
        //Setting up column
        COandPLO.setCellValueFactory(new PropertyValueFactory<V_Table,String>("name"));
        SuccessfullyAchieved.setCellValueFactory(new PropertyValueFactory<V_Table,Integer>("passed"));
        SuccessfullyAchieved_P.setCellValueFactory(new PropertyValueFactory<V_Table,Float>("percentPassed"));
        FailedToAchieved.setCellValueFactory(new PropertyValueFactory<V_Table,Integer>("failed"));
        FailedToAchieved_P.setCellValueFactory(new PropertyValueFactory<V_Table,Float>("percentFailed"));

        //Show on Table
        TableView.setItems(VerdictTable);
        
        //Getting Data for Table from ArrayList        
        for(V_Table item : ItemList)
         {
            TableView.getItems().add(item);
          }
        TableView.setItems(VerdictTable);        
   
        } catch (SQLException ex) {
            Logger.getLogger(Course_ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        
    }    


    @FXML
    private void SelectPLOComboBoxOnSelect(ActionEvent event) throws SQLException {
        
        PLOprobysemLineChart.setAnimated(false);
        PLOprobysemLineChart.getData().clear();
        Database db = new Database();
        
        db.getSemesterWisePLOAverage().clear();
        
        db.Get_Course_Semester_Wise_Avg_Plo_Count(SelectPLOComboBox.getValue(),course.getCourseID());
     
        XYChart.Series<String,Integer> PLObySemesterLineChartAVG = new XYChart.Series<String,Integer>();
        for(Sem_PLO p : db.getSemesterWisePLOAverage())
        {
            
            String semester = p.getSem()+p.getYear().toString();
            PLObySemesterLineChartAVG.getData().add(new XYChart.Data<String,Integer>(semester,p.getCount()));
        }
        PLObySemesterLineChartAVG.setName("Expected");
        PLOprobysemLineChart.getData().add(PLObySemesterLineChartAVG);
        
        //Semester Wise plo Line Chart Setup (Compare) =================================================================
        db.getSemesterWisePLOAverageAchieved().clear();
            db.Get_Course_Semester_Wise_Avg_Achieved_Plo_Count(SelectPLOComboBox.getValue(),course.getCourseID());
        
        XYChart.Series<String,Integer> PLObySemesterLineChartAVGAchieved = new XYChart.Series<String,Integer>();
        for(Sem_PLO p : db.getSemesterWisePLOAverageAchieved())
        {
            
            String semester = p.getSem()+p.getYear().toString();
            PLObySemesterLineChartAVGAchieved.getData().add(new XYChart.Data<String,Integer>(semester,p.getCount()));
        }
        PLObySemesterLineChartAVGAchieved.setName("Achieved");
        PLOprobysemLineChart.getData().add(PLObySemesterLineChartAVGAchieved);

    }

    
}
