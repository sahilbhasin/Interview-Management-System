package interviewmgmtsystem;

import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import java.lang.NumberFormatException;


/**
 * Servlet implementation class Interviews
 */
@WebServlet("/Interviews")
public class Interviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Interviews() {
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
			response.sendRedirect("login.jsp?referrer=Interviews");
		}
		
		PrintWriter out=response.getWriter();
		
		// Get action/id parameters
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		if(action==null) {
			action="get";
		}
		
		if(action.equals("new")) {
		
			Interview i=new Interview();
			String rid=request.getParameter("recruiterid");
			String cid=request.getParameter("candidateid");
			String start_date=request.getParameter("start_date");
			String end_date=request.getParameter("end_date");
			String start_time=request.getParameter("start_time");
			String end_time=request.getParameter("end_time");
			String round=request.getParameter("round");
			i.setRecruiterid(rid);
			i.setCandidate(cid);
			i.setStartDate(start_date);
			i.setStartTime(start_time);
			i.setEndDate(end_date);
			i.setEndTime(end_time);
			i.setRound(round);
			
			if(DBAccess.getInterviewConflicts(i)) {
				out.print("Date/time overlaps with another interview.");
				return;
			}
			
			if(DBAccess.addInterview(i)) {
				out.print("success");
			}
			else {
				out.print("Could not add interview to database.");
			}
		
		}
		else if(action.equals("update")) {
			String score = request.getParameter("score");
			String feedback = request.getParameter("feedback");
			
			
			try {
				int scoreInt = Integer.parseInt(score);
				if(scoreInt<0||scoreInt>100) {
					out.print("Score must be 0-100");
					return;
				}
			}
			catch(NumberFormatException e) {
				e.printStackTrace();
				out.print("Score must be 0-100");
				return;
			}
			
			if(DBAccess.updateInterview(id, score, feedback)) {
				out.print("success");
			}
			else {
				out.print("Database update error");
			}
		}
		else if(action.equals("get")) {
			
			if(id==null) {
				// Get all jobs
				ResultSet rs = DBAccess.getInterviews();
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
				ResultSet rs = DBAccess.getInterview(id);
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
		doGet(request, response);
	}
}
