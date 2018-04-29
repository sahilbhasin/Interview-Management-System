package interviewmgmtsystem;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.InputStream;

public class DBAccess {

	// Constants for DB access credentials
	private static final String dbUN="team5";
	private static final String dbPW="icsi518";
	private static final String dbName="csi518_interview_mgmt";
	private static final String dbHost="localhost";
	private static final String dbPort="3306";
	private static final String dbURL = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
	
	// Connection variable
	private static Connection dbConn = null;

	// Get connection
	private static boolean dbConnect(){
		if(dbConn==null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				dbConn =(Connection)DriverManager.getConnection(dbURL,dbUN,dbPW);
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			}
			catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return true;
	}
	
	//Insert Applications
	public static int addApplication(int jobID, int userID, int resumeID) {
		int id=0;
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return 0;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "INSERT INTO applications(job,user,resume) VALUES(?,?,?)";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setInt(1, jobID);
			statement.setInt(2, userID);
			statement.setInt(3, resumeID);
			System.out.println(statement.toString());
			statement.executeUpdate();
			System.out.println("Successfully executed insertion query.");
			
			sql = "SELECT LAST_INSERT_ID()";
			statement = dbConn.prepareStatement(sql);
			System.out.println(statement.toString());
			rs=statement.executeQuery();
			System.out.println("Successfully executed select query.");
			
			if(rs.next()) {
				id=rs.getInt("LAST_INSERT_ID()");
			}
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return id;		
	}
	
	//Insert Resume, return ID
	public static int addResume(String filename, String filetype, int size, InputStream file) {
		int id=0;
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return 0;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "INSERT INTO resumes(filename,filetype,size,resume) VALUES(?,?,?,?)";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, filename);
			statement.setString(2,  filetype);
			statement.setInt(3, size);
			statement.setBlob(4, file);
			System.out.println(statement.toString());
			statement.executeUpdate();
			System.out.println("Successfully executed insertion query.");
			
			
			sql = "SELECT LAST_INSERT_ID()";
			statement = dbConn.prepareStatement(sql);
			System.out.println(statement.toString());
			rs=statement.executeQuery();
			System.out.println("Successfully executed select query.");
			
			if(rs.next()) {
				id=rs.getInt("LAST_INSERT_ID()");
			}
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return id;		
	}
	
	public static int get_skillid_by_name(String name) {
		int id=0;
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return 0;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT * FROM skills WHERE skillname=?";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, name);
			rs = statement.executeQuery();
			System.out.print("Successfully executed query for skill=");
			System.out.println(name);
			
			if(rs.next()) {
				id=rs.getInt("idskills");
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	public static int insert_skill(String skill_name) {
		int id=0;
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return 0;
		}
			
		try {
			System.out.println("Connected to DB");
			String sql = "INSERT INTO skills (skillname) VALUES(?)";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, skill_name);
			System.out.println(statement.toString());
			statement.executeUpdate();
			System.out.println("Successfully executed insertion query.");

			sql = "SELECT LAST_INSERT_ID()";
			statement = dbConn.prepareStatement(sql);
			System.out.println(statement.toString());
			rs=statement.executeQuery();
			System.out.println("Successfully executed select query.");
			
			if(rs.next()) {
				id=rs.getInt("LAST_INSERT_ID()");
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
		
	}

	public static  boolean insert_app_skill(int app_id, int skill_id) {
		
		if(!dbConnect()) {
			return false;
		}
			
		try {
			System.out.println("Connected to DB");
			String sql = "INSERT INTO applications_skills (application,skill) VALUES(?,?)";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setInt(1, app_id);
			statement.setInt(2, skill_id);
			System.out.println(statement.toString());
			statement.executeUpdate();
			System.out.println("Successfully executed insertion query.");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	
	
	public static boolean getApplicationExists(String jobID,String userID ) {
		ResultSet rs = getapplicationbyuser(jobID);
		ResultSet rs1=getapplicationbyjob(userID);
		try {
			if(rs.next()&&rs1.next()) {
				return true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ResultSet getapplicationbyuser(String id) {
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT * FROM applications WHERE user=?";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, id);
			rs = statement.executeQuery();
			System.out.print("Successfully executed query for id=");
			System.out.println(id);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	//Get application by Job ID
	public static ResultSet getapplicationbyjob(String id) {
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT * FROM applications WHERE job=?";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, id);
			rs = statement.executeQuery();
			System.out.print("Successfully executed query for id=");
			System.out.println(id);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	// Get jobs
	public static ResultSet getJobs() {
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT * FROM jobs";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			rs = statement.executeQuery();
			System.out.println("Successfully executed query.");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return rs;
		
	}

	// Get single job
	public static ResultSet getJob(String id) {
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT * FROM jobs WHERE idjobs=?";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, id);
			rs = statement.executeQuery();
			System.out.print("Successfully executed query for id=");
			System.out.println(id);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	// Update job
	public static boolean updateJob(String id, String title, String description, String salary, String location) {
		if(!dbConnect()) {
			return false;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "UPDATE jobs SET title=?, description=?, salary=?, location=? WHERE idjobs=? ";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, title);
			statement.setString(2, description);
			statement.setString(3, salary);
			statement.setString(4, location);
			statement.setString(5, id);
			System.out.println(statement.toString());
			statement.executeUpdate();
			System.out.println("Successfully executed update query.");
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		return true;
	}	
	
	// Add job
	public static boolean addJob(String title, String description, String salary, String location) {
		if(!dbConnect()) {
			return false;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "INSERT INTO jobs (title,description,salary,location) VALUES(?,?,?,?)";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, title);
			statement.setString(2, description);
			statement.setString(3, salary);
			statement.setString(4, location);
			System.out.println(statement.toString());
			statement.executeUpdate();
			System.out.println("Successfully executed update query.");
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		return true;		
	}
		public static boolean deleteJob(String id) {
		if(!dbConnect()) {
			return false;
		}
		try {
			System.out.println("Connected to DB");
			String sql = "DELETE FROM jobs WHERE idjobs = ?";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, id);
			System.out.println(statement.toString());
			statement.executeUpdate();
			System.out.println("Successfully executed delete query. ");
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// Schedule interview
	public static boolean addInterview(Interview w) {
		if(!dbConnect()) {
			return false;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql="INSERT INTO interviews (recruiterid,candidateid,round,start_time,end_time) VALUES(?,?,?,?,?)";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, w.getRecruiterid());
			statement.setString(2, w.getCandidate());
			statement.setString(3,w.getRound());
			statement.setString(4,w.getStartDateTime());
			statement.setString(5,w.getEndDateTime());
			
			System.out.println(statement.toString());
			statement.executeUpdate();
			System.out.println("Successfully executed insertion query.");
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		return true;		
	}

	// Update interview
	public static boolean updateInterview(String id, String score, String feedback) {
		if(!dbConnect()) {
			return false;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "UPDATE interviews SET score=?, feedback=? WHERE idinterviews=? ";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, score);
			statement.setString(2, feedback);
			statement.setString(3, id);
			System.out.println(statement.toString());
			statement.executeUpdate();
			System.out.println("Successfully executed update query.");
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		return true;
	}		
	
	// Get all interviews
	public static ResultSet getInterviews() {
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT interviews.idinterviews, interviews.candidateid, interviews.start_time, interviews.end_time, interviews.round, interviews.score, interviews.feedback, users.idusers, users.firstname, users.lastname FROM interviews INNER JOIN users ON interviews.candidateid = users.idusers";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			rs = statement.executeQuery();
			System.out.println("Successfully executed query.");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return rs;
		
	}
	
	
	
	
	// Get all interview conflicts
	public static boolean getInterviewConflicts(Interview i) {
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return true;
		}
		
		try {
			
			String start_time = i.getStartDateTime();
			String recruiterid = i.getRecruiterid();
			String candidateid = i.getCandidate();
			
			
			System.out.println("getInterviewConflicts: Connected to DB");
			String sql = "SELECT * FROM interviews WHERE ? <= end_time AND ? >= start_time AND (recruiterid=? OR candidateid=?)";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, start_time);
			statement.setString(2, start_time);
			statement.setString(3, recruiterid);
			statement.setString(4, candidateid);
			System.out.println(statement.toString());
			rs = statement.executeQuery();
			System.out.println("Successfully executed query.");

			if(rs.next()) {
				return true;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}	
	

	// Get single interview
	public static ResultSet getInterview(String id) {
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT interviews.idinterviews, interviews.candidateid, interviews.start_time, interviews.end_time, interviews.round, interviews.score, interviews.feedback, users.idusers, users.firstname, users.lastname FROM interviews INNER JOIN users ON interviews.candidateid = users.idusers WHERE interviews.idinterviews=?";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, id);
			rs = statement.executeQuery();
			System.out.print("Successfully executed getInterview query for interview id=");
			System.out.println(id);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
	// Get role
	public static String getRole(int roleid) {
		ResultSet rs = null;
		
		// Default to user
		String role="user";
		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT name FROM roles WHERE idroles=?";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setInt(1, roleid);
			rs = statement.executeQuery();
			System.out.print("Successfully executed query for role=");
			System.out.println(roleid);
				
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(rs.next()) {
				role = rs.getString("name");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		return role;
	}
	
	// Get single user
	public static ResultSet getUser(String username) {
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT * FROM users WHERE username=?";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, username);
			rs = statement.executeQuery();
			System.out.print("Successfully executed query for username=");
			System.out.println(username);
				
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	// Get single user
	public static ResultSet getUserById(int id) {
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT * FROM users WHERE idusers=?";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			System.out.print("Successfully executed query for idusers=");
			System.out.println(id);
				
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
		
	
	
	// Get all users
	public static ResultSet getUsers() {
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT idusers,firstname,lastname FROM users WHERE NOT username='admin'";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			rs = statement.executeQuery();
			System.out.print("Successfully executed query for all users");
				
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	// Get all users who have applied for jobs
	public static ResultSet getApplicants() {
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT DISTINCT users.idusers, users.firstname, users.lastname FROM users INNER JOIN applications ON users.idusers = applications.user";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			rs = statement.executeQuery();
			System.out.print("Successfully executed query for all applicants");
				
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	// Get all users who have applied for jobs
	public static ResultSet getRecruiters() {
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT idusers,firstname,lastname FROM users WHERE role > 1 AND NOT username='admin'";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			rs = statement.executeQuery();
			System.out.print("Successfully executed query for all users > 1");
				
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
	
	// Get user exists
	public static boolean getUserExists(String username) {
		ResultSet rs = getUser(username);
		try {
			if(rs.next()) {
				return true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// Add user
	public static boolean addUser(User user) {
		if(!dbConnect()) {
			return false;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "INSERT INTO users (username,firstname,lastname,email,password,role) VALUES(?,?,?,?,?,1)";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			System.out.println(statement.toString());
			statement.executeUpdate();
			System.out.println("Successfully executed update query.");
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		return true;		
	}
	
	// Get the single organization row out of table
	public static ResultSet getOrg() {
		ResultSet rs = null;
		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "SELECT * FROM organization LIMIT 0,1";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			rs = statement.executeQuery();
			System.out.println("Successfully executed query.");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return rs;
	}
	
	// Update the organization row
	public static boolean updateOrg(String name, String description, String contact_name, String contact_email) {
		if(!dbConnect()) {
			return false;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "UPDATE organization SET name=?, description=?, contact_name=?, contact_email=?  WHERE idorganization=1 ";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, description);
			statement.setString(3, contact_name);
			statement.setString(4, contact_email);
			System.out.println(statement.toString());
			statement.executeUpdate();
			System.out.println("Successfully executed update query.");
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		return true;
	}

	public static String UpdateUserRole(int uid, int role) {

		
		if(!dbConnect()) {
			return null;
		}
		
		try {
			System.out.println("Connected to DB");
			String sql = "update users set role = ? where idusers=?";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setInt(1, role);
			statement.setInt(2, uid);
			int i = statement.executeUpdate();
			if(i==0)
				return "No such row";
			System.out.print("Successfully updated role for idusers=");
			System.out.println(uid);
				
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "success";

	}
	
	public static int GetroleFromUID(int uid) {
		int rs = 1 ;
				
				if(!dbConnect()) {
					return 0;
				}
				
				try {
					System.out.println("Connected to DB");
					String sql = "SELECT role from users where idusers=?";
					PreparedStatement statement = dbConn.prepareStatement(sql);
					statement.setInt(1, uid);
					rs= statement.executeUpdate();
					System.out.print("Successfully displayed role for idusers=");
					System.out.println(uid);
						
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				
				return rs;

			}
	
	
public static boolean CheckPassword(int uid, String current_password) {
	ResultSet rs = null;
	if(!dbConnect()) {
		return true;
	}
	
	try {
		
		System.out.println("Connected to DB");
		String sql = "Select  password from users where idusers=?";
	PreparedStatement statement = dbConn.prepareStatement(sql);	
	statement.setInt(1, uid);
	rs=statement.executeQuery();
	while(rs.next()) {
	if( rs.getString("password").equals(current_password))
	{
		System.out.print("Successfully checked Password for user id=");
		System.out.println(uid);
		return true;
	}
	else
	{
		System.out.print(" current password does not match the orignal Password for user id=");
		System.out.println(uid);
		return false;
	}
}
}
catch (SQLException e) {
	e.printStackTrace();
}

	return true;
}

	
	
	
	
public static boolean UpdatePassword(int uid,String current_Password, String conf_new_Password, String new_Password) {

		int rs;
		if(!dbConnect()) {
			return false;
		}
		
		try {
			System.out.println("Connected to DB");
			if(!new_Password.equals(conf_new_Password)) {
				return false;
			}
			
			
			String sql = "update users set password = ? where idusers=?";
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, new_Password);
			statement.setInt(2, uid);
			rs=statement.executeUpdate();
			if(rs==0) {
				return false;
			}

			System.out.print("Successfully updated Password for users id=");
			System.out.println(uid);
			return true;
				
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		


	}
			
	
}
