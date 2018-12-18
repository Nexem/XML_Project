package rest.first;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/account")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class Account {
    	
	@Context HttpServletRequest request;
	
	@Path("/login")
	@POST
	public String loginUser(@JsonDeserialize JSONObject data) throws SQLException, JSONException{
		
		String pseudo = data.getString("pseudo");
		String password = data.getString("password");

	
	
		String query = "SELECT * FROM user WHERE login = '" + pseudo + "'";				
		
		ResultSet rs = Database.getStatement(query);
		if(rs.next() && rs.getString("password").compareTo(password) == 0) {
			HttpSession session = request.getSession();

			UserBean user = (UserBean) session.getAttribute("user");
			user = new UserBean(pseudo, password);
			session.setAttribute( "user", user );
			return "{\"success\": \"true\"}";
		}
		return "{\"success\": \"false\"}";
	}
	
	
	@Path("/register")
	@POST
	public String registerUser(@JsonDeserialize JSONObject data) throws SQLException, JSONException{
		
		String pseudo = data.getString("pseudo");
		String password = data.getString("password");

		String query = "SELECT * FROM user where login = '" + pseudo + "'";
		
		ResultSet rs = Database.getStatement(query);
		
		if(!rs.next()){
			HttpSession session = request.getSession();

			UserBean user = (UserBean) session.getAttribute("user");
			user = new UserBean(pseudo, password);
			session.setAttribute( "user", user );
			query = "INSERT INTO user(login, password) VALUES('" + pseudo + "','" + password + "')";
			Database.executeQuery(query);
			return "{\"success\": \"true\"}";
		}
		return "{\"success\": \"false\"}";
	}
}
