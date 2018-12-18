package rest.first;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

@Path("/movie")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class Movie {
    	
	@Context HttpServletRequest request;
	
	@Path("/getAllMovie")
	@POST
	public JSONArray getAllMovies(@JsonDeserialize JSONObject data) throws Exception{
		
		int idLanguage = data.getInt("id");
		String title = data.getString("title");
		String query;
		if(idLanguage == 0 ) query = "SELECT * FROM movie WHERE title LIKE '" + title + "%' ";		
		else query = "SELECT * FROM movie m join language_movie l on l.id_movie = m.id WHERE m.title LIKE '" + title + "%' AND l.id_language = " + idLanguage;		
		ResultSet rs = Database.getStatement(query);
		JSONArray result = new JSONArray();
		
		JSONArray array = Database.convert(rs);
		for(int i = 0; i < array.length(); i++) {
			JSONObject o = array.getJSONObject(i);
			o.put("actor",  Actor.getByMovie(o.getInt("id")));
			o.put("city",  City.getByMovie(o.getInt("id")));
			o.put("language",  Language.getByMovie(o.getInt("id")));
			o.put("director",  Director.getByMovie(o.getInt("id")));
			result.put(o);
		}
		return result;

	}
	
	
	@Path("/getById")
	@GET
	public JSONObject getMovie(@QueryParam("id")int  data) throws Exception{
		
	
		String query = "SELECT * FROM movie WHERE id  = " + data + "";				
		ResultSet rs = Database.getStatement(query);
		
		
		JSONArray array = Database.convert(rs);
		JSONObject o = array.getJSONObject(0);
		o.put("actor",  Actor.getByMovie(data));
		o.put("city",  City.getByMovie(data));
		o.put("language",  Language.getByMovie(data));
		o.put("director",  Director.getByMovie(data));
		return o;
	}
	
	@Path("/getAllMovie")
	@GET
	public JSONArray getAllMovies() throws Exception{
		
	
		String query = "SELECT * FROM movie";		
		
		ResultSet rs = Database.getStatement(query);
		JSONArray result = new JSONArray();
		
		JSONArray array = Database.convert(rs);
		for(int i = 0; i < array.length(); i++) {
			JSONObject o = array.getJSONObject(i);
			o.put("actor",  Actor.getByMovie(o.getInt("id")));
			o.put("city",  City.getByMovie(o.getInt("id")));
			o.put("language",  Language.getByMovie(o.getInt("id")));
			o.put("director",  Director.getByMovie(o.getInt("id")));
			result.put(o);
		}
		return result;

	}
	
	
	@Path("/add")
	@POST
	public String addMovie(@JsonDeserialize JSONObject data) throws Exception{
		
		String title = data.getString("title");
		String duration = data.getString("duration");
		String minimumAge = data.getString("minimumAge");
		JSONArray actors = data.getJSONArray("listActor");
		JSONArray directors = data.getJSONArray("listDirector");
		JSONArray languages = data.getJSONArray("listLanguage");
		JSONArray city = data.getJSONArray("listCity");

		String query = "INSERT INTO movie(title, duration, min_age) VALUES('" + title + "', '" +
		duration + "', '" + minimumAge + "')";
		int id = Database.executeQuery(query);
		for(int i = 0; i < actors.length(); i++) {
			JSONObject o = actors.getJSONObject(i);
			Actor.addActorMovie(o.getInt("id"), id);
		}
		
		for(int i = 0; i < directors.length(); i++) {
			JSONObject o = directors.getJSONObject(i);
			Director.addDirectorMovie(o.getInt("id"), id);
		}
		
		for(int i = 0; i < languages.length(); i++) {
			JSONObject o = languages.getJSONObject(i);
			Language.addLanguageMovie(o.getInt("id"), id);
		}
		
		for(int i = 0; i < city.length(); i++) {
			JSONObject o = city.getJSONObject(i);
			City.addCityMovie(o.getInt("id"), id);
		}
		return "{\"id\": \"" + id + "\"}";
	}	
	@Path("/updateMovie")
	@POST
	public String upadteMovie(@JsonDeserialize JSONObject data) throws Exception{
		
		String label = data.getString("label");
		String value = data.getString("value");
		String id = data.getString("id");

		String query = "update movie set " + label + " = '" + value + "' where id = " + id ;
		System.out.println(query);
		Database.executeQuery(query);
		return "{\"id\": \"" + id + "\"}";
	}
}
