package rest.first;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/director")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class Director {
    	
	@Context HttpServletRequest request;
	

	@Path("/getByName")
	@POST
	public JSONArray getName(@JsonDeserialize JSONObject data) throws Exception{
		
		String name = data.getString("name");
	
		String query = "SELECT * FROM director WHERE name Like '" + name + "%'";				
		ResultSet rs = Database.getStatement(query);
		
		return Database.convert(rs);
	}
	
	
	@Path("/add")
	@POST
	public String addDirector(@JsonDeserialize JSONObject data) throws SQLException, JSONException{
		
		String name = data.getString("name");

		String query = "SELECT * FROM director where name = '" + name + "'";
		
		ResultSet rs = Database.getStatement(query);
		
		if(!rs.next()){
			query = "INSERT INTO director(name) VALUES('" + name + "')";
			return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
		}
		return "{\"success\": \"false\"}";
	}
	
	@Path("/getDirectorByMovie")
	@POST
	public static JSONArray getByMovie(int id) throws Exception{
	
		String query = "SELECT name, a.id as id FROM director_movie am join director a on a.id = am.id_director WHERE am.id_movie = " + id;				
		System.out.println(query);
		ResultSet rs = Database.getStatement(query);
		
		return Database.convert(rs);
	}
	
	
	@Path("/addToMovie")
	@POST	
	
	public static String addDirectorMovieJSON(@JsonDeserialize JSONObject data) throws SQLException, JSONException{
		
		int idDirector = data.getInt("id_director");
		int idMovie = data.getInt("id_movie");
		
		String query = "INSERT INTO director_movie(id_director, id_movie) VALUES(" + idDirector + ", " + idMovie + ")";
		return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
	}
	
	public static String addDirectorMovie(int id, int idMovie) throws SQLException, JSONException{
	
		String query = "INSERT INTO director_movie(id_director, id_movie) VALUES(" + id + ", " + idMovie + ")";
		System.out.println(query);
		return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
	}
	
	@Path("/remove")
	@POST
	public static String removeDirectorMovieJSON(@JsonDeserialize JSONObject data) throws SQLException, JSONException{
		
		int idDirector = data.getInt("id_director");
		int idMovie = data.getInt("id_movie");
		
		String query = "DELETE FROM director_movie WHERE id_director = " + idDirector + " and id_movie = " + idMovie;
		System.out.println(query);
		return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
	}

}
