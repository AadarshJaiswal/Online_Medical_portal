
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

/**
 *
 * @author hadoop
 */
@WebServlet(name = "RemoveDoctor", urlPatterns = {"/RemoveDoctor"})
public class RemoveDoctor extends HttpServlet {

 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
          PrintWriter out = response.getWriter();
          
             String f=request.getParameter("doctor");
           
          try{
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical","root","");
                 PreparedStatement ps=con.prepareStatement("delete from doctor where doctor_id=?");
                 
                ps.setString(1,f);
               
               
                 int i=ps.executeUpdate();
                 if(i>0)
                 { 
                   out.println("<script> alert('Doctor Deleted')</script>\";\n" +
                        "<script>window.location='Admin_Home.html'</script>\";");
                  
                 }else
                     {
                               out.println("<script> alert('Doctor id not found')</script>\";\n" +
                            "<script>window.location='Admin_removedoc.html'</script>\";");
                     }
                 
                   
             }catch (        ClassNotFoundException | SQLException e2) {System.out.println(e2);}
      

    }

 
}
