package Admin;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 

        {
	    String m = request.getParameter("email");
            String p = request.getParameter("password");

        ResultSet rs = null;
            response.setContentType("text/html");
          PrintWriter out = response.getWriter();
          

		try {
                    
             Class.forName("com.mysql.jdbc.Driver");
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical","root","");
            
			
                        PreparedStatement ps = conn.prepareStatement("select * from admin where admin_id=? and admin_pass=?");
			ps.setString(1, m);
			ps.setString(2, p);
			rs = ps.executeQuery();
                        HttpSession session=request.getSession();  
        session.setAttribute("email",m);  
                           
			if (rs.next()) {
                            
                          response.sendRedirect("Admin_Home.html"); 
                       } else{
   
               out.println("<html><body><script>alert(\"Username and/or Password incorrect\");\n" +
                "window.location='Admin_Login.html'\n</script></body><html>");
   
                    
			}
		} catch (Exception e) {
			System.out.println("Log In failed: An Exception has occurred! " + e);
		}

	
           
              
                
            }
        }




