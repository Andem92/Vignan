<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="styles.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="images/comment.png" style="background-repeat: no-repeat;">
<br><br>
<a href="index.jsp" class="btn btn-primary" style="margin-left:1000px;" >Home</a>
<center>
	
	<div align="center" class="form-control">
	
	<form action="Postservlet" method="post" >
			<h3><b>Post a comment</b></h3>
			<br>
			<textarea name="post" rows="10" cols="50">
			</textarea><br>
			<input type="submit" value="submit">
	</form>
</center>
</body>
</html>
