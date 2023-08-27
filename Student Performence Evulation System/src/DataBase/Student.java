/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.time.LocalDate;
import java.time.Period;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author rayha
 */
public class Student {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private LocalDate birthday ;
    private SimpleStringProperty gender;
    private SimpleStringProperty department;
    private SimpleIntegerProperty semester;
    private SimpleFloatProperty average_plo;
    private SimpleStringProperty most_plo;
    private SimpleStringProperty lest_pli;

    public Student(Integer id, String name,String gender, String email, LocalDate birthday, String department, Integer semester, Float average_plo, String most_plo, String lest_pli) {
        this.id = new SimpleIntegerProperty(id);
        this.name =  new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.email = new SimpleStringProperty(email);
        this.birthday = birthday;
        this.department = new SimpleStringProperty(department);
        this.semester = new SimpleIntegerProperty(semester);
        this.average_plo = new SimpleFloatProperty(average_plo);
        this.most_plo = new SimpleStringProperty(most_plo);
        this.lest_pli = new SimpleStringProperty(lest_pli);
    }
    
        public Student() {
        this.id = new SimpleIntegerProperty(0);
        this.name =  new SimpleStringProperty("");
        this.gender = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.birthday = birthday;
        this.department = new SimpleStringProperty("");
        this.semester = new SimpleIntegerProperty(0);
        this.average_plo = new SimpleFloatProperty(0);
        this.most_plo = new SimpleStringProperty("");
        this.lest_pli = new SimpleStringProperty("");
    }
        
    public Integer getAge()
    {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", email=" + email + ", birthday=" + birthday + ", gender=" + gender + ", department=" + department + ", semester=" + semester + ", average_plo=" + average_plo + ", most_plo=" + most_plo + ", lest_pli=" + lest_pli + '}';
    }



    public Integer getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getEmail() {
        return email.get();
    }
    public String getGender() {
        return gender.get();
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getDepartment() {
        return department.get();
    }

    public Integer getSemester() {
        return semester.get();
    }

    public Float getAverage_plo() {
        return average_plo.get();
    }

    public String getMost_plo() {
        return most_plo.get();
    }

    public String getLest_pli() {
        return lest_pli.get();
    }

    public void setId(Integer id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setEmail(String email) {
        this.email =new SimpleStringProperty(email);
    }
    
    public void setGender(String gender) {
        this.gender =new SimpleStringProperty(gender);
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setDepartment(String department) {
        this.department =new SimpleStringProperty(department);
    }

    public void setSemester(Integer semester) {
        this.semester =new SimpleIntegerProperty(semester);
    }

    public void setAverage_plo(Float average_plo) {
        this.average_plo = new SimpleFloatProperty(average_plo);
    }

    public void setMost_plo(String most_plo) {
        this.most_plo = new SimpleStringProperty(most_plo);
    }

    public void setLest_pli(String lest_pli) {
            this.lest_pli = new SimpleStringProperty(lest_pli);
    }
    
    
    
    
    
}
