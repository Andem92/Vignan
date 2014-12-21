
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.mysql.jdbc.DatabaseMetaData;

/**
 * Servlet implementation class ViewSubmissions
 */
@WebServlet("/ViewSubmissions")
public class ViewSubmissions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;
	@Override
	public void init() throws ServletException{
	DiskFileItemFactory fileFactory = new DiskFileItemFactory();
	File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
	fileFactory.setRepository(filesDir);
	this.uploader = new ServletFileUpload(fileFactory);
	}  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewSubmissions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileName =request.getParameter("fileName");
		System.out.println("fileName is in viewwfiles........."+fileName);
		if(fileName == null || fileName.equals("")){
		throw new ServletException("File Name can't be null or empty");
		}
		File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileName);
		if(!file.exists()){
		throw new ServletException("File doesn't exists on server.");
		}
		System.out.println("File location on server::"+file.getAbsolutePath());
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null? mimeType:"application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		ServletOutputStream os       = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read=0;
		while((read = fis.read(bufferData))!= -1){
		os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
		System.out.println("File downloaded at client successfully");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Connection conn=null;
		Statement stmt=null;
		response.setContentType("text/html");  
		System.out.println("1..........");

		out.write("<html><head></head><body>");
		try {
		
			 Class.forName("com.mysql.jdbc.Driver");
		     conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/vignan","root","root");
		     stmt = (Statement) conn.createStatement();
		     DatabaseMetaData md = (DatabaseMetaData) conn.getMetaData();
		     Statement stmt1=conn.createStatement();
		     ResultSet rs1=stmt1.executeQuery("select * from upload_file");
		     while(rs1.next())
		     {
		   	
		   	 out.write("<br><a href=\"ViewSubmissions?fileName="+rs1.getString(3)+"\">Download "+rs1.getString(3)+"</a>");
		   	 
		   	 
		     }
		} 
		catch (Exception e) {
		out.write("Exception in uploading file..."+e.getMessage());
		}
		out.write("</body></html>");
		
	}

}
