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
public class V_table_sub {
    public String name;
    public Integer count;

    public V_table_sub(String name, Integer count) {
        this.name = name;
        this.count = count;
    }
    
    public V_table_sub() {
        this.name = "";
        this.count = 0;
    }    
}
