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
import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;
public class ParseData {
    public static SimpleDateFormat sformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
    public static int expdates[] ={1,45,76,106,274,730,730,2192,3654,5846};
    public static String calcDate(long d,int no){
    
                  Calendar c=Calendar.getInstance();
                 c.setTimeInMillis(d);
                 c.add(Calendar.DAY_OF_YEAR,no);
                 return(sformat.format(c.getTime()));
    }
    public String parser() {
        String parsed = new String();
        StringBuilder sb = new StringBuilder();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://10.10.226.44:3306/at5?zeroDateTimeBehavior=convertToNull","at5","at5");
            PreparedStatement qs=con.prepareStatement("select child_id from at5.det2 order by child_id desc limit 1");
            ResultSet set=qs.executeQuery();set.next();
            int count=set.getInt(1);System.out.println("count"+count);
            PreparedStatement ps = con.prepareStatement("select bd_fname,bd_mname,bd_childdob,bd_childsex,bd_fadd1,bd_fadd2,bd_fadd3,bd_fpin,bd_fcontactno,bd_mcontactno,bd_type from at5.det2");
            ResultSet rs = ps.executeQuery();
            int deadcount=0;
            sb.append("{\"values\":[");int i=0;
            java.util.Date today=new java.util.Date(); 
            java.util.Date d;String days;
            String sexdesc;
            System.out.println("size:"+expdates.length+"\n"+expdates[9]);
            while (rs.next()) {
                //System.out.println(sb); 
                if(rs.getString("bd_type").equalsIgnoreCase("alive")){
                sb.append("{");
                sb.append("\"fname\":\"").append(rs.getString("bd_fname")).append("\",");
                sb.append("\"mname\":\"").append(rs.getString("bd_mname")).append("\",");
                sb.append("\"dob\":\"").append(rs.getTimestamp("bd_childdob")).append("\",");
                sb.append("\"sex\":\"").append(rs.getString("bd_childsex")).append("\",");
                 sb.append("\"sexdesc\":\"");
                if(rs.getString("bd_childsex").equalsIgnoreCase("male")){
                  sb.append("son").append("\",");
                }
                else{
                      sb.append("daughter").append("\",");
                }
                sb.append("\"address\":\"").append(rs.getString("bd_fadd1")).append(" ,").append(rs.getString("bd_fadd2")).append(" ,").append(rs.getString("bd_fadd3")).append(" ,").append(rs.getString("bd_fpin")).append(" .").append("\",");
                sb.append("\"contact_1\":\"").append(rs.getString("bd_fcontactno")).append("\",");
                sb.append("\"contact_2\":\"").append(rs.getString("bd_mcontactno")).append("\",");
                d=rs.getTimestamp("bd_childdob");
                for(int j=0;j<expdates.length;j++){
               // System.out.println("j:"+j);
                sb.append("\"v").append(j+1).append("\":\"").append(calcDate(d.getTime(),expdates[j])).append("\",");
                /*sb.append("\"v2\":\"").append(calcDate(d.getTime(),45)).append("\",");
                sb.append("\"v3\":\"").append(calcDate(d.getTime(),76)).append("\",");
                sb.append("\"v4\":\"").append(calcDate(d.getTime(),106)).append("\",");
                sb.append("\"v5\":\"").append(calcDate(d.getTime(),274)).append("\",");
                sb.append("\"v6\":\"").append(calcDate(d.getTime(),730)).append("\",");
                sb.append("\"v7\":\"").append(calcDate(d.getTime(),730)).append("\",");
                sb.append("\"v8\":\"").append(calcDate(d.getTime(),2192)).append("\",");
                sb.append("\"v9\":\"").append(calcDate(d.getTime(),3654)).append("\",");
                sb.append("\"v10\":\"").append(calcDate(d.getTime(),5846)).append("\"");*/
                
                }
                long di=(today.getTime()-d.getTime());
                int diff=(int)(di/(1000*60*60*24));
                System.out.println(diff);
                int j;
                for(j=0;j<expdates.length;j++){
                    System.out.println("j:"+j+"  "+i);
                  if(diff<expdates[j])
                      break;
                }
                 sb.append("\"estvac\":\"").append(calcDate(d.getTime(),expdates[j])).append("\"");
              // sb.append("m_name:").append(rs.getString("m_name")).append(",");
                
                /*long one_day=(long)86400000;int days;
                java.util.Date d = rs.getTimestamp("dob");
               Calendar cal = Calendar.getInstance();
               cal.setTimeInMillis(d.getTime());            
               cal.add(Calendar.DAY_OF_YEAR,2);
               java.util.Date t = new java.util.Date();                       
                d=cal.getTime();
                long ms=d.getTime()-t.getTime();
                String vac=rs.getString("vac1");
                if(ms<2*one_day&&vac.equals("no")){
                    days=(int)(ms/one_day);
                   sb.append("\"v1\":\"no "+days+"\","); 
                }
                else{
                  sb.append("\"v1\":\"yes\","); 
                }
                cal.add(Calendar.DAY_OF_YEAR,10);              
                d=cal.getTime();
                ms=d.getTime()-t.getTime();
                 if(ms<20*one_day){
                    days=(int)(ms/one_day);
                   sb.append("\"v2\":\"no "+days+"\","); 
                }
                else{
                  sb.append("\"v2\":\"yes\","); 
                }
                 cal.add(Calendar.DAY_OF_YEAR,20);              
                d=cal.getTime();
                ms=d.getTime()-t.getTime();
                 if(ms<60*one_day){
                    days=(int)(ms/one_day);
                   sb.append("\"v3\":\"no "+days+"\""); 
                }
                else{
                  sb.append("\"v3\":\"yes\""); 
                }*/
                 if(i<count-1)
                 sb.append("},");
                 else
                     sb.append("}");
                 i++;
                 System.out.println("count:"+count);
            }
            else{
                    deadcount++;
                    i++;
                    if(i==count){
                        System.out.println("\t\tdead:"+i);
                     sb.delete(sb.length()-1,sb.length());
                    }
                }
              
            }
           // }
           // System.out.println(i);
            sb.append("]}");
            if(deadcount==count)
                sb.delete(0,sb.length());
            System.out.println(sb);
        } catch (Exception e) {
            System.out.println("exception:" + e);
        }
       
        return (sb.toString());
    }

    public static void main(String a[]) {
        ParseData pd = new ParseData();
        pd.parser();
    }
}
