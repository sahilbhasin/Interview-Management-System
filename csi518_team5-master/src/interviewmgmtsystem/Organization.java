package interviewmgmtsystem;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;

/**
 * Servlet implementation class Organization
 */
@WebServlet("/Organization")
public class Organization extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Organization() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Get session, else redirect
		HttpSession session = request.getSession(false);
		if(session==null) {
			response.sendRedirect("login.jsp");
		}
		
		// Set content type
		response.setContentType("application/json");
		
		// Get parameter id
		String action = request.getParameter("action");
		
		if(action==null) {
			// Get organization info
			ResultSet rs = DBAccess.getOrg();
			if(rs==null) {
				response.getWriter().print("{ status: 'error' }");
			}
			else {
				response.getWriter().print(JSON.getJSONFromRS(rs));
			}
		}
		else if(action.equals("update")) {
			System.out.println("Update called.");
			String contact_name = request.getParameter("contact_name");
			String contact_email = request.getParameter("contact_email");
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			
			
			//TODO: validation
			
			if(DBAccess.updateOrg(name, description, contact_name, contact_email)) {
				response.getWriter().print("success");
			}
			else {
				response.getWriter().print("Error updating database");
			}
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
