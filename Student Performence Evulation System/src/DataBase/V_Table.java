/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author rayha
 */
public class V_Table {
    private SimpleStringProperty name;
    private SimpleIntegerProperty passed , failed;
    private SimpleFloatProperty percentPassed, percentFailed;

    public V_Table(String name, Integer passed, Integer failed, Float percentPassed, Float percentFailed) {
        this.name = new SimpleStringProperty(name);
        this.passed = new SimpleIntegerProperty(passed);
        this.failed = new SimpleIntegerProperty(failed);
        this.percentPassed = new SimpleFloatProperty(percentPassed);
        this.percentFailed = new SimpleFloatProperty(percentFailed);
    }
    
    public V_Table() {
        this.name = new SimpleStringProperty("");
        this.passed = new SimpleIntegerProperty(0);
        this.failed = new SimpleIntegerProperty(0);
        this.percentPassed = new SimpleFloatProperty(0);
        this.percentFailed = new SimpleFloatProperty(0);
    }

    public String getName() {
        return name.get();
    }

    public Integer getPassed() {
        return passed.get();
    }

    public Integer getFailed() {
        return failed.get();
    }

    public Float getPercentPassed() {
        return percentPassed.get();
    }

    public Float getPercentFailed() {
        return percentFailed.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setPassed(Integer passed) {
        this.passed = new SimpleIntegerProperty(passed);
    }

    public void setFailed(Integer failed) {
        this.failed = new SimpleIntegerProperty(failed);
    }

    public void setPercentPassed(Float percentPassed) {
        this.percentPassed = new SimpleFloatProperty(percentPassed);
    }

    public void setPercentFailed(Float percentFailed) {
        this.percentFailed = new SimpleFloatProperty(percentFailed);
    }

    @Override
    public String toString() {
        return "V_Table{" + "name=" + name + ", passed=" + passed + ", failed=" + failed + ", percentPassed=" + percentPassed + ", percentFailed=" + percentFailed + '}';
    }
    
    
    
}
