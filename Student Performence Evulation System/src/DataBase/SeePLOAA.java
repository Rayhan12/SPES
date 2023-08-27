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
public class SeePLOAA {
    public String plo;
    public Float achieved , achievable;

    public SeePLOAA(String plo,  Float achievable, Float achieved) {
        this.plo = plo;
        this.achieved = achieved;
        this.achievable = achievable;
    }
    
        public SeePLOAA() {
        this.plo = "";
        this.achieved = (float)0;
        this.achievable = (float)0;
    }

    @Override
    public String toString() {
        return "SeePLOAA{" + "plo=" + plo + ", achieved=" + achieved + ", achievable=" + achievable + '}';
    }
        
        
    
    
}
