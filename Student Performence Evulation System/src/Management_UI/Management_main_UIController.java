/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management_UI;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayha
 */
public class Management_main_UIController implements Initializable {

    @FXML
    private BorderPane BorderPane;
    @FXML
    private AnchorPane LeftAnchorPane;
    @FXML
    private Button Dashboard;
    @FXML
    private Button StudentEvaulation;
    @FXML
    private Button CourseEvaulation;
    @FXML
    private Button ProgramDesign;
    @FXML
    private Button LogOut;
    @FXML
    private Button EvaluationButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Parent root;
            
            root = FXMLLoader.load(getClass().getResource("Dashboard_UI.fxml"));
            BorderPane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(Management_main_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void DashboardOnClick(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Dashboard_UI.fxml"));
        BorderPane.setCenter(root);
    }

    @FXML
    private void StudentEvaulationOnClick(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Student_Evulation_UI.fxml"));
        BorderPane.setCenter(root);
    }



    @FXML
    private void CourseEvaulationOnClick(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Course_Evulation_UI.fxml"));
        BorderPane.setCenter(root);
    }

    @FXML
    private void ProgramDesignOnClick(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Program_Management_UI.fxml"));
        BorderPane.setCenter(root);
    }



    @FXML
    private void EvaluationButtonOnClick(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Evulation_UI.fxml"));
        BorderPane.setCenter(root);
    }
    
    @FXML
    private void LogOutOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("/LoginPage.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
       }


}
