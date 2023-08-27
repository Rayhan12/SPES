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
public class Course_PLO {
    
    private String PLO;
    private float achi;

    public Course_PLO(String PLO, float achi) {
        this.PLO = PLO;
        this.achi = achi;
    }
    
    public Course_PLO() {
        this.PLO = "";
        this.achi = 0;
    }

    public String getPLO() {
        return PLO;
    }

    public void setPLO(String PLO) {
        this.PLO = PLO;
    }

    public float getAchi() {
        return achi;
    }

    public void setAchi(float achi) {
        this.achi = achi;
    }
    
    
}
