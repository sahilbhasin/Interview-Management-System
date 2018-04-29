package interviewmgmtsystem;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class resumeupload
 */
@WebServlet("/resumeupload")
public class resumeupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resumeupload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,  HttpServletResponse response)       throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
 
        final Part filePart = request.getPart("file");
        String rId = request.getParameter("resumeID");
 
        InputStream pdfFileBytes = null;
        final PrintWriter writer = response.getWriter();
 
        try {
 
          if (!filePart.getContentType().equals("resume.pdf"))
            {
                       writer.println("<br/> Invalid File");
                       return;
            }
 
           else if (filePart.getSize()>1048576 ) { //2mb
               {
              writer.println("<br/> File size too big");
              return;
               }
           }
 
            pdfFileBytes = filePart.getInputStream();  // to get the body of the request as binary data
 
            final byte[] bytes = new byte[pdfFileBytes.available()];
             pdfFileBytes.read(bytes);  //Storing the binary data in bytes array.
 
            Connection  con=null;
             Statement stmt=null;
 
               try {
                     Class.forName("com.mysql.jdbc.Driver");
                     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/csi518_interview_mgmt","team5","icsi518");
                  } catch (Exception e) {
                        System.out.println(e);
                        System.exit(0);
                              }
 
            
 
                int success=0;
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO resume VALUES(?,?)");
                pstmt.setString(1, rId);
                pstmt.setBytes(2,bytes);    //Storing binary data in blob field.
                success = pstmt.executeUpdate();
                if(success>=1)  System.out.println("File Stored");
                 con.close(); 
 
                 writer.println("<br/> file Successfully Stored");
 
        } catch (FileNotFoundException fnf) {
            writer.println("You  did not specify a file to upload");
            writer.println("<br/> ERROR: " + fnf.getMessage());
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
 
            if (pdfFileBytes != null) {
                pdfFileBytes.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
 
    }
 
}
