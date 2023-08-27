/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rayha
 */
public class UserInfo {
    public String ID;
    public String Password;

    public UserInfo(String ID, String Password) {
        this.ID = ID;
        this.Password = Password;
    }
    
    public UserInfo() {
        this.ID = "";
        this.Password = "";
    }

    @Override
    public String toString() {
        return "UserInfo{" + "ID=" + ID + ", Password=" + Password + '}';
    }
    
    
}
