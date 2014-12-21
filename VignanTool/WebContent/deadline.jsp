<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Uploading_task</title>
<link href="styles.css" rel="stylesheet" type="text/css" />
	<link type="text/css" href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script >
	function signout()
	{
		alert("Hiii");
	}
	</script>
</head>
<body>
<!-- <div class="center">
 <ul>
<li><a href="deadline.jsp">DeadLines</a></li>
<li><a href="View_proj.jsp">Assigned prjct</a></li>
<li><a href="member.jsp">Members</a></li>
<li><a href="">Shared</a></li>
<li><a href="">Discussion</a></li>
<li><a href="">Logout</a></li>

</ul>
</div>
 -->

<%
if(session.getAttribute("email") == null) {
	response.sendRedirect("index.jsp");
    
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
<h2 align="center">Welcome <%=a %></h2>
<p>${requestScope.message}</p>

<%} %>


<form action="main_user.jsp" method="post" >
<input type="submit" value="View Projects" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:60px; left:80px;font-size: 15px;" /></form><br>


<form action="deadline.jsp" method="post" >
<input type="submit" value="DeadLines" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:100px; left:80px;font-size: 15px;" /></form><br>

<form action="member.jsp" method="post" >
<input type="submit" value="View Members" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:140px; left:80px;font-size: 15px;" /></form><br>

<form action="#" method="post" >
<input type="submit" value="Shared" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:180px; left:80px;font-size: 15px;" /></form><br>

<form action="Posts.jsp" method="post" >
<input type="submit" value="Discussions" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:220px; left:80px;font-size: 15px;" /></form><br>


<form name="regform" action="Submitfile_user" method="post" enctype="multipart/form-data">
 <input type="text" name="email" value="<%=session.getAttribute("email").toString() %>" readonly><br> 
 <input type="text" name="repname" value="<%=session.getAttribute("user_prjct").toString() %>" readonly><br> 
 Select File to Upload:<input type="file" name="fileName"><br>
<input type="text" name="fname" placeholder="FileName"><br>
<!-- <input type="date" name="uplddate" placeholder="Curdate"><br> -->
<input type="submit" value="Upload">
</form>
 
</body>
</html>>