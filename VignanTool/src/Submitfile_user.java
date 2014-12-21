


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.jdbc.DatabaseMetaData;

/**
 * Servlet implementation class Submitfile_user
 */
@WebServlet("/Submitfile_user")
public class Submitfile_user extends HttpServlet {
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
	
    public Submitfile_user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileName =request.getParameter("fileName");
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
		if(!ServletFileUpload.isMultipartContent(request)){
			throw new ServletException("Content type is not multipart/form-data");
		}
		HttpSession session=request.getSession();
		PrintWriter out = response.getWriter();
		String email=session.getAttribute("email").toString();
		String pname=session.getAttribute("rep_name").toString();
		System.out.println("email ......."+email);
		System.out.println("uname.........."+pname);
		 String update = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		Connection conn=null;
		Statement stmt=null;
		response.setContentType("text/html");  
		System.out.println("1..........");
		
		out.write("<html><head></head><body>");
		System.out.println("1..1..............");
		try {
			System.out.println("in try.....................");
			Class.forName("com.mysql.jdbc.Driver");
			// Class.forName("com.mysql.jdbc.Driver").newInstance();
			 System.out.println("2............");
		      //STEP 3: Open a connection
		      conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/vignan","root","root");
		      System.out.println("3..........................");
		      //STEP 4: Execute a query
		      stmt = (Statement) conn.createStatement();
		      System.out.println("4..................");
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			/*String fname=request.getParameter("fname");
			System.out.println("filename is...."+fname);
			String chfile=request.getParameter("fileName");
			System.out.println("chose file... "+chfile);*/
			//String fpath="D://Upload//";
			String mail=null;
			String rname=null;
			String fpath=null;
			String fname=null;
			String dline=null;
			System.out.println("5...............");
			 DatabaseMetaData md = (DatabaseMetaData) conn.getMetaData();
		      ResultSet rs = md.getTables(null, null, "submit_file", null);
		      
		      if(rs.next())
		      {
		    	  
		      }
		      else
		      {
		    	  String sql;
		    	  sql="create table submit_file(email varchar(40),rep_name varchar(20),file_path varchar(250) ,file_name varchar(20),upld_date datetime);";
		    	  stmt.executeUpdate(sql);
		      }
		     /* HttpSession session=request.getSession(true);
			  session.setAttribute("uname",details[0]);*/
		     // PreparedStatement pre = (PreparedStatement) conn.prepareStatement("insert into upload_file values(?,?,?,?,?,?)");
		     /* pre.setString(1,email);
		      System.out.println("email.."+email);
		      pre.setString(2,pname);
		      System.out.println("panme...."+pname);*/
			int i=0;
			while(fileItemsIterator.hasNext())
			{
				i++;
				FileItem fileItem = fileItemsIterator.next();
				System.out.println("ok..................");
				 if (fileItem.isFormField()) 
				 {
					 
					// fname=fileItem.getString();
					 if(fileItem.getFieldName().equalsIgnoreCase("email"))
					 {
						 System.out.println("email is...."+fileItem.getString());
						 mail=fileItem.getString();
						// pre.setString(1,fileItem.getString());
						 
					 }
					 else if(fileItem.getFieldName().equalsIgnoreCase("repname"))
					 {
						 rname=fileItem.getString();
						 System.out.println("repname is...."+fileItem.getString());
						// pre.setString(2,fileItem.getString());
					 }
					 else if(fileItem.getFieldName().equalsIgnoreCase("fname"))
					 {
						 fname=fileItem.getString();
						 //pre.setString(4,fileItem.getString());
					 }
					  
					 System.out.println("FieldName="+"i"+fileItem.getFieldName());
					 System.out.println("FileName="+"i"+fileItem.getString());
						
				
				 }
				 else
				 {
					 if(fileItem.getFieldName().equalsIgnoreCase("fileName"))
					 {
						 fpath=fileItem.getName();
						 //pre.setString(3,fileItem.getName());
					 }
					 //pre.setString(i,fileItem.getName());
					// fpath=fileItem.getName();
					 System.out.println("FieldName="+"i"+fileItem.getFieldName());
						System.out.println("FileName="+"i"+fileItem.getName());
						System.out.println("ContentType="+fileItem.getContentType());
						System.out.println("Size in bytes="+fileItem.getSize());
						System.out.println("path: "+request.getServletContext().getAttribute("FILES_DIR"));
						File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
						//String relativeWebPath = "";
						//String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
						//File file = new File(absoluteDiskPath);
						//File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fpath);
						System.out.println("Absolute Path at server="+file.getAbsolutePath());
						fileItem.write(file);
						out.write("File "+fileItem.getName()+ " uploaded successfully.");
						out.write("<br>");
						//out.write("<a href=\"UploadDownloadFileServlet?fileName="+fpath+"\">Download "+fileItem.getName()+"</a>");
						//out.write("<a href=\"UploadDownloadFileServlet?fileName="+fpath+"\">Download "+fpath+"</a>");
				 }
				
				
		}
			Statement st = (Statement) conn.createStatement();
			String sql="insert into submit_file values('"+mail+"', '"+ rname+"', '"+fpath+"', '"+ fname+"', '"+ update+"')";
	        st.executeUpdate(sql);
			//pre.setString(6,update);
			 /*DatabaseMetaData md = (DatabaseMetaData) conn.getMetaData();
		      ResultSet rs = md.getTables(null, null, "upload_file", null);
		      
		      if(rs.next())
		      {
		    	  
		      }
		      else
		      {
		    	  String sql;
		    	  sql="create table upload_file(email varchar(40),rep_name varchar(20),file_path varchar(250) ,file_name varchar(20),upld_date datetime,deadline date);";
		    	  stmt.executeUpdate(sql);
		      }
		      HttpSession session=request.getSession(true);
			  session.setAttribute("uname",details[0]);
		      PreparedStatement pre = (PreparedStatement) conn.prepareStatement("insert into upload_file values(?,?,?,?,?,?)");*/
		      //pre.setString(1,fpath);
		     // pre.setString(2,fname);
		      //pre.executeUpdate();
		      
		      //pre.close();
		      rs.close();
		      
		       
		} catch (FileUploadException e) 
		{
			out.write("file Exception in uploading file."+e.getMessage());
		} catch (Exception e) {
			out.write("Exception in uploading file..."+e.getMessage());
		}
		out.write("</body></html>");
	}

}
