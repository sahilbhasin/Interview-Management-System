package interviewmgmtsystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


/**
 * Servlet implementation class Jobs
 */
@WebServlet("/Applications")
@MultipartConfig
public class Applications extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Applications() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get session, else redirect
		HttpSession session = request.getSession(false);
		if(session==null) {
			response.sendRedirect("login.jsp?referrer=Applications");
		}
		
		// Get writer object
		PrintWriter out = response.getWriter();
		
		// Set content type
		response.setContentType("text/plain");
		
		// Get parameter id
		String action = request.getParameter("action");
		if(action==null) {
			action="get";
		}
		
		
		if(action.equals("update")) {
			out.print("NOT IMPLEMENTED");
		}
		else if (action.equals("get")) {
			System.out.println("ACTION IS GET!!!!!!!!!!");
			String jobID = request.getParameter("jobid");
			String userID = request.getParameter("userid");
			
			if(!(jobID==null||userID==null||jobID.equals("")||userID.equals(""))) {
				if(DBAccess.getApplicationExists(userID,jobID)) {
					out.print("Applied");
				}
				else {
					out.print("Not Applied");
				}
			}
			else {
				out.print("Not Applied");
				
			}
		
			
			
		}
		else if (action.equals("new")) {
			String jobID = request.getParameter("jobid");
			String userID = request.getParameter("userid");


			
			
			int jobIDint = Integer.parseInt(jobID);
			int userIDint = Integer.parseInt(userID);
			
			
			System.out.println("jobid= " + jobID);
			System.out.println("userid= " + userID);
			
			
			int appID=0;
			int skillID=0;
			int resumeID=0;
			boolean error=false;
			String skills[] = request.getParameter("skills").split(",");

			InputStream resume = null;
			Part filePart = request.getPart("resume");
			if(filePart!=null) {
				String resumeFileName = filePart.getSubmittedFileName().toString();
				String resumeFileType = filePart.getContentType();
				int resumeFileSize = (int)filePart.getSize();
				
				if(resumeFileSize>4194304) {
					out.print("Resume exceeds MySQL max_allowed_packet (4MiB)");
					return;
				}
				
				resume = filePart.getInputStream();
				
				if(resume!=null) {
					resumeID = DBAccess.addResume(resumeFileName, resumeFileType, resumeFileSize, resume);
					if(resumeID==0) {
						out.print("Resume failed to upload.");
						return;
					}
				}
				
			}			
			
			
			if(DBAccess.getApplicationExists(userID, jobID)) {
				out.print("Application exists.");
				return;
			}
			else {			
				
				appID=DBAccess.addApplication(jobIDint,userIDint,resumeID); 
				System.out.println("application id: " + appID);
				if(appID!=0){
					
					// successfully inserted application into applications table
					for(String skill: skills) {
						skill = skill.trim().toLowerCase();
						System.out.println("Skill: " + skill);
						skillID=DBAccess.get_skillid_by_name(skill);
						
						if(skillID==0) {
							skillID=DBAccess.insert_skill(skill);
						}
						else {
							System.out.println("skill " + skill + " exists in DB");
						}
						if(skillID==0) {
							out.print("Could not insert skill: " + skill);
							error=true;
						}
						
						if(!(DBAccess.insert_app_skill(appID, skillID))) {
							out.print("Could not insert application + skill");
							error=true;
						}
					}
				}
				else {
					out.print("Could not insert application.");
					error=true;
				}
				if(!error) {
					out.print("success");
				}
			}
		}	

		else {
			out.print("Unknown form action.");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
