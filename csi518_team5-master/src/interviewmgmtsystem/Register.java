package interviewmgmtsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Variables
		PrintWriter out = response.getWriter();
		Boolean isMissing = false;
		Boolean isError = false;
		Boolean result = false;
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm");
		String errormsg = "";
		String joined = "";
		ArrayList<String> missing = new ArrayList<String>(0);
		
		// Form validation
		if(firstname == null || firstname.isEmpty()) {
			isMissing=true;
			missing.add("First Name");
		}
		if(lastname == null || lastname.isEmpty()) {
			isMissing=true;
			missing.add("Last Name");
		}
		if(username == null || username.isEmpty()) {
			isMissing=true;
			missing.add("User Name");
		}
		if(email == null || email.isEmpty()) {
			isMissing=true;
			missing.add("Email");
		}
		if(password == null || password.isEmpty()) {
			isMissing=true;
			missing.add("Password");
		}
		if(confirm == null || confirm.isEmpty()) {
			isMissing=true;
			missing.add("Confirm Password");
		}

		if(isMissing) {
			isError=true;
			joined = String.join(", ", missing);
			errormsg = "Missing fields (" + joined + ")";
		}
		if(!(confirm.equals(password))) {
			isError=true;
			if(isMissing) {
				errormsg += ", ";
			}
			errormsg += "Passwords do not match.";
		}
		else {
		
			// Create user object
			User user = new User();
			user.setUserName(username);
			user.setPassword(password);
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setEmail(email);
			
	
			// Check if user object exists.
			if(DBAccess.getUserExists(username)) {
				isError=true;
				errormsg="Username already exists.";
			}
			else {
				// Attempt to add the user.
				result = DBAccess.addUser(user);
				if(!result ) {
					isError=true;
					errormsg="Failed to add user.";
				}
			}
		}
		
		
		// Return response
		response.setContentType("text/plain");
		if(isError) {
			out.print(errormsg);
		}
		else {
			out.print("success");
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
