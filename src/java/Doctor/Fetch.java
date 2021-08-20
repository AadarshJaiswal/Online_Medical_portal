 package Doctor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/fetch")
@WebServlet(name = "fetch", urlPatterns = {"/fetch"})
public class Fetch extends HttpServlet {
    
    Connection con;
    PreparedStatement ps;
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
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical?useSSL=false","root","");
                // String sql;
               ps = con.prepareStatement("select * from doctor where doctor_email=?");
		ps.setString(1, email);
  
               rs=ps.executeQuery();
             out.println("<html><head>  <link rel=\"stylesheet\" href=\"table.css\"></head><body><img src=\"images/1.jpg\" width=\"1900\" height=\"400\"><div class=\"navbar\">\n" +
        "  <a  href=\"fetch\">My Details</a>  \n" +
        "  <a href=\"Check_appointment.jsp\">My Appointments</a> \n" +
        "  <a href=\"DoctorSearch.jsp\">Search Patient</a> \n" +
        "  <a href=\"Doctor_description.jsp\">Add Desciption</a>\n" +
        "<a href=\"OMP.html\" >Logout</a>\n </div>") ;
//        " \n" +
//         " \n" +
//        "</div><h1><center><u>My Details</u></center></h1><table>");
             out.println("<table id = doctors align=center text-align=center width=10% height=10% border=2><caption><b><h1><u>My Details</u></h1></b></caption> <tr>");

        while(rs.next()){
            out.println("<tr><th><b>Id</b></th> <td> "+ rs.getString("doctor_id")+"</td></tr>");
out.println("<tr><th><b>Name</b></th> <td>"+ rs.getString("doctor_name")  +"</td></tr>");
out.println("<tr><th><b>Email</b></th> <td>"+ rs.getString("doctor_email") +"</td></tr>");
out.println("<tr><th><b>Password</b></th> <td>"+ rs.getString("doctor_pass") +"</td></tr>");
out.println("<tr><th><b>Contact No.</b></th> <td>"+ rs.getString("doctor_cn") +"</td></tr>");
out.println("<tr><th><b>Address</b></th> <td>"+ rs.getString("doctor_address") +"</td></tr>");

            
            
//        out.println("<tr><th>Id</th><td>"+ rs.getString("doctor_id")  +"</td></tr><tr><th>Name</th><td>"+ rs.getString("doctor_name")  +"</td></tr><tr><th>Email</th><td>"+ rs.getString("doctor_email") +"</td></tr><tr><th>Password</th><td>"+ rs.getString("doctor_pass")  +"</td></tr><tr><th>Contact No.</th><td>"+ rs.getString("doctor_cn")+"</td></tr><tr><th>Address</th><td>"+ rs.getString("doctor_address")+"</td></tr>");
                   
         
                   out.print("</table></body></html>");
            }
              }
      
          } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Fetch.class.getName()).log(Level.SEVERE, null, ex);
        }}}
        
        
        
    

