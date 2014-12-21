<%@page import="java.io.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link href="styles.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vignan</title>
<link type="text/css" href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

</head>

<%!
String name="teja",prjct="",s="";

ResultSet rs=null;
ResultSet rs1=null;
%>
<body>

<%
if(session.getAttribute("email") == null) {
	response.sendRedirect("index.jsp");
    //getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
}else{
%>
<div id="cssmenu" >
<ul style="float:right ">
	<li><a href="index.jsp" class="current"><span></span>Home</a></li>
	<% String a=session.getAttribute("email").toString(); %>
	<li class='has-sub' ><a href="index.jsp" ><span></span><%=a %></a>
		<ul>
			<li class='active'><a href="deleteaccount" ><span></span>Delete Account</a></li>
			<li class='active'><a href="SessionInvalidate"><span></span>Sign Out</a> </li>
		</ul>
	</li>
</ul>
</div> <!-- end of menu -->

<p>${requestScope.message}</p>

<%} %>

<br><br><br>
<!-- <a href="#" class="btn" style="height:28px; width:190px; font-size: 15px;">DeadLines</a><br>
<a href="main_user.jsp" class="btn" style="height:28px; width:190px; font-size: 15px;">View Project</a><br>
<a href="member.jsp" class="btn" style="height:28px; width:190px; font-size: 15px;">View Members</a><br>
<a href="#" class="btn" style="height:28px; width:190px; font-size: 15px;">Shared</a><br>
<a href="Posts.jsp" class="btn" style="height:28px; width:190px; font-size: 15px;">Discussion</a><br> -->

<!-- <form action="deadline.jsp" method="post" >
<input type="submit" value="DeadLines" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:60px; left:80px;font-size: 15px;" /></form><br>
 -->
<form action="main_user.jsp" method="post" >
<input type="submit" value="View Projects" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:100px; left:80px;font-size: 15px;" /></form><br>

<form action="member.jsp" method="post" >
<input type="submit" value="View Members" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:140px; left:80px;font-size: 15px;" /></form><br>

<form action="#" method="post" >
<input type="submit" value="Shared" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:180px; left:80px;font-size: 15px;" /></form><br>

<form action="Posts.jsp" method="post" >
<input type="submit" value="Discussions" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:220px; left:80px;font-size: 15px;" /></form><br>


 <div style="top:100px;margin-top:10px;">
<!-- </div> -->
<%
 

try
{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vignan","root","root");

		Statement st=con.createStatement();
		String query="select * from repository where uname='"+session.getAttribute("email").toString()+"'";
		rs=st.executeQuery(query);
		while(rs.next())
		{
		prjct=rs.getString("rep_name");
		s=s+prjct+"\n";
		String g=rs.getString("email");
		session.setAttribute("guide",g);
		session.setAttribute("user_prjct",prjct);
		%>
		<%out.println("<center>"); %>
		<a href="main_user.jsp" id="logo"><h1><%=prjct%></h1></a></h1>
		<% out.println("</center>");%>

		<% }
}
        

catch(Exception e)
{
    e.printStackTrace();
}%>
  </div>
</body>
</html>