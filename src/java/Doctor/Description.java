package Doctor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Description", urlPatterns = {"/Description"})
public class Description extends HttpServlet {

    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
          PrintWriter out = response.getWriter();
          
          
          
          int patientId =Integer.parseInt(request.getParameter("first"));
             String p=request.getParameter("treatment");
             String c=request.getParameter("note");
             out.println(patientId);
               out.println(p);
             
  out.println("<html><head> <link rel=\"stylesheet\" href=\"styles1.css\"></head><body><img src=\"images/1.jpg\" width=\"1900\" height=\"400\"><div class=\"navbar\">\n" +
"  <a  href=\"fetch\">My Details</a>  \n" +
"  <a href=\"Check_appointment.jsp\">My Appointments</a> \n" +
"  <a href=\"DoctorSearch.jsp\">Search Patient</a> \n" +
"  <a href=\"Doctor_description.jsp\">Add Desciption</a>\n" +
"<a href=\"OMP.html\" >Logout</a>\n" );
 
             try{
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical","root","");
                 Statement st=con.createStatement();
                 ResultSet rs=st.executeQuery("select patient_treatment,patient_note from patient where patient_id="+patientId+"");
                 String o_treatment = " ",o_note=" ";
                 while(rs.next())
                {
                     o_treatment=rs.getString(1);
                     o_note=rs.getString(2);
                }
                 PreparedStatement ps=con.prepareStatement("update patient set patient_treatment = ?,patient_note = ? where patient_id = ?");
               
                p=p+"   "+o_treatment;
                c=c+"    "+o_note;
                ps.setString(1,p);
                ps.setString(2,c); 
                ps.setInt(3,patientId);
                
                int i=ps.executeUpdate();
                 if(i>0)
                 { 
           out.println("<script> alert('Description Added Successfully!!!!')</script>\";\n" +
                "<script>window.location='Doctor_description.jsp'</script>\";");
                 }else
                     {
                     out.println("not registered");}
                 
                   
             }catch (ClassNotFoundException | SQLException e2) {System.out.println(e2);}
      
          }
    


}

