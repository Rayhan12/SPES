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
public class Sem_PLO {
    
    private String sem ;
     private Integer year , count;

    public Sem_PLO(String sem, Integer year, Integer count) {
        this.sem = sem;
        this.year = year;
        this.count = count;
    }
    public Sem_PLO() {
        this.sem = "";
        this.year = 0;
        this.count = 0;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "sem=" + sem + ", year=" + year + ", count=" + count ;
    }

    
}
