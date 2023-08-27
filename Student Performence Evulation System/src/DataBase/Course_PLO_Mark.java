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
public class Course_PLO_Mark {
    
    public String course , ploName;
    public Float mark;

    public Course_PLO_Mark(String course, String ploName, Float mark) {
        this.course = course;
        this.ploName = ploName;
        this.mark = mark;
    }
    public Course_PLO_Mark() {
        this.course = "";
        this.ploName = "";
        this.mark = (float)0;
    }

    @Override
    public String toString() {
        return "Course_PLO_Mark{" + "course=" + course + ", ploName=" + ploName + ", mark=" + mark + '}';
    }
    
}
