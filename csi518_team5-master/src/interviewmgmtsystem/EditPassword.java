package interviewmgmtsystem;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditPassword
 */
@WebServlet("/EditPassword")
public class EditPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPassword() {
        super();
        // TODO Auto-generated constructor stub
    } 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String new_Password=request.getParameter("new_password");
		String current_Password=request.getParameter("current_password");
		String conf_new_Password=request.getParameter("conf_new_password");
		String id=request.getParameter("user_id");
		
		if(!(new_Password.equals(conf_new_Password))) {
			out.print("Passwords do not match.");
			return;
		}
		if(!(DBAccess.CheckPassword(Integer.parseInt(id), current_Password))) {
			out.print("Password incorrect.");
			return;
		}
		if(DBAccess.CheckPassword(Integer.parseInt(id), new_Password)) {
			out.print("New password same as old password.");
			return;
		}
		
		
		if(id!=null) {
			if(DBAccess.UpdatePassword(Integer.parseInt(id), current_Password, conf_new_Password, new_Password)) {
				System.out.print("updated Password successfully for userid = ");
				System.out.print(Integer.parseInt(id));
				out.print("success");
			}
			else {
				out.print("Could not update database.");
			}
		}
		
		else
		{
			out.print("missing id");
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
