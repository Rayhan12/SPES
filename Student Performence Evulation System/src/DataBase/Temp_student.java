/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.time.LocalDate;

/**
 *
 * @author rayha
 */
public class Temp_student {
    private Integer id;
    private String name , email,gender;
    private LocalDate birthday;

    public Temp_student(Integer id, String name, String gender, LocalDate birthday, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
    }
    
    public Temp_student() {
        this.id = 0;
        this.name = "";
        this.email = "";
        this.gender = "";
        this.birthday = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    
    
}
