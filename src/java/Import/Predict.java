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
import java.time.*;
import java.text.*;

public class Predict {
    public static HashMap<String,ArrayList> hm=new HashMap<String,ArrayList>();
    public static void calcDays(long d,String id,long contact,int days,String msg){
        java.util.Date today=new java.util.Date(); 
         Calendar c=Calendar.getInstance();
         c.setTimeInMillis(d);
         c.add(Calendar.DAY_OF_YEAR,days);
         SimpleDateFormat format = new SimpleDateFormat("dd,MMM yyyy");
         java.util.Date day=c.getTime();
         //msg=msg+" "+format.format(c.getTime())+"\tid:"+id;
         long calc=c.getTimeInMillis()-today.getTime();
         //System.out.println(id+":"+calc);
         addInfo(id,contact,calc,msg);
    
    }
    public static void addInfo(String id,long contact,long calc,String msg){
        //long onems=(long)86400000;
       // System.out.println("info\t"+id);
        if(calc<3*86400000&&calc>=0){
            //System.out.println("info1");
            ArrayList a=new ArrayList();
            a.add(contact);a.add(msg);
            hm.put(id,a);
         }
     
    }
    public void evaluate(){
     try{
       Class.forName("com.mysql.jdbc.Driver");
       Connection con=DriverManager.getConnection("jdbc:mysql://10.10.226.44:3306/at5?zeroDateTimeBehavior=convertToNull","at5","at5");
       PreparedStatement ps=con.prepareStatement("select child_id,bd_type,bd_childdob,bd_childsex,bd_fcontactno from det2");
       ResultSet rs=ps.executeQuery();
       
       java.util.Date d;java.util.Date et=new java.util.Date();  
      String sex;
      //System.out.println(et.getTime());
      while(rs.next()){
          //System.out.println();
          if(rs.getString("bd_childsex").equals("male")){
            sex="son";
          }
          else{
          sex="daughter";
          }
         d=rs.getTimestamp("bd_childdob");
         //System.out.println(rs.getTimestamp("bd_childdob").getTime());INSERT INTO smsdb.sms_send (mobile_no,message,valid_till,inserted_by,inserted_date) VALUES ()
         calcDays(d.getTime(),rs.getString("child_id"),rs.getLong("bd_fcontactno"),1,"Dear Parents,Your baby due for OPV-2 vaccination.Please contact the nearest hospital");
         calcDays(d.getTime(),rs.getString("child_id"),rs.getLong("bd_fcontactno"),45,"Dear Parents,Your baby due for OPV1, Penta-1, IPV1 vaccination.Please contact the nearest hospital");
         calcDays(d.getTime(),rs.getString("child_id"),rs.getLong("bd_fcontactno"),76,"Dear Parents,Your baby due for OPV2, Penta-2 vaccination.Please contact the nearest hospital");
         calcDays(d.getTime(),rs.getString("child_id"),rs.getLong("bd_fcontactno"),106,"Dear Parents,Your baby due for OPV3, Penta-3 vaccination.Please contact the nearest hospital");
         calcDays(d.getTime(),rs.getString("child_id"),rs.getLong("bd_fcontactno"),274,"Dear Parents,Your baby due for Measles-1/MR-1, Vit A-1, JE-1 vaccination.Please contact the nearest hospital");
         calcDays(d.getTime(),rs.getString("child_id"),rs.getLong("bd_fcontactno"),730,"Dear Parents,Your baby due for Measles2/MR-2, Vit A-2, JE-2 vaccination.Please contact the nearest hospital");
         calcDays(d.getTime(),rs.getString("child_id"),rs.getLong("bd_fcontactno"),730,"Dear Parents,Your baby due for OPV Booster, DPT First Booster vaccination.Please contact the nearest hospital");
         calcDays(d.getTime(),rs.getString("child_id"),rs.getLong("bd_fcontactno"),2192,"Dear Parents,Your baby due for DPT Second Booster vaccination.Please contact the nearest hospital");
         calcDays(d.getTime(),rs.getString("child_id"),rs.getLong("bd_fcontactno"),3654,"Dear Parents,Your baby due for TT First Booster vaccination.Please contact the nearest hospital");
         calcDays(d.getTime(),rs.getString("child_id"),rs.getLong("bd_fcontactno"),5846,"Dear Parents,Your baby due for TT Second Booster vaccination.Please contact the nearest hospital");
         //calcDays(d.getTime(),rs.getString("child_id"),rs.getLong("bd_fcontactno"),4);
         
         //c.add(Calendar.DAY_OF_YEAR,1);
         
       }
     }
     catch(Exception e){
       System.out.println(e);
     }
    }
    public String commence_process(){
    try{
       Class.forName("com.mysql.jdbc.Driver");
       Connection con=DriverManager.getConnection("jdbc:mysql://10.10.226.44:3306/smsdb?zeroDateTimeBehavior=convertToNull","at5","at5");
       String user="at5";
        //System.out.println("main");
      Predict p=new Predict();
      p.evaluate();ArrayList a;
      System.out.println("ID\t\t\tContact");
      StringBuffer sent=new StringBuffer("");
      SimpleDateFormat sf=new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
      for(String k:hm.keySet()){
          PreparedStatement ps1=con.prepareStatement("select NOW()");
          ResultSet r=ps1.executeQuery();r.next();
          System.out.println("Date:"+r.getTimestamp(1));
         sent.append("<li class=\"collection-item\">");
        java.sql.Date date=new java.sql.Date(new java.util.Date().getTime()+86400000);  
        //System.out.println(date);
        a=hm.get(k);
        PreparedStatement ps=con.prepareStatement("INSERT INTO smsdb.sms_send (mobile_no,message,valid_till,inserted_by,inserted_date) VALUES (?,?,?,?,?)");
          ps.setString(1,a.get(0).toString());
          sent.append(a.get(0).toString());
          ps.setString(2,a.get(1).toString());
          ps.setDate(3,date);
          ps.setString(4,user);
          ps.setTimestamp(5,r.getTimestamp(1));
          ps.executeUpdate();
          sent.append("</li>");
        System.out.print(k);System.out.print("\t    ---->   \t");
        System.out.println(a.get(0)+"\t-->\t"+a.get(1));
        
      }
      
        
      return(sent.toString());
        }
        catch(ClassNotFoundException | SQLException e){
         System.out.println(e);
         return("Something went wrong!");
        }
       
    }
    public static void main(String arg[]){
        Predict p=new Predict();
       System.out.println(p.commence_process());
        
    }
}

