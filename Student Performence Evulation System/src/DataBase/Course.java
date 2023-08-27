/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author rayha
 */
public class Course {
    private SimpleStringProperty courseID , depertment ;
    private SimpleIntegerProperty NumberOfPLO ,TotalSectionOffered;
    private SimpleFloatProperty AvgPloCon;

    public Course(String courseID, String depertment, Integer NumberOfPLO, Integer TotalSectionOffered, Float AvgPloCon) {
        this.courseID = new SimpleStringProperty(courseID);
        this.depertment = new SimpleStringProperty(depertment);
        this.NumberOfPLO = new SimpleIntegerProperty(NumberOfPLO);
        this.TotalSectionOffered = new SimpleIntegerProperty(TotalSectionOffered);
        this.AvgPloCon = new SimpleFloatProperty(AvgPloCon);
    }
    
    public Course() {
        this.courseID = new SimpleStringProperty("");
        this.depertment = new SimpleStringProperty("");
        this.NumberOfPLO = new SimpleIntegerProperty(0);
        this.TotalSectionOffered = new SimpleIntegerProperty(0);
        this.AvgPloCon = new SimpleFloatProperty(0);
    }

    public String getCourseID() {
        return courseID.get();
    }

    public String getDepertment() {
        return depertment.get();
    }

    public Integer getNumberOfPLO() {
        return NumberOfPLO.get();
    }

    public Integer getTotalSectionOffered() {
        return TotalSectionOffered.get();
    }

    public Float getAvgPloCon() {
        return AvgPloCon.get();
    }

    public void setCourseID(String courseID) {
        this.courseID = new SimpleStringProperty(courseID);
    }

    public void setDepertment(String depertment) {
        this.depertment = new SimpleStringProperty(depertment);
    }


    public void setNumberOfPLO(Integer NumberOfPLO) {
        this.NumberOfPLO =new SimpleIntegerProperty(NumberOfPLO);
    }

    public void setTotalSectionOffered(Integer TotalSectionOffered) {
        this.TotalSectionOffered = new SimpleIntegerProperty(TotalSectionOffered);
    }

    public void setAvgPloCon(Float AvgPloCon) {
        this.AvgPloCon = new SimpleFloatProperty(AvgPloCon);
    }

    @Override
    public String toString() {
        return "Course{" + "courseID=" + courseID + ", depertment=" + depertment + ", NumberOfPLO=" + NumberOfPLO + ", TotalSectionOffered=" + TotalSectionOffered + ", AvgPloCon=" + AvgPloCon + '}';
    }

    
    
    
    
    
    
}
