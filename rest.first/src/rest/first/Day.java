package rest.first;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/day")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class Day {
    	
	@Context HttpServletRequest request;
	
	
	
	@Path("/addToMovie")
	@POST	
	
	public static String addActorMovieJSON(@JsonDeserialize JSONObject data) throws SQLException, JSONException{
		
		int idActor = data.getInt("id_actor");
		int idMovie = data.getInt("id_movie");
		
		String query = "INSERT INTO actor_movie(id_actor, id_movie) VALUES(" + idActor + ", " + idMovie + ")";
		return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
	}
	
	public static String addDayMovie(String day, int idMovieShow) throws SQLException, JSONException{
	
		String query = "INSERT INTO day_cinema(day, id_movie_show) VALUES('" + day + "', " + idMovieShow + ")";
		return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
	}
	
	
	
	
	
}
