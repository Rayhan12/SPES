/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

/**
 *
 * @author rayha
 */
public class SemYear {
    public String semester;
    public Integer year;

    public SemYear( Integer year , String semester) {
        this.semester = semester;
        this.year = year;
    }
    
    public SemYear() {
        this.semester = "";
        this.year = 0;
    }

    @Override
    public String toString() {
        return "SemYear{" + "semester=" + semester + ", year=" + year + '}';
    }
    
    
}
