import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.DatabaseMetaData;

import java.sql.ResultSet;

/**
 * Servlet implementation class AddRepository
 */
@WebServlet("/AddRepository")
public class AddRepository extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRepository() {
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
		PrintWriter out=response.getWriter();	
		
		try 
		{	
				HttpSession session=request.getSession();
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vignan","root","root");
				
				String pname=request.getParameter("pname");
				String user="default";
				String email=session.getAttribute("email").toString();
				
		    	Statement st=con.createStatement();
		    	DatabaseMetaData md = (DatabaseMetaData) con.getMetaData();
		    	
		        ResultSet rs = md.getTables(null, null, "repository", null);
		        
		        if(rs.next())
		        {
		      	 System.out.println("sdfif");
		        }
		        else
		        {
		      	 String sql;
		      	 sql="create table repository (uname varchar(30), email varchar(40), rep_name varchar(30))";
		      	 st.executeUpdate(sql);
		        }
		      
		        String sql="insert into repository values('"+user+"', '"+ email+"', '"+pname+"')";
		        st.executeUpdate(sql);
		        st.close();
		        
		        String grole="guide";
		        Statement st1=con.createStatement();
		        ResultSet rs3=st1.executeQuery("select * from register");
		        
		        int flag=0;
		        while(rs3.next())
		        {
		        	if(rs3.getString(3).equals(email))
		        	{
		        		flag=1;
		        		break;
		        		
		        	}        	
		        	
		        }
		        if(flag==1)
		        {
			        String sql1="update register set role='"+grole+"';";
			    	st1.executeUpdate(sql1);
		        }
		        RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
				rd.include(request,response);
				out.println("<center><h3><font color=Green>Repository Created Successfully!!!</font></h3></center>");
				rs.close();
				rs3.close();
				 
				
       
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	}

}