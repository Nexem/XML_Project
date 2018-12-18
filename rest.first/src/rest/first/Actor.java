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

@Path("/actor")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class Actor {
    	
	@Context HttpServletRequest request;
	

	@Path("/getByName")
	@POST
	public JSONArray getName(@JsonDeserialize JSONObject data) throws Exception{
		
		String name = data.getString("name");

	
		String query = "SELECT * FROM actor WHERE name Like '" + name + "%'";				
		ResultSet rs = Database.getStatement(query);
		
		return Database.convert(rs);
	}
	
	
	@Path("/add")
	@POST
	public String registerUser(@JsonDeserialize JSONObject data) throws SQLException, JSONException{
		
		String name = data.getString("name");

		String query = "SELECT * FROM actor where name = '" + name + "'";
		
		ResultSet rs = Database.getStatement(query);
		
		if(!rs.next()){
			query = "INSERT INTO actor(name) VALUES('" + name + "')";
			return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
		}
		return "{\"success\": \"false\"}";
	}
	
	@Path("/getActorsByMovie")
	@POST
	public static JSONArray getByMovie(int id) throws Exception{
	
		String query = "SELECT name, a.id as id FROM actor_movie am join actor a on a.id = am.id_actor WHERE am.id_movie = " + id;				
		System.out.println(query);
		ResultSet rs = Database.getStatement(query);
		
		return Database.convert(rs);
	}
	
	
	@Path("/addToMovie")
	@POST	
	
	public static String addActorMovieJSON(@JsonDeserialize JSONObject data) throws SQLException, JSONException{
		
		int idActor = data.getInt("id_actor");
		int idMovie = data.getInt("id_movie");
		
		String query = "INSERT INTO actor_movie(id_actor, id_movie) VALUES(" + idActor + ", " + idMovie + ")";
		return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
	}
	
	
	public static String addActorMovie(int id, int idMovie) throws SQLException, JSONException{
		
		String query = "INSERT INTO actor_movie(id_actor, id_movie) VALUES(" + id + ", " + idMovie + ")";
		return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
	}	



	
	@Path("/removeActorMovie")
	@POST
	
	public static String removeActorMovieJSON(@JsonDeserialize JSONObject data) throws SQLException, JSONException{
		
		int idActor = data.getInt("id_actor");
		int idMovie = data.getInt("id_movie");
		
		String query = "DELETE FROM actor_movie WHERE id_actor = " + idActor + " and id_movie = " + idMovie;
		System.out.println(query);
		return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
	}
	
	public static String removeActor(int id, int idMovie) throws SQLException, JSONException{
	
		String query = "DELETE FROM actor_movie WHERE id_actor = " + id + " and actor_movie = " + idMovie;
		return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
	}

	
	
	
}
