

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.mysql.jdbc.DatabaseMetaData;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Servlet implementation class Postservlet
 */
@WebServlet("/Postservlet")
public class Postservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Postservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int postnum = 0;
		PrintWriter out = response.getWriter();
				Connection conn=null;
				Statement stmt=null;
				response.setContentType("text/html"); 
				HttpSession session=request.getSession();
				String email=(String)session.getAttribute("email");
				String posttime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
				try
				{
			     
			      Class.forName("com.mysql.jdbc.Driver");		
				  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vignan","root","root");
			      stmt = con.createStatement();
			      ResultSet rs;
			      
			    	  String sql1="select Max(id) as id from posts;";
				      ResultSet r=stmt.executeQuery(sql1);
				      if(r.next())
				      {
				    	  postnum=r.getInt(1);
				    	  postnum++;
				      }
				      r.close();			    
			      
			     
			      PreparedStatement pre = con.prepareStatement("insert into posts values(?,?,?,?)");
			      pre.setInt(1,postnum);
			      pre.setString(2,email);
			      pre.setString(3,request.getParameter("post").toString());
			      pre.setString(4,posttime);
			      pre.executeUpdate();
			      
			      pre.close();
			     
			      
			      RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");  
			      rd.include(request,response); 
			      out.print("<table  border=1 cellpadding=8 width=45% align=center cellspacing=8 bgcolor=aqua>");  
			      
			      String sql=" select post,email,date from posts group by id DESC LIMIT 10;";
			      rs=stmt.executeQuery(sql);
			      while(rs.next())
			      {
			    	  System.out.println(rs.getString("post"));
			    	  out.print("<tr><tr>");
			    	  out.print("<tr style=background-color:white;color:black;>");
			    	  out.print("<td align=centercolspan=6><font face=calibri color=darkgray>"+rs.getString("email")+"</font><br><br><font face=verdana>"+rs.getString("post")+"</font><font face=calibri><div align=right><b>Posted on:</b>"+rs.getString("date")+"</div>"+"</font></td>");
	
		          }
			      out.print("<tr><tr>");
			      out.println("</table>");
			      rs.close();
			      
				}
				catch(SQLException se){
				      //Handle errors for JDBC
				      se.printStackTrace();
				} 
				catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
	}


