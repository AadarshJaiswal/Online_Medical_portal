 package Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/fetch")
@WebServlet(name = "FetchPatient", urlPatterns = {"/FetchPatient"})
public class FetchPatient extends HttpServlet {
    
    Connection con;
    ResultSet rs;
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html");  
                  PrintWriter out = response.getWriter();
                
             try{
                 
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical?useSSL=false","root","");
                String sql;
                 sql= "select * from patient inner join appointment on patient.patient_id=appointment.patient_id group by appointment.patient_id ";
//                 FROM Orders
//INNER JOIN Customers ON Orders.CustomerID=Customers.CustomerID;
                  Statement stmt = con.createStatement();
               rs = stmt.executeQuery(sql);
               
          out.print("<html><head> <link rel=\"stylesheet\" href=\"table.css\"></head><body><img src=\"images/1.jpg\" width=\"1900\" height=\"400\"><div class=\"navbar\">\n" +
"  <a  href=\"Admin_adddoctor.html\">Add Doctor</a>  \n" +
"  <a href=\"FetchDoctor\">View Doctor</a> \n" +
"  <a href=\"FetchPatient\">View Patient</a> \n" +
"  <a href=\"Admin_removedoc.jsp\">Remove Doctor</a>\n" +
"<a href=\"OMP.html\" >Logout</a>\n" +
" \n" +
"</div><table id = customers>");  
out.print("<caption><b><h1><u>Patient Details</u></h1></b></caption>"); 
out.print("<tr>");
            out.print(" <th> S No. </th>");
            out.print(" <th> Id </th>");
            out.print(" <th> Name </th>");
            out.print("<th> Email </th>");
            out.print("<th> Contact No. </th>");
            out.print("<th> Address </th>");
            out.print("<th> Operate By </th>");
        out.print("</tr>");
        int count=1;
while(rs.next())  
{  
 out.print("<tr>");
        out.print("<td>"+count+"</td>");
        out.print("<td>" +rs.getInt("patient_id")+ "</td>");
        out.print("<td>" +rs.getString("patient_name")+ "</td>");
        out.print("<td>" +rs.getString("patient_email")+ "</td>");
        out.print("<td>" +rs.getString("patient_cn")+ "</td>");
        out.print("<td>" +rs.getString("patient_add")+ "</td>");
        if(rs.getString("doctor_name")!=null)
        {
        out.print("<td>" +rs.getString("doctor_name")+"</td>");
        }
        else
        {
        out.print("<td> NIL </td>");
        }
        out.print("</tr>");
        count++;
}  
  
out.print("</table>");  
out.print("</body></html>");
               
       }catch (SQLException | ClassNotFoundException ex) {
      
          } 
            
        }
}

        
        
        
    

