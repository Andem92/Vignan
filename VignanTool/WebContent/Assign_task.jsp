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
	<script >
	function signout()
	{
		alert("Hiii");
	}
	</script>
</head>
<body>


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
<center>
<br><br>
<form name="regform" action="Submitfile" method="post" enctype="multipart/form-data">
 <input type="text" name="email" value="<%=session.getAttribute("email").toString() %>" readonly><br> 
 <input type="text" name="repname" value="<%=session.getAttribute("rep_name").toString() %>" readonly><br> 
Select File to Upload:<input type="file" name="fileName"><br>
<input type="text" name="fname" placeholder="FileName"><br>
<input type="date" name="deadline" placeholder="dd/mm/yyyy"><br>
<input type="submit" value="Upload">
</form>

<form name="regform1" action="Viewfiles" method="post" enctype="multipart/form-data">
<input type="submit" value="Viewfiles">
</center>
</body>
</html>