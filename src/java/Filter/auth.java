/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*; 
import java.io.IOException;  
import java.io.PrintWriter;
/**
 *
 * @author Naga Vignesh N
 */
      

      
    public class auth implements Filter{  
      
    public void init(FilterConfig arg0) throws ServletException {}  
          
    public void doFilter(ServletRequest req, ServletResponse resp,  
            FilterChain chain) throws IOException, ServletException { 
        try{
        HttpServletRequest r=(HttpServletRequest)req;
        HttpServletResponse re=(HttpServletResponse)resp;
        HttpSession hs=r.getSession();
        if(hs.getAttribute("name")!=null){
          System.out.println("\t\tFilter:logged in");
        }
        else
        {
         System.out.println("\t\tFilter:log in failed");
         re.sendRedirect("../index.html");
         
        }
        
        chain.doFilter(req, resp);
        
        }
        catch(Exception e){
            HttpServletResponse re=(HttpServletResponse)resp;
            
         System.out.println(e);
        }
    }  
        public void destroy() {}  
      
    }  