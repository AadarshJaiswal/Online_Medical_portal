/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patient;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hadoop
 */
@WebServlet(name = "Book", urlPatterns = {"/Book"})
public class Book extends HttpServlet {

 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
          PrintWriter out = response.getWriter();
          Random rand = new Random();
             int f= rand.nextInt(10000);
             //String cat=request.getParameter("cat");
             String e=request.getParameter("doctor_name");
             String p=request.getParameter("appoint");
             HttpSession session=request.getSession();
             int id= Integer.parseInt((String)session.getAttribute("logid"));
          try{
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical","root","");
                 PreparedStatement ps=con.prepareStatement("insert into appointment(appointment_id,doctor_name,appointment_date,patient_id)values(?,?,?,?)");
                 
                 ps.setInt(1, f);
                 ps.setString(2,e);
//                 ps.setString(3,cat);
                 ps.setString(3,p);
                 ps.setInt(4,id);
                 System.out.println("Successfully registered");
                 int i=ps.executeUpdate();
                 if(i>0)
                 { 
                   out.println(
"<script>alert('Appointment Successfully Done..')\n" +
"window.location='User_book.jsp'</script>\";");
                  
                 }else
                     {
                     out.println("not registered");}
                 
                   
             }catch (        ClassNotFoundException | SQLException e2) {System.out.println(e2);}
      

    }

 
}
