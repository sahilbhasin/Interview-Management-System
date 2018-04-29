package interviewmgmtsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON {

	public static String getJSONFromRS(ResultSet rs) {
    	
    	JSONArray jsonArray = new JSONArray();
    	
    	try {
	    	while(rs.next()) {
	    		int rows = rs.getMetaData().getColumnCount();
	    		JSONObject obj = new JSONObject();
	    		for(int i=0; i<rows; i++) {
	    			obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));
	            }
	    		System.out.println(obj.toString());
	    		jsonArray.put(obj);
	    	}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    		return "[{ status: error }]";
    	}
    	catch (JSONException e) {
    		e.printStackTrace();
    		return "[{ status: error }]";
    	}
    	
    	return jsonArray.toString();
    }
		
}
