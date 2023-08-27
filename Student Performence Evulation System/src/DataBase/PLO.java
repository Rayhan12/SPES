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
public class PLO {
    public String name;
    public float point;

    public PLO(String name, float point) {
        this.name = name;
        this.point = point;
    }
    
    public PLO() {
    this.name = "";
    this.point = (float)0;
    }

    @Override
    public String toString() {
        return name+ ":" + point + " || ";
    }
    

    
    
}
