<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<title>Insert title here</title>
</head>
<body>
<%
	if(session.getAttribute("email") == null) 
	{
		response.sendRedirect("index.jsp");
	    //getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
	else{
%>
<div style="position:absolute;font-size:18px; margin-left:300px;width:800px; height: top:100px; background-color:blue;opacity:0.7">

<form action="Addrepository" method="post">
Project Name&nbsp;<input type="text" name="proj_name">
<br />
Add User&nbsp;&nbsp;&nbsp;<input type="text" name="user" />
<br />
<input type="submit" value="Submit" class="btn btn-success"/>
</form>

</div>

<%} %>
</body>
</html>