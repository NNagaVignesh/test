/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Import;

/**
 * 
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/perspicacious?autoReconnect=true&useSSL=true","root","SuperSaiyen.9"))
 * @author Naga Vignesh N
 */
import java.util.*;
import java.sql.*;
import java.io.*;

public class Copy {       
    public static void main(String z[]) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver"); 
     try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination?zeroDateTimeBehavior=convertToNull","root","SuperSaiyen.9")){
         //String query="load data local infile 'C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 5.7\\\\Uploads\\\\TestExcel.csv' into table log_in_det fields terminated by ',' lines terminated by '\\n' (@col1,@col2,@col3,@col4) set uid=@col1,pswd=@col2,r_pswd=@col2";
         String query="load data local infile 'C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 5.7\\\\Uploads\\\\TestExcel.csv' into table det fields terminated by ',' lines terminated by '\\n' (@col1,@col2,@col3,@col4,@col5,@col6,@col7) set child_id=@col1,Name=@col2,dob=@col3,sex=@col4,f_name=@col5,m_name=@col6,vac1=@col7";
   PreparedStatement ps=con.prepareStatement(query);
    //PreparedStatement p1=con.prepareStatement("insert into Det values(?,?,?)");
   /* p1.setString(1,"ABC");
    p1.setInt(2,4);
    p1.setInt(3,9);*/
    
    ps.executeUpdate();
    
     }
     catch(Exception e){
             System.out.println(e);
             }
    }      
} 
