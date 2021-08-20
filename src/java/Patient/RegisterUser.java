package Patient;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet(name = "RegisterUser", urlPatterns = {"/RegisterUser"})
public class RegisterUser extends HttpServlet {

    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
          PrintWriter out = response.getWriter();
          
          
          
            String f=request.getParameter("Name");
             String e=request.getParameter("Email");
             String p=request.getParameter("Password");
             String m=request.getParameter("Mobile");
             String a=request.getParameter("Address");
             out.println(f);out.println(m);
             
              
             try{
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical","root","");
                 PreparedStatement ps=con.prepareStatement("insert into patient(patient_name,patient_email,patient_pass,patient_cn,patient_add)values(?,?,?,?,?)");
                 
                 //ps.setInt(1,14);
                 ps.setString(1,f);
                 ps.setString(2,e);
                 ps.setString(3,p);
                 ps.setString(4,m);
                 ps.setString(5,a);
                 
               
                 System.out.println("Successfully registered");
                 int i=ps.executeUpdate();
                 if(i>0)
                 { 
                   out.println(f);out.println(m);
                     System.out.println("Successfully registered");
                 response.sendRedirect("User_Login.html");  
                 }else
                     {
                     out.println("not registered");}
                 
                   
             }catch (        ClassNotFoundException | SQLException e2) {System.out.println(e2);}
      
          }
    


}

