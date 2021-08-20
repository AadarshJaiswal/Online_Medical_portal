package Admin;

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




@WebServlet(name = "RegisterDoctor", urlPatterns = {"/RegisterDoctor"})
public class RegisterDoctor extends HttpServlet {

    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
          PrintWriter out = response.getWriter();
          
            //int id=Integer. parseInt(request. getParameter("doctorid"));
         // String id=request.getParameter("doctorid");
            String f=request.getParameter("name");
             String e=request.getParameter("email");
             String p=request.getParameter("password");
             String c=request.getParameter("category");
             String m=request.getParameter("telephone");
             String a=request.getParameter("address");
             //out.println(f);out.println(m);
             
              
             try{
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical","root","");
                 PreparedStatement ps=con.prepareStatement("insert into doctor(doctor_name,doctor_email,doctor_pass,doctor_cat,doctor_cn,doctor_address)values(?,?,?,?,?,?)");
                 
                // ps.setInt(1,id);
                 ps.setString(1,f);
                 ps.setString(2,e);
                 ps.setString(3,p);
                 ps.setString(4,c);
                 ps.setString(5,m);
                 ps.setString(6,a);
                 
               
                 System.out.println("Successfully registered");
                 int i=ps.executeUpdate();
                 if(i>0)
                 { 
               out.println("<script> alert('Doctor Added Successfully!!!!')</script>\";\n" +
                "<script>window.location='Admin_Home.html'</script>\";");
                 }else
                     {
                     out.println("not registered");}
             }catch (        ClassNotFoundException | SQLException e2)
                        {
                            System.out.println(e2);
                        }
      
          }
    


}

