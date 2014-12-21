

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class deleteaccount
 */
@WebServlet("/deleteaccount")
public class deleteaccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteaccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession();
		String a=session.getAttribute("email").toString();
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vignan","root","root");
			Statement st=con.createStatement();
			String sql="delete from register where email='"+a+"'";
			st.executeUpdate(sql);
			request.setAttribute("message", "<center><h1><font color=red>Account Deleted Successfully</font></h1></center>");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
				
		}
		catch(Exception e){
			request.setAttribute("message", "<center><h1><font color=red>Account Deletion UnSuccessfull</font></h1></center>");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
