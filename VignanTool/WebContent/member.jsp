<%@page import="java.io.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Members</title>

<link href="styles.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

<script >
function signout()
{
	alert("Hiii");
}
</script>

<%
if(session.getAttribute("email") == null) {
	response.sendRedirect("index.jsp");
    //getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
}else{
%>

  
<div id="cssmenu" >
<ul style="float:right ">
<li><a href="index.jsp" class="current"><span></span>Home</a></li>
<!-- <li><a href="ViewProjects.jsp"><span></span>View All Repositories</a></li> -->

<% String a=session.getAttribute("email").toString(); %>
<li class='has-sub' ><a href="index.jsp" ><span></span><%=a %></a>
<ul>
<li class='active'><a href="deleteaccount" ><span></span>Delete Account</a></li>
<li class='active'><a href="SessionInvalidate"><span></span>Sign Out</a> </li>
</ul>
</li>
</ul>
</div>
<p>${requestScope.message}</p>

<%} %>


<%!
String prjct="",guide="",user="",email="",uname="";
ResultSet rs=null;
ResultSet rs1=null;
%>
</head>
<body>
<%
    prjct=session.getAttribute("user_prjct").toString();
    guide=session.getAttribute("guide").toString();
    user=session.getAttribute("email").toString();
%>


<br><br>
<form action="deadline.jsp" method="post" >
<input type="submit" value="DeadLines" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:60px; left:80px;font-size: 15px;" /></form><br>

<form action="main_user.jsp" method="post" >
<input type="submit" value="View Projects" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:120px; left:80px;font-size: 15px;" /></form><br>

<form action="member.jsp" method="post" >
<input type="submit" value="View Members" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:180px; left:80px;font-size: 15px;" /></form><br>

<form action="#" method="post" >
<input type="submit" value="Shared" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:240px; left:80px;font-size: 15px;" /></form><br>

<form action="Posts.jsp" method="post" >
<input type="submit" value="Discussions" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:300px; left:80px;font-size: 15px;" /></form><br>
<div style="margin-top:-130px; position: relative">
<center>
<h3>Members</h3>
<%
try
{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vignan","root","root");
        Statement st=con.createStatement();  
        String query="select * from repository where rep_name='"+prjct+"'";
        rs=st.executeQuery(query);
        while(rs.next())
        {
        	email=rs.getString("uname");%>
        	<h4><%=email%></h4>
        	
        <% }
 }
catch(Exception e)
{
    e.printStackTrace();
}%>
</center>
</div>
</body>
</html>