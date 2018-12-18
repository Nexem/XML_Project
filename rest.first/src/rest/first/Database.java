package rest.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.mysql.jdbc.Statement;

public class Database {

    private static Connection connexion = null;
    
    public static void connect() {
        System.out.println("log");
        /* Chargement du driver JDBC pour MySQL */
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver OK !");
        } catch ( ClassNotFoundException e ) {
            System.out.println(e.getMessage());
        }
        /* Connexion à la base de données */
        String url = "jdbc:mysql://localhost:3306/cinema";
        String utilisateur = "root";
        String motDePasse = "";
        try {
            connexion =  DriverManager.getConnection( url, utilisateur, motDePasse );
            System.out.println("Connexion OK !");
        } catch ( SQLException e ) {
            System.out.println("erreur connexion : " + e.getMessage());
            /* Gérer les éventuelles erreurs ici */
        } 
    }
	public static Connection getConnexion() {
		return connexion;
	}
	public static void setConnexion(Connection connexion) {
		Database.connexion = connexion;
	}
    public static ResultSet getStatement(String sql) {
    	
    	connect();
    	PreparedStatement prepare;
		try {
			prepare = connexion.prepareStatement(sql);
	    	return prepare.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
    }
    
    public static int executeQuery(String sql) {
    	
    	connect();
    	PreparedStatement prepare;
		try {
			prepare = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prepare.executeUpdate();
			ResultSet rs = prepare.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
    }
    
    // Source : https://stackoverflow.com/questions/6514876/most-efficient-conversion-of-resultset-to-json
	public static JSONArray convert( ResultSet rs )
		    throws SQLException, JSONException
		  {
		    JSONArray json = new JSONArray();
		    ResultSetMetaData rsmd = rs.getMetaData();

		    while(rs.next()) {
		      int numColumns = rsmd.getColumnCount();
		      JSONObject obj = new JSONObject();

		      for (int i=1; i<numColumns+1; i++) {
		        String column_name = rsmd.getColumnName(i);

		        if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
		         obj.put(column_name, rs.getArray(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
		         obj.put(column_name, rs.getInt(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
		         obj.put(column_name, rs.getBoolean(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
		         obj.put(column_name, rs.getBlob(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
		         obj.put(column_name, rs.getDouble(column_name)); 
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
		         obj.put(column_name, rs.getFloat(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
		         obj.put(column_name, rs.getInt(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
		         obj.put(column_name, rs.getNString(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
		         obj.put(column_name, rs.getString(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
		         obj.put(column_name, rs.getInt(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
		         obj.put(column_name, rs.getInt(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
		         obj.put(column_name, rs.getDate(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
		        obj.put(column_name, rs.getTimestamp(column_name));   
		        }
		        else{
		         obj.put(column_name, rs.getObject(column_name));
		        }
		      }

		      json.put(obj);
		    }

		    return json;
		  }
	
}
