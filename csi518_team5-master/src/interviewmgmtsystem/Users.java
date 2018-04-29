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
@WebServlet("/Users")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Users() {
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
			response.sendRedirect("login.jsp");
		}
		ResultSet rs = null;
		
		
		// Get writer object
		PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		if(action==null) {
			action="get";
		}
		
		if(action.equals("get")) {
		
			if(id!=null) {
				rs = DBAccess.getUserById(Integer.parseInt(id));
			}
			else {
				rs = DBAccess.getUsers();
			}
	
			if(rs==null) {
				out.print("DB error");
			}
			else {
				response.setContentType("application/json");
				out.print(JSON.getJSONFromRS(rs));
			}
			
		}
		else if (action.equals("get_applicants")) {
			rs = DBAccess.getApplicants();
			if(rs==null) {
				out.print("DB error");
			}
			else {
				response.setContentType("application/json");
				out.print(JSON.getJSONFromRS(rs));
			}
		}
		else if (action.equals("get_recruiters")) {
			rs = DBAccess.getRecruiters();
			if(rs==null) {
				out.print("DB error");
			}
			else {
				response.setContentType("application/json");
				out.print(JSON.getJSONFromRS(rs));
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
