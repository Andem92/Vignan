

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();		
		
		try 
		{	
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vignan","root","root");
			
			String uname=request.getParameter("uname");
			String pwd=request.getParameter("pwd");
			String email=request.getParameter("email");
			String phone=request.getParameter("phone");
			String gender=request.getParameter("sex");
			String role="NULL";
	
			int flag1=0,flag2=0;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from register");
			while(rs.next())
			{
				if(email.equals(rs.getString(3)))
					{
						flag1=1;
						break;
					}	
			
			}
			if(flag1==1)
			{
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
				rd.include(request,response);
				out.println("<center><font color=red>Email Already Exists</font></center>");	
			}
			
			else
			{	
				
				ResultSet rs1=st.executeQuery("select * from register where email='"+email+"'");
				if(rs1.next())
				{
					role="student";
				}
				else
				{
					
					role="guide";
				}
				String sql="insert into register values('"+uname+"', '"+ pwd+"', '"+email+"','"+phone+"','"+gender+"','"+role+"')";
				System.out.println(uname);
				st.executeUpdate(sql);
				
				
				String to=email;
				String subject="Your vignan.com account have been created.";
				String message="Hi,Thank you for signing up.";
				
				String user="yshnv08@gmail.com";
				String pass="winniedpooh@143";
				SendMail.send(to, subject, message, user, pass);
		
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
				rd.include(request,response);
			 
			}
			
			
	} 
	catch (Exception e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
		out.println("Registration UnSuccessful!!!");
	}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
