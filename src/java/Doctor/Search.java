package Doctor;

import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;  
  

@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {  
  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
  int patientId =Integer.parseInt(request.getParameter("cars"));

try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/medical?useSSL=false","root","");  
              
//PreparedStatement ps=con.prepareStatement("select * from patient where patient_id=?");  
//ps.setInt(1,patient_id); 
   
out.print("<head> <link rel=\"stylesheet\" href=\"table.css\"></head><body>\n" +
      
"    <img src=\"images/pmcare.jpg\" width=\"1900\" height=\"400\">\n" +
"    <div class=\"navbar\">\n" +
"  <a  href=\"fetch\">My Details</a>  \n" +
"  <a href=\"Check_appointment.jsp\">My Appointments</a> \n" +
"  <a href=\"DoctorSearch.jsp\">Search Patient</a> \n" +
"  <a href=\"Doctor_description.jsp\">Add Desciption</a>\n" +
"<a href=\"OMP.html\" >Logout</a>\n" +
" \n"+
"</div>\n" ); 

PreparedStatement ps=con.prepareStatement(  "Select patient_id ,patient_name,patient_email,patient_cn,patient_add from patient where patient_id=?");

        
ps.setInt(1,patientId); 


out.print("<table id = customers>");  
out.print("<caption><b><h1><u>Patient Details</u></h1></b></caption>"); 
out.print("<tr>");
        
           out.print(" <th> Id </th>");
           out.print(" <th> Name </th>");
            out.print("<th> Email </th>");
            out.print("<th> Contact No. </th>");
            out.print("<th> Address </th>");
        out.print("</tr>");
  
ResultSet rs=ps.executeQuery();  
            
while(rs.next())  
{  
 out.print("<tr>");
        out.print("<td>" +rs.getInt("patient_id")+ "</td>");
        out.print("<td>" +rs.getString("patient_name")+ "</td>");
        out.print("<td>" +rs.getString("patient_email")+ "</td>");
        out.print("<td>" +rs.getString("patient_cn")+ "</td>");
        out.print("<td>" +rs.getString("patient_add")+ "</td>");
        out.print("</tr>");
}  
  
out.print("</table>");  
out.print("</body></html>");
              
}catch (Exception e2) {e2.printStackTrace();}  
          
finally{out.close();}  
  
}  
}  