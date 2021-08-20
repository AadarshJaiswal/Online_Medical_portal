
package Doctor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Appointment", urlPatterns = {"/Appointment"})
public class Appointment extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
response.setContentType("text/html");
PrintWriter pw = response.getWriter();
String url = "jdbc:mysql://localhost:3306/medical";
String uid = "root";
String psw = "";
Connection con;
PreparedStatement ps;
ResultSet rs;

 pw.println("<html><head> <link rel=\"stylesheet\" href=\"styles1.css\"></head><body><img src=\"images/1.jpg\" width=\"1900\" height=\"400\"><div class=\"navbar\">\n" +
"  <a  href=\"fetch\">My Details</a>  \n" +
"  <a href=\"Check_appointment\">My Appointments</a> \n" +
"  <a href=\"Doctor_search.html\">Search Patient</a> \n" +
"  <a href=\"Doctor_description.jsp\">Add Desciption</a>\n" +
"<a href=\"OMP.html\" >Logout</a>\n </div><br>" );
              
try {
Class.forName("com.mysql.jdbc.Driver");
con = DriverManager.getConnection(url, uid, psw);
ps = con.prepareStatement("SELECT patient.patient_id, patient.patient_name,patient.patient_email,patient.patient_add, appointment.appointment_date,appointment.appointment_time FROM patient INNER JOIN appointment on patient.patient_id=appointment.appointment_id");
rs = ps.executeQuery();
pw.println("<table align=center text-align=center width=50% height=10% border=2><caption><b><h2>My Appointment</h2></b></caption> <tr>");
pw.println("<td><b>Patient Id</b></td>");
pw.println("<td><b>Name</b></td>");
pw.println("<td><b>Email</b></td>");
pw.println("<td><b>Address</b></td>");
pw.println("<td><b>Date</b></td>");
pw.println("<td><b>Time</b></td>");
pw.println("</tr>");

while (rs.next()) {
Integer id = rs.getInt("patient_id");
String name = rs.getString("patient_name");
String email = rs.getString("patient_email");
String add = rs.getString("patient_add");
String date = rs.getString("appointment_date");
String time = rs.getString("appointment_time");



pw.println("<tr>");
pw.println("<td>" + id + "</td>");
pw.println("<td>" + name + "</td>");
pw.println("<td>" + email + "</td>");
pw.println("<td>" + add + "</td>");
pw.println("<td>" + date + "</td>");
pw.println("<td>" + time + "</td>");
pw.println("</tr>");
}
pw.println("</table>");
con.close();
ps.close();
} catch (SQLException sx) {

} catch (ClassNotFoundException cx) {

}
}
}
        