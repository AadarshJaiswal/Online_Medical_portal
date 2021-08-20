package Patient;

import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;  
  

@WebServlet(name = "UserSearch", urlPatterns = {"/UserSearch"})
public class UserSearch extends HttpServlet {  
  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
String p=request.getParameter("doctor");
if(p==" ")
{
out.println("<script> alert('Please Choose Doctor Name First ')</script>\";\n" +
                "<script>window.location='User_search.jsp'</script>\";");
                 
}
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/medical?useSSL=false","root","");  
              
out.print("<head> <link rel=\"stylesheet\" href=\"tableUser.css\"></head><body>\n" +
      
"    <img src=\"images/pmcare.jpg\" width=\"1900\" height=\"400\">\n" +
"    <div class=\"navbar\">\n" +
        " <a  href=\"PatientDetail\">My Details</a>  \n" +
        "  <a href=\"User_book.jsp\">Book Appointments</a> \n" +
        "  <a href=\"User_removeapp.html\">Cancel Booking</a>\n" +
        "  <a href=\"User_search.jsp\">Search Doctor</a>\n" +
        "<a href=\"OMP.html\" >Logout</a>\n" +
" \n"+
"</div>\n" ); 

PreparedStatement ps=con.prepareStatement(  "Select doctor_id ,doctor_name,doctor_cat from doctor where doctor_name=?");

        
ps.setString(1,p); 


out.print("<table id = customers>");  
out.print("<caption><b><h1><u>Doctor Details</u></h1></b></caption>"); 
out.print("<tr>");
        
           out.print(" <th> Id </th>");
           out.print(" <th> Name </th>");
            out.print("<th> Category </th>");
        out.print("</tr>");
  
ResultSet rs=ps.executeQuery();  
            
while(rs.next())  
{  
 out.print("<tr>");
        out.print("<td>" +rs.getInt("doctor_id")+ "</td>");
        out.print("<td>" +rs.getString("doctor_name")+ "</td>");
        out.print("<td>" +rs.getString("doctor_cat")+ "</td>");
        out.print("</tr>");
}  
  
out.print("</table>");  
out.print("</body></html>");
              
}catch (Exception e2) {e2.printStackTrace();}  
          
finally{out.close();}  
  
}  
}  