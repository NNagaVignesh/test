/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Import;

/**
 *
 * @author Naga Vignesh N
 */
import java.util.*;
import java.sql.*;
import java.io.*;

public class Login {
    public String check(String un,String pass){
        System.out.println("la:");
        try{
             Class.forName("com.mysql.jdbc.Driver"); 
            Connection con=DriverManager.getConnection("jdbc:mysql://10.10.226.44:3306/at5?zeroDateTimeBehavior=convertToNull","at5","at5");
            PreparedStatement ps=con.prepareStatement("select pswd from at5.log_in_det where uid=?");
            ps.setString(1,un);
            ResultSet rs=ps.executeQuery();
            rs.next();
            String pswd=rs.getString(1);
            System.out.println("pa:"+pass);
            System.out.println("p:"+pswd);
            if(pswd.equals(pass)){
                System.out.println("if:yes");
                
              return(un); 
            }
            else{
                System.out.println("if:no");
                return("no");
        }
        }
        catch(Exception e){
         System.out.println(e);
         return("no");
        }
     //return("test");   
    }
    public static void main(String args[]){
     Login l =new Login ();
     System.out.println(l.check("user1","user1"));
    }
}
