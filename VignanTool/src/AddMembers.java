import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.DatabaseMetaData;

/**
 * Servlet implementation class AddMembers
 */
@WebServlet("/AddMembers")
public class AddMembers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMembers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();	
		System.out.println("had");
				
				try 
				{	
					HttpSession session=request.getSession();
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vignan","root","root");
					
					String pname=session.getAttribute("rep_name").toString();
					String user=request.getParameter("uname");
					String email=session.getAttribute("email").toString();
					
					session.setAttribute("rep_name", pname);
					Statement st=con.createStatement();
			    	DatabaseMetaData md = (DatabaseMetaData) con.getMetaData();
			    	ResultSet rs = md.getTables(null, null, "repository", null);
			        
			        if(rs.next())
			        {
			        	 System.out.println("Hi");
			        }
			        else
			        {
				      	 String sql;
				      	 sql="create table repository (uname varchar(30), email varchar(40), rep_name varchar(30))";
				      	 st.executeUpdate(sql);
			        }
			        RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
					rd.include(request,response);
			     
			        ResultSet rs1=st.executeQuery("select uname from repository");
			        String srole="student";
			        ResultSet rs2=st.executeQuery("select * from register");
			        
			        while(rs2.next())
			        {
			        	String sql="update register set role='"+srole+"' where email='"+user+"';";
			        	st.executeUpdate(sql);
			        }
			        
			        String grole="guide";
			        ResultSet rs3=st.executeQuery("select * from register");
			        while(rs3.next())
			        {
			        	String sql="update register set role='"+grole+"' where email='"+email+"';";
			        	st.executeUpdate(sql);
			        }
			        
			        int flag=0;
			        while(rs1.next())
			        {
			        	System.out.println("default..........."+rs1.getString("uname"));
			        	if(rs1.getString(1).equalsIgnoreCase("default"))
			        	{
			        		String sql=" update repository set uname='"+user+"' where email='"+email+"' and rep_name='"+pname+"';";
					        st.executeUpdate(sql);
					        flag=1;
							out.println("<center><h4><font color=Green>Members Added Successfully!!!</font></h4></center>");
			        	}
			        	
			        }
			        
			        if(flag==0)
		        	{
		        		 String sql="insert into repository values('"+user+"', '"+ email+"', '"+pname+"')";
		        	        st.executeUpdate(sql);
		        	        
		    				out.println("<center><h4><font color=Green>Members Added Successfully!!!</font></h4></center>");
		        	}
			        
			       
					
			       
				}
				catch (Exception e) {
						
					}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

}