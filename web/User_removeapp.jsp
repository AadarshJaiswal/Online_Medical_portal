<%-- 
    Document   : User_removeapp
    Created on : Jul 20, 2021, 12:22:09 AM
    Author     : Jaiswalji
--%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
    <style>
	
	// Navbar code
* {box-sizing: border-box}
body {font-family: Arial, Helvetica, sans-serif;}

.navbar {
  width: 100%;
  background-color: skyblue;
  overflow: auto;
  margin-top: -18;
}

.navbar a {
	
  float: left;
  padding: 12px;
  color: Black;
  text-decoration: none;
  font-size: 17px;
  width: 16.6%; /* Four links of equal widths */
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
table, th, td {
  border: 2px solid black;
  border-collapse: collapse;
  align: left;
  margin-left: 60px;
  margin-top:0px;
}
th, td {
  padding: 24px;
  padding-right: 60px;
  text-align: left;
   
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
  background-color: red;
}


</style>
</head>
<body>
	
<div class="slideshow-container">

<div class="mySlides fade">
  <div class="numbertext">1 / 3</div>
  <img src="images/pmcare.jpg" width="1900px" height="400px">
</div>

<div class="mySlides fade">
  <div class="numbertext">2 / 3</div>
  <img src="images/Face-Mask-Required-at-Clinics.jpg"  width="1900px" height="400px">
</div>

<div class="mySlides fade">
  <div class="numbertext">3 / 3</div>
  <img src="images/1.jpg"  width="1900px" height="400px">
</div>

</div>
<br>

<div style="text-align:center">
  <span class="dot"></span> 
  <span class="dot"></span> 
  <span class="dot"></span> 
</div>


<script>
var slideIndex = 0;
showSlides();
function showSlides() {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1}    
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
  setTimeout(showSlides, 4000); // Change image every 4 seconds
}
</script>

<div class="navbar">
  <a  href="PatientDetail">My Details</a>  
  <a href="User_book.jsp">Book Appointments</a> 
  <a href="User_removeapp.jsp">Cancel Booking</a>
   <a href="User_search.jsp">Search Doctor</a>
<a href="OMP.html" >Logout</a>
 </div>
<h2><center><u>Cancel Appointment</u></center></h2>
<form action="CancelAppointment" method="Post">  
    <table>
  `          <td>
                
                <th style="background-color:#0080ff" >Appointment Id</th>
                <th style="background-color:#0080ff ">Doctor Name</th>
                <th style="background-color:#0080ff ">Appointment Date (yyyy-mm-dd)</th>
            </td>
            <%
    Connection con;
try{
                 
             session=request.getSession();
             int id= Integer.parseInt((String)session.getAttribute("logid"));
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical?useSSL=false","root","");
                String sql;
                 sql= "select * from appointment where patient_id="+id+" order by appointment_date DESC";
                  Statement stmt = con.createStatement();
               ResultSet rs = stmt.executeQuery(sql);
               while(rs.next())
               { 
            %>
            <br><h3>
                <tr>
            <td>
                <input type="radio" name="id" value="<%=rs.getString("appointment_id")%>"/></td>
            
            <td><%=rs.getString("appointment_id")%> <br></td>
             <td><%=rs.getString("doctor_name")%><br> </td>
             <td><%=rs.getString("appointment_date")%> </td></h3>
             </tr>
    </center>
      <%
    }
}
    catch(Exception e)
{
System.out.print(e);
}%>
    </table> <center><input type="submit" value="Delete"/> </center> 
</form> 
</body>
</html> 
