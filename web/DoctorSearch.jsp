<%-- 
    Document   : DoctorSearch
    Created on : Jul 17, 2021, 3:15:39 PM
    Author     : Jaiswalji
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  
    <head>
    <style>
	
	// Navbar code
* {box-sizing: border-box}
body {font-family: Arial, Helvetica, sans-serif;}

.navbar {
  width: 100%;
  background-color: skyblue;
  overflow: auto;
  margin-top: 0;
}

.navbar a {
	
  float: left;
  padding: 12px;
  color: Black;
  text-decoration: none;
  font-size: 17px;
  width: 20%; /* Four links of equal widths */
  text-align: center;
}

.navbar a:hover {
  background-color: #bfff00;
}

.navbar a.active {
  background-color: skyblue;
}

@media screen and (max-width: 500px) {
  .navbar a {                          
    float: none;
    display: block;
    width: 100%;
    text-align: left;
  }
}

// SlideShow Images Code
* {box-sizing: border-box;}
body {font-family: Verdana, sans-serif;}
.mySlides {display: none;}
img {vertical-align: middle;}

/* Slideshow container */
.slideshow-container {
  max-width: 10px;
  position: relative;
  margin-left: 2px;
  cursor:pointer;
}

/* Caption text */
.text {
  color: #f2f2f2;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 0px;
  width: 20%;
  text-align: center;
}

* {box-sizing: border-box;}
body {font-family: Verdana, sans-serif;}
.mySlides {display: none;}
img {vertical-align: middle;}

/* Slideshow container */
.slideshow-container {
  max-width: 600px;
  position: relative;
  margin-top: 0px;
  margin-left: 4px;
}

/* Caption text */
.text {
  color: #f2f2f2;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}
.active {
  background-color: #717171;
}

/* Fading animation */
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
  .text {font-size: 11px}
}

input[type=text], select {
  width: 25%;
  padding: 12px 20px;
    font-size:19px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 10%;
  background-color: #0080ff;
  color: black;
  font-size:19px;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;

}

input[type=submit]:hover {
  background-color: #45a049;
}


</style>
</head>

    <img src="images/pmcare.jpg" width="1900" height="400">
    <div class="navbar">
  <a  href="fetch">My Details</a>  
  <a href="Check_appointment.jsp">My Appointments</a> 
  <a href="DoctorSearch.jsp">Search Patient</a> 
  <a href="Doctor_description.jsp">Add Desciption</a>
<a href="OMP.html" >Logout</a>
 
</div>
    <br>
<form action="Search" method="Post">  
   <center> <h2>
<label for="cars">Patient Id:</label>
</h2>
   </center>
    <%
    
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/medical?useSSL=false","root","");  
 Statement s=con.createStatement();
 String sql="select patient_id,patient_name from patient";
 ResultSet rs=s.executeQuery(sql);
 %>
<center>
 <select name="cars" id="cars">
     <%
 while(rs.next())
 {
     String id=rs.getString(1);
     String name=rs.getString(2);
    %>
    
    <option value="<%=id%>"><%=id+" :: "+name%></option>

    <%
        }
%>
</select>
</center>
<%
}
catch(Exception e)
{
System.out.print(e);
}
    %>
<br>    <center>
    <input type="submit" value="Search"/> 
</center> 
</form>  
  
  
