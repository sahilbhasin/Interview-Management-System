package interviewmgmtsystem;

public class Interview {
	private String recruiterid;
	private String candidate;
	private String start_date;
	private String start_time;
	private String end_date;
	private String end_time;
	private String candidatename;
	private String round;
	private String score;
	private String feedback;
	
	public String getRecruiterid() {
		return recruiterid;
	}
	public void setRecruiterid(String recruiterid) {
		this.recruiterid = recruiterid;
	}
	public String getCandidate() {
		return candidate;
	}
	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}
	public String getStartTime() {
		return start_time;
	}
	public String getEndTime() {
		return end_time;
	}
	public String getStartDateTime() {
		return start_date + " " + start_time;
	}
	public String getEndDateTime() {
		return end_date + " " + end_time;
	}
	public String getRound() {
		return round;
	}
	public String getScore() {
		return score;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setEndDate(String end_date) {
		this.end_date = end_date;
	}
	public void setEndTime(String end_time) {
		this.end_time = end_time;
	}
	public void setStartDate(String start_date) {
		this.start_date = start_date;
	}
	public void setStartTime(String start_time) {
		this.start_time = start_time;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public void setScore(String score) {
		this.score = score;
	}	
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}	
	public String getCandidatename() {
		return candidatename;
	}
	public void setCandidatename(String candidatename) {
		this.candidatename = candidatename;
	}
	

	

}
