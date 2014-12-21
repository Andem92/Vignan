

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

/**
 * Servlet implementation class main
 */
@WebServlet("/main")
public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public main() {
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
		HttpSession session=request.getSession();
		String role="NULL";
		
		
		int flag=0,flag1=0;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vignan","root","root");
			String email=request.getParameter("email");
			String pswd=request.getParameter("pwd");
			String uname="";
			Statement st=con.createStatement();        
			ResultSet rs=st.executeQuery("select * from register");
 
		
					
		    while(rs.next())
	    	{
	    		if(email.equals(rs.getString(3))&&pswd.equals(rs.getString(2)))
	    		{
	    		uname=rs.getString(1);
	    		flag=1;
	    		break;
	    		}
	    	}
			
			if(flag==1)
			{
				session.setAttribute("email", new String(email));
				if(rs.getString("role").equals("guide"))
				{
					RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
					rd.include(request,response);
					
				}
				if(rs.getString("role").equals("student"))
				{
					RequestDispatcher rd=request.getRequestDispatcher("welcome_user.jsp");
					rd.include(request,response);
					
				}
			}
			else
			{
					
				RequestDispatcher rd=request.getRequestDispatcher("Hello.jsp");
				rd.include(request,response);
				out.println("<center><font color=red><h3>Invalid Username and Password</h3></font></center>");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
