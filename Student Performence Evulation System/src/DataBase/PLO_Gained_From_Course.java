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
public class PLO_Gained_From_Course {
    public String course;
    public Float cper , pper;

    public PLO_Gained_From_Course(String course, Float cper, Float pper) {
        this.course = course;
        this.cper = cper;
        this.pper = pper;
    }
    
        public PLO_Gained_From_Course() {
        this.course = "";
        this.cper =(float) 0;
        this.pper =(float) 0;
    }

    @Override
    public String toString() {
        return "PLO_Gained_From_Course{" + "course=" + course + ", cper=" + cper + ", pper=" + pper + '}';
    }
    
    
}
