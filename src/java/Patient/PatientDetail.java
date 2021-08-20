 package Patient;
 
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

@WebServlet(name = "PatientDetail", urlPatterns = {"/PatientDetail"})
public class PatientDetail extends HttpServlet {
 ResultSet rs;
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html");  
                  PrintWriter out = response.getWriter();
               
                 
             try{
                 HttpSession session=request.getSession(false);  
              if(session!=null){  
            String email=(String)session.getAttribute("email");  
          Class.forName("com.mysql.jdbc.Driver");
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical?useSSL=false","root","");
                // String sql;
              PreparedStatement ps = con.prepareStatement("select * from patient where patient_email=?");
		ps.setString(1, email);
              rs=ps.executeQuery();
              
                 out.println("<html><head> <link rel=\"stylesheet\" href=\"styles2.css\"> <link rel=\"stylesheet\" href=\"table.css\"></head><body><img src=\"images/1.jpg\" width=\"1900\" height=\"400\"><div class=\"navbar\">\n" +
        "  <a  href=\"PatientDetail\">My Details</a>  \n" +
        "  <a href=\"User_book.jsp\">Book Appointments</a> \n" +
        "   \n" +
        "  <a href=\"User_removeapp.jsp\">Cancel Booking</a>\n" +
        "  <a href=\"User_search.jsp\">Search Doctor</a>\n" +
        "<a href=\"OMP.html\" >Logout</a>\n" +
        " \n" +
         " \n</div>" );
out.println("<table id = doctors align=center text-align=center width=10% height=10% border=2><caption><b><h1><u>My Details</u></h1></b></caption> <tr>");

while(rs.next()){
 out.println("<tr><th>Id</th><td>"+ rs.getString("patient_id")  +"</td></tr><tr><th>Name</th><td>"+ rs.getString("patient_name")  +"</td></tr><tr><th>Email</th><td>"+ rs.getString("patient_email") +"</td></tr><tr><th>Password</th><td>"+ rs.getString("patient_pass")  +"</td></tr><tr><th>Contact No.</th><td>"+ rs.getString("patient_cn")+"</td></tr><tr><th>Address</th><td>"+ rs.getString("patient_add")+"</td></tr>");
                   
         
                   out.print("</table></body></html>");
               
            
                   
                 } }
      
          } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
}}
