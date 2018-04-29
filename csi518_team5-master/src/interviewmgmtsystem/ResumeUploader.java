package interviewmgmtsystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ResumeUploader
 */
@MultipartConfig
@WebServlet("/ResumeUploader")
public class ResumeUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResumeUploader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Get writer object
		
		PrintWriter out = response.getWriter();
		System.out.println("In ResumeUploader");
		InputStream resume = null;
		Part filePart = request.getPart("resume");
		if(filePart!=null) {
			String filename = filePart.getSubmittedFileName().toString();
			System.out.println("Filename: " + filename);
			
			
			String filetype = filePart.getContentType();
			System.out.println("Filetype: " + filetype);
			
			int size = (int)filePart.getSize();
			System.out.println("Filesize: " + size);
			
			
			resume = filePart.getInputStream();
			
			if(resume!=null) {
				if(DBAccess.addResume(filename, filetype, size, resume)!=0) {
					out.print("success");
				}
				else {
					out.println("Failed to upload to DB");
				}
			}
			
			
			
		}
		
	}

}
