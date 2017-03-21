/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Import;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
/**
 *
 * @author Naga Vignesh N
 */
public class Insert_Serv extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter ot = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://10.10.226.44:3306/at5?zeroDateTimeBehavior=convertToNull","at5","at5");
            
            
            int bd_regno=Integer.parseInt(request.getParameter("bd_regno"));
            int div_code=Integer.parseInt(request.getParameter("div_code"));
            java.util.Date date=new java.util.Date();
            Timestamp bd_regdt = new Timestamp(date.getTime());
            int bd_regyr=1900+date.getYear();
            String l=request.getParameter("l");
            Timestamp dob = Timestamp.valueOf(request.getParameter("dob"));
            String sex=request.getParameter("sex");
            int bd_childsext=Integer.parseInt(request.getParameter("bd_childsext"));
            String bd_childname=request.getParameter("bd_childname");
            String bd_fatname=request.getParameter("bd_fatname");
            String bd_motname=request.getParameter("bd_motname");
            String bd_infadd1=request.getParameter("bd_infadd1");
            String bd_infadd2=request.getParameter("bd_infadd2");
            String bd_infadd3=request.getParameter("bd_infadd3");
            int bd_infpin=Integer.parseInt(request.getParameter("bd_infpin"));
            String bd_fadd1=request.getParameter("bd_fadd1");
            String bd_fadd2=request.getParameter("bd_fadd2");
            String bd_fadd3=request.getParameter("bd_fadd3");
            int bd_fpin=Integer.parseInt(request.getParameter("bd_fpin"));
            long fcontact=Long.parseLong(request.getParameter("fcontact"));
            long mcontact=Long.parseLong(request.getParameter("mcontact"));
            PreparedStatement qs=con.prepareStatement("insert into at5.det2 (bd_regno,bd_regyr,div_code,bd_regdt,bd_type,bd_childdob,bd_childsex,bd_childsext,bd_childname,bd_fname,bd_mname,bd_infadd1,bd_infadd2,bd_infadd3,bd_infpin,bd_fadd1,bd_fadd2,bd_fadd3,bd_fpin,bd_fcontactno,bd_mcontactno) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
            qs.setInt(1,bd_regyr);
            qs.setInt(2,bd_regno);
            qs.setInt(3,div_code);
            qs.setTimestamp(4,bd_regdt);
            qs.setString(5,l);
            qs.setTimestamp(6,dob);
            qs.setString(7,sex);
            qs.setInt(8,bd_childsext);
            qs.setString(9,bd_childname);
            qs.setString(10,bd_fatname);
            qs.setString(11,bd_motname);
            qs.setString(12,bd_infadd1);
            qs.setString(13,bd_infadd2);
            qs.setString(14,bd_infadd3);
            qs.setInt(15,bd_infpin);
            qs.setString(16,bd_fadd1);
            qs.setString(17,bd_fadd2);
            qs.setString(18,bd_fadd3);
            qs.setInt(19,bd_fpin);
            qs.setLong(20,fcontact);
            qs.setLong(21,mcontact);
            qs.executeUpdate();
            /* TODO output your page here. You may use following sample code. */
            System.out.println(request.getParameter("dob"));
            out.print("Inserted");
        }
        catch(Exception e){
            out.print("Something went wrong try again");
         System.out.println("form"+e);}
        
            }
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
