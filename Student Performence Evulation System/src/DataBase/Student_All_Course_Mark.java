/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author rayha
 */
public class Student_All_Course_Mark {
   
   private SimpleStringProperty courseName; 
   private SimpleStringProperty plo1;
   private SimpleStringProperty plo2;
   private SimpleStringProperty plo3;
   private SimpleStringProperty plo4;
   private SimpleStringProperty plo5;
   private SimpleStringProperty plo6;
   private SimpleStringProperty plo7;
   private SimpleStringProperty plo8;
   private SimpleStringProperty plo9;
   private SimpleStringProperty plo10;
   private SimpleStringProperty plo11;
   private SimpleStringProperty plo12;

    public Student_All_Course_Mark(String courseName, String plo1, String plo2, String plo3, String plo4, String plo5, String plo6, String plo7, String plo8, String plo9, String plo10, String plo11, String plo12) {
        this.courseName = new SimpleStringProperty(courseName);
        this.plo1 = new SimpleStringProperty(plo1);
        this.plo2 = new SimpleStringProperty(plo2);
        this.plo3 = new SimpleStringProperty(plo3);
        this.plo4 = new SimpleStringProperty(plo4);
        this.plo5 = new SimpleStringProperty(plo5);
        this.plo6 = new SimpleStringProperty(plo6);
        this.plo7 = new SimpleStringProperty(plo7);
        this.plo8 = new SimpleStringProperty(plo8);
        this.plo9 = new SimpleStringProperty(plo9);
        this.plo10 = new SimpleStringProperty(plo10);
        this.plo11 = new SimpleStringProperty(plo11);
        this.plo12 = new SimpleStringProperty(plo12);
    }
    
        public Student_All_Course_Mark() {
        this.courseName = new SimpleStringProperty("");
        this.plo1 = new SimpleStringProperty("");
        this.plo2 = new SimpleStringProperty("");
        this.plo3 = new SimpleStringProperty("");
        this.plo4 = new SimpleStringProperty("");
        this.plo5 = new SimpleStringProperty("");
        this.plo6 = new SimpleStringProperty("");
        this.plo7 = new SimpleStringProperty("");
        this.plo8 = new SimpleStringProperty("");
        this.plo9 = new SimpleStringProperty("");
        this.plo10 = new SimpleStringProperty("");
        this.plo11 = new SimpleStringProperty("");
        this.plo12 = new SimpleStringProperty("");
    }

    public String getCourseName() {
        return courseName.get();
    }

    public String getPlo1() {
        return plo1.get();
    }

    public String getPlo2() {
        return plo2.get();
    }

    public String getPlo3() {
        return plo3.get();
    }

    public String getPlo4() {
        return plo4.get();
    }

    public String getPlo5() {
        return plo5.get();
    }

    public String getPlo6() {
        return plo6.get();
    }

    public String getPlo7() {
        return plo7.get();
    }

    public String getPlo8() {
        return plo8.get();
    }

    public String getPlo9() {
        return plo9.get();
    }

    public String getPlo10() {
        return plo10.get();
    }

    public String getPlo11() {
        return plo11.get();
    }

    public String getPlo12() {
        return plo12.get();
    }

    public void setCourseName(String courseName) {
        this.courseName = new SimpleStringProperty(courseName);
    }

    public void setPlo1(String plo1) {
        this.plo1 =  new SimpleStringProperty(plo1);
    }

    public void setPlo2(String plo2) {
        this.plo2 =  new SimpleStringProperty(plo2);
    }

    public void setPlo3(String plo3) {
        this.plo3 =  new SimpleStringProperty(plo3);
    }

    public void setPlo4(String plo4) {
        this.plo4 =  new SimpleStringProperty(plo4);
    }

    public void setPlo5(String plo5) {
        this.plo5 =  new SimpleStringProperty(plo5);
    }

    public void setPlo6(String plo6) {
        this.plo6 =  new SimpleStringProperty(plo6);
    }

    public void setPlo7(String plo7) {
        this.plo7 =  new SimpleStringProperty(plo7);
    }

    public void setPlo8(String plo8) {
        this.plo8 =  new SimpleStringProperty(plo8);
    }

    public void setPlo9(String plo9) {
        this.plo9 =  new SimpleStringProperty(plo9);
    }

    public void setPlo10(String plo10) {
        this.plo10 =  new SimpleStringProperty(plo10);
    }

    public void setPlo11(String plo11) {
        this.plo11 =  new SimpleStringProperty(plo11);
    }

    public void setPlo12(String plo12) {
        this.plo12 =  new SimpleStringProperty(plo12);
    }

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    @Override
    public String toString() {
        return "Student_All_Course_Mark{" + "courseName=" + courseName + ", plo1=" + plo1 + ", plo2=" + plo2 + ", plo3=" + plo3 + ", plo4=" + plo4 + ", plo5=" + plo5 + ", plo6=" + plo6 + ", plo7=" + plo7 + ", plo8=" + plo8 + ", plo9=" + plo9 + ", plo10=" + plo10 + ", plo11=" + plo11 + ", plo12=" + plo12 + '}';
    }
   
   
}
