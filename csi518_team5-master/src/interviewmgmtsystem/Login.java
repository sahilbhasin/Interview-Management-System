package interviewmgmtsystem;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private User getUser(String username) {
    	
    	User user = new User();
    	ResultSet rs = DBAccess.getUser(username);
    	
    	if(rs==null) {
    		System.out.println("User not found or DB connection failed.");
    		return null;
    	}

    	try {
    		if(rs.next()) {
    			int db_userid = rs.getInt("idusers");
    			String db_password = rs.getString("password");
	    		String db_firstname = rs.getString("firstname");
	    		String db_lastname = rs.getString("lastname");
	    		String db_email = rs.getString("email");
	    		int db_role = rs.getInt("role");
	    	
	    		user.setUserID(db_userid);
	    		user.setEmail(db_email);
	    		user.setFirstName(db_firstname);
	    		user.setLastName(db_lastname);
	    		user.setPassword(db_password);
	    		user.setRole(DBAccess.getRole(db_role));
	    		
	    		return user;
    		}	
	    }
	    catch (SQLException e) {
	    	e.printStackTrace();
	    }
    	
    	return null;
    	
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	// Get UN/PW parameters
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		
		// If UN/PW not entered, print error.
		if(password==null&&username==null) {
			response.getWriter().print("Please enter username and password.");
			return;
		}
		else if(username==null) {
			response.getWriter().print("Please enter username.");
			return;
		}
		else if(password==null) {
			response.getWriter().print("Please enter password.");
			return;
		}
		
		// Get user from DB
		User user = getUser(username);
		
		// If this fails, print error.
		if(user==null) {
			response.getWriter().print("User does not exist.");
			return;
		}
		
		// Validate PW, else print error.
		if(!(user.validate(password))) {
			response.getWriter().print("Invalid password.");
			return;
		}
		
		// Create HTTP session
		HttpSession session = request.getSession(true);
		session.setAttribute("user", username);
		session.setAttribute("userid", user.getUserID());
		session.setAttribute("role", user.getRole());
		response.getWriter().print("success");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
