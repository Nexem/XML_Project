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

@Path("/city")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class City {
    	
	@Context HttpServletRequest request;
	

	@Path("/getByName")
	@POST
	public JSONArray getName(@JsonDeserialize JSONObject data) throws Exception{
		
		String name = data.getString("name");
	
		String query = "SELECT * FROM city WHERE name Like '" + name + "%'";				
		ResultSet rs = Database.getStatement(query);
		
		return Database.convert(rs);
	}
	
	
	@Path("/add")
	@POST
	public String addCity(@JsonDeserialize JSONObject data) throws SQLException, JSONException{
		
		String name = data.getString("name");

		String query = "SELECT * FROM city where name = '" + name + "'";
		
		ResultSet rs = Database.getStatement(query);
		
		if(!rs.next()){
			query = "INSERT INTO city(name) VALUES('" + name + "')";
			return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
		}
		return "{\"success\": \"false\"}";
	}
	
	@Path("/getCityByMovie")
	@POST	
	public static JSONArray getByMovie(int id) throws Exception{
	
		String query = "SELECT name, a.id as id FROM movie_show_cinema am join city a on a.id = am.id_city WHERE am.id_movie = " + id;				
		ResultSet rs = Database.getStatement(query);
		
		return Database.convert(rs);
	}
	
	
	@Path("/addToMovie")
	@POST
	public static String addCityMovieJSON(@JsonDeserialize JSONObject data) throws SQLException, JSONException{
		
		int idCity = data.getInt("id_city");
		int idMovie = data.getInt("id_movie");
		
		String query = "INSERT INTO city_movie(id_city, id_movie) VALUES(" + idCity + ", " + idMovie + ")";
		return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
	}
	
	@Path("/remove")
	@POST
	public static String removeCityMovieJSON(@JsonDeserialize JSONObject data) throws SQLException, JSONException{
		
		int idCity = data.getInt("id_city");
		int idMovie = data.getInt("id_movie");
		
		String query = "DELETE FROM city_movie WHERE id_city = " + idCity + " and id_movie = " + idMovie;
		return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
	}
	
	public static String addCityMovie(int id, int idMovie) throws SQLException, JSONException{
	
		String query = "INSERT INTO movie_show_cinema(id_city, id_movie) VALUES(" + id + ", " + idMovie + ")";
		System.out.println(query);
		return "{\"id\": \"" + Database.executeQuery(query) + "\"}";
	}
}
