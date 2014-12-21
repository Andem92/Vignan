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
</div> <!-- end of menu -->
<h2 align="center">Welcome <%=a %></h2>
<p>${requestScope.message}</p>

<%} %>


<script src="bootstrap/js/bootstrap.js"></script> 
<h1><a href="#myModal" class="btn btn-success" data-toggle="modal"  style=" position: absolute; top: 120px; left : 80px ;width:180px; height:25px;"><center>Add Repository</center></a></h1>
<div class="modal hide fade" id="myModal" aria-hidden="true">
<div class="modal-header">
<h3>Add Repository</h3>
</div>
<div class="modal-body">
<form action="AddRepository" method="post">
<label>Project Name</label>
<input type="text" name="pname" placeholder="Enter Project Name" class="span4"><br>
<!-- <label>Email</label>
<input type="text" name="email" placeholder="Enter User Mail Id" class="span4"><br>
 -->
<br>
<button type="submit" class="btn btn-success">Add</button>
<button type="reset" class="btn">Clear</button>

</form>
</div>

<div class="modal-footer">
<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
</div>
</div>


<form action="ViewProjects" method="post" >
<input type="submit" value="View Projects" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:190px; left:80px;font-size: 15px;" /></form><br>


<form action="Posts.jsp">
<input type="submit" value="Discussions" class="btn btn-success" style=" position: absolute;height:35px; width:205px; top:260px; left:80px;font-size: 15px;" /></form><br>
</body>
</html>