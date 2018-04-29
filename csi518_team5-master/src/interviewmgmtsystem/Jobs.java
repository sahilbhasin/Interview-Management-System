package interviewmgmtsystem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;

/**
 * Servlet implementation class Jobs
 */
@WebServlet("/Jobs")
public class Jobs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Jobs() {
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
			response.sendRedirect("login.jsp?referrer=Jobs");
		}
		
		// Get writer object
		PrintWriter out = response.getWriter();
		
		// Set content type
		response.setContentType("text/plain");
		
		// Get parameter id
		String id = request.getParameter("id");
		String action = request.getParameter("action");
		if(action==null) {
			action="get";
		}
		
		
		if(action.equals("update")) {
			if(id==null) {
				out.print("no id");
			}
			else {
				String title = request.getParameter("title");
				String description = request.getParameter("description");
				String salary = request.getParameter("salary");
				String location = request.getParameter("location");
				if(title==null || description==null) {
					if(title==null&&description==null) {
						out.print("Title and description not given.");
					}
					else if(title==null) {
						out.print("Title not entered.");
					}
					else if(description==null) {
						out.print("Description not given");
					}
					else if(salary==null) {
						out.print("Salary not given");
					}
					else if(location==null) {
						out.print("Location not given");
					}
				}
				else {
					if(DBAccess.updateJob(id,title,description, salary, location)) {
						out.print("success");
					}
					else {
						out.print("DB update error");
					}
				}
			}
		}
		else if(action.equals("delete")) {
			if(id==null) {
				out.print("no id");
				return;
			}
			
			if(DBAccess.deleteJob(id)) {
				out.print("success");
			}
			else {
				out.print("DB update error");
			}
			
		}
		
		else if (action.equals("new")) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String salary = request.getParameter("salary");
			String location = request.getParameter("location");
			boolean error=false;
			
			if(title==null||title.equals("")) {
				out.print("title missing");
			}
			else if(description==null||description.equals("")) {
				out.print(" description missing");
			}
			else if(salary==null||salary.equals("")) {
				out.print(" salary missing");
			}
			else if(location==null||location.equals("")) {
				out.print(" location missing");
			}
			else {
				if(DBAccess.addJob(title,description, salary, location)) {
					out.print("success");
				}
				else {
					out.print("DB update error");
				}	
			}
			
		}
		else if(action.equals("get")) {
			
			if(id==null) {
				// Get all jobs
				ResultSet rs = DBAccess.getJobs();
				if(rs==null) {
					out.print("DB error");
				}
				else {
					response.setContentType("application/json");
					out.print(JSON.getJSONFromRS(rs));
				}
			}
			else {
				// Get job by ID
				ResultSet rs = DBAccess.getJob(id);
				if(rs==null) {
					out.print("DB error");
				}
				else {
					response.setContentType("application/json");
					out.print(JSON.getJSONFromRS(rs));
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
