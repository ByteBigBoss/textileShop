package service.impl;

import GUI.Home;
import service.UserService;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.MySQL;

/**
 *
 * @author sandaruwan
 */
public class UserServiceIMPL implements UserService {

    @Override
    public String login(String username, String password) {

        try {

            ResultSet rs = MySQL.execute("SELECT * FROM `user` WHERE `username`='"+username+"' AND `password`='"+password+"'");
            if (rs.next()) {

                int status = rs.getInt("status");
                if (status == 1) {
                    //Active User
                    String fname = rs.getString("fname");
                    String lname = rs.getString("lname");
                    int userId = rs.getInt("user_type_id");
                    
                    Home h = new Home(fname, lname, userId);
                    h.setVisible(true);
                    return "success";
                } else {
                    //Inactive User
                    return "inactive";
                }

            } else {
                return "invalid";

            }

        } catch (Exception e) {
        }

        return "invalid";
    }

}
