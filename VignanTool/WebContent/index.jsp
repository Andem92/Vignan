
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Vignan Tool</title>
	<link type="text/css" href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
</head>
<body background="">
<div id="header" class="form-control">
<img src="images/vignan.png" alt="logo" style="position:absolute; margin-top:0px;left:0px; width:150px;">
<script src="bootstrap/js/bootstrap.js"></script> 
<h1><a href="#myModal" class="btn btn-success" data-toggle="modal"  style=" position: absolute; top: 50px; right : 80px ;width:80px; height:25px;"><center>Sign up</center></a></h1>
	<div class="modal hide fade" id="myModal" aria-hidden="true">
	<div class="modal-header">
	<h3>Creative</h3>
	</div>
	<div class="modal-body">
		<form action="register" method="get">
			<label>UserName</label>
			<input type="text" name="uname" placeholder="Username" class="span4"><br>
			
			<label>Password</label>
			<input type="password" name="pwd" placeholder="password" class="span4"><br>
			
			<label>Email</label>
			<input type="email" name="email" placeholder="email" class="span4"><br>
			
			<label>Phone No</label>
			<input type="text" name="phone" placeholder="Your Phone No." class="span4"><br>
			
			<label>Gender</label>
			<input type="radio" name="sex" value="male">Male&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="sex" value="female">Female</label><br>
			<br>
			
			<button type="submit" class="btn btn-success">Register</button>
			<button type="reset" class="btn">Clear</button>
		
		</form>
	</div>
	
	<div class="modal-footer">
	<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
	</div>
</div>
<!-- End of header div tag -->
<br><br><br><br><br>
<div id="center" class="well" style="background-color: black; height:300px;">
<div style="height:300px; width:800px;margin-top:100px; margin-left:140px; margin-top:60px;position:absolute"><p style="  color: white; font-family:Bradley Hand ITC; font-size: 70px;  position:absolute;">Share your Work</p></div>
	<form action="main" method="post" style="color: white; margin-left: 820px; margin-top:50px; position:absolute;">
		<input type="text" name="email" placeholder="Your Email" style="height:38px; width:320px; ">
		<br />
		
		<input type="password" name="pwd" placeholder="Password" style="height:38px; width:320px;" />
		<br />
		
		<%
		
		%>
		<input type="submit" value="SignIn" class="btn btn-success" style="height:38px; width:330px; font-size: 20px;"/>
	</form>
</div> 
</body>
</html>