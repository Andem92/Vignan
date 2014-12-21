<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link href="styles.css" rel="stylesheet" type="text/css" />
	<link type="text/css" href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

</head>
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
</div>
<br><br><br>

 <div id="form-control" style=" position:absolute; width:100px; height:200px; margin-left: 600px; opacity:0.8;">
 <center>
 <%String s=session.getAttribute("rep_name").toString(); %>
 <form action="AddMembers" style="font-size:15px; opacity:0.8 ;border:black; width:550px;height:200px; background-color:ash;" method="get" >
 <h4>Add Members</h4>
 Enter Users:&nbsp;&nbsp;
<input type="text" name="uname"/>
<input type="submit" class="btn btn-primary" style="margin-top:-10px;" value="Add"/>
</form><br>
</center>
 </div>
 
<form action="ViewSubmissions" method="post" enctype="multipart/form-data">
<input type="submit" value="View Submissions" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:100px; left:80px;font-size: 15px;"/>
</form>

<form action="Assign_task.jsp">
<input type="submit" value="Assign Task" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:160px; left:80px;font-size: 15px;">
</form>

<form action="ViewMembers" method="post">
<input type="submit" value="View Members" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:220px; left:80px;font-size: 15px;">
</form>

<form action="Posts.jsp">
<input type="submit" value="Discussions" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:280px; left:80px;font-size: 15px;" />
</form><br>
<%} %>
</body>
</html>