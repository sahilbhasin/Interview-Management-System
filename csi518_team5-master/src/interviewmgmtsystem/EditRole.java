package interviewmgmtsystem;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditRole
 */
@WebServlet("/EditRole")
public class EditRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditRole() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String new_role = request.getParameter("newrole");
		String id_of_user_to_update=request.getParameter("id_of_user");
		String result="";
		
		if(new_role!=null) {
			 result = DBAccess.UpdateUserRole(Integer.parseInt(id_of_user_to_update), Integer.parseInt(new_role));
		}
		else {
			System.out.print("New Role not found");
		}
		
		if(result.equalsIgnoreCase("success")) {
			
			out.print("success");
			System.out.print("updated role successfully for userid = ");
			System.out.print(Integer.parseInt(id_of_user_to_update));
			//request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else{
			out.print("failed to update role");
			System.out.print("updated role error for userid = ");
			System.out.print(Integer.parseInt(id_of_user_to_update));
			//request.getRequestDispatcher("/editRole.jsp").forward(request, response);
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
