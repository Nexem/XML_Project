package rest.first;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class Pages {
	
	  @Context ServletContext servletContext;
	  @Context HttpServletRequest request;

	  @Path("/")
	  @GET
	  public String showIndex() throws IOException {
		   String base = servletContext.getRealPath("/WEB-INF/classes/rest/first");

		   try {
			   
			   HttpSession session = request.getSession();
			   UserBean user = (UserBean) session.getAttribute("user");
			   File f;
	           if(user == null) f = new File(String.format("%s/%s", base, "pages/home.html"));
	           else f = new File(String.format("%s/%s", base, "pages/home_logged.html"));
	           FileReader fr = new FileReader(f);
	           int i = 0;
	           String str = "";
	           while(( i = fr.read()) != -1)
	               str += (char)i;
	           return str;
	       } catch (FileNotFoundException e) {
	         
	       }
		   return "Go inside -> \n" + base + "\nThen paste folder 'pages'";
	  }
	  @Path("/logout")
	  @GET
	  public String logout() throws IOException {
		   try {
			   String base = servletContext.getRealPath("/WEB-INF/classes/rest/first");
			   
			   HttpSession session = request.getSession();
			   session.invalidate();
			   File f = new File(String.format("%s/%s", base, "pages/home.html"));
	           FileReader fr = new FileReader(f);
	           int i = 0;
	           String str = "";
	           while(( i = fr.read()) != -1)
	               str += (char)i;
	           return str;
	       } catch (FileNotFoundException e) {
	         
	       }
		   return "erreur";
	  }
	  
	  @Path("/add_movie.html")
	  @GET
	  public String showAddMovie() throws IOException {
		   try {
			   String base = servletContext.getRealPath("/WEB-INF/classes/rest/first");
			   
			   
			   HttpSession session = request.getSession();
			   UserBean user = (UserBean) session.getAttribute("user");
			   File f = new File(String.format("%s/%s", base, "pages/add_movie.html"));
	           FileReader fr = new FileReader(f);
	           int i = 0;
	           String str = "";
	           while(( i = fr.read()) != -1)
	               str += (char)i;
	           return str;
	       } catch (FileNotFoundException e) {
	         
	       }
		   return "erreur";
	  }
	  
	  @Path("/modify_movie.html")
	  @GET
	  public String showModifyMovie() throws IOException {
		   try {
			   String base = servletContext.getRealPath("/WEB-INF/classes/rest/first");
			   
			   HttpSession session = request.getSession();
			   UserBean user = (UserBean) session.getAttribute("user");
			   File f = new File(String.format("%s/%s", base, "pages/modify_movie.html"));
	           FileReader fr = new FileReader(f);
	           int i = 0;
	           String str = "";
	           while(( i = fr.read()) != -1)
	               str += (char)i;
	           return str;
	       } catch (FileNotFoundException e) {
	         
	       }
		   return "erreur";
	  }
	  
	  @Path("/search_movie.html")
	  @GET
	  public String showSearchMovie() throws IOException {
		   try {
			   String base = servletContext.getRealPath("/WEB-INF/classes/rest/first");
			   
			   HttpSession session = request.getSession();
			   UserBean user = (UserBean) session.getAttribute("user");
			   File f = new File(String.format("%s/%s", base, "pages/search_movie.html"));
	           FileReader fr = new FileReader(f);
	           int i = 0;
	           String str = "";
	           while(( i = fr.read()) != -1)
	               str += (char)i;
	           return str;
	       } catch (FileNotFoundException e) {
	         
	       }
		   return "erreur";
	  }
	  
	  @Path("/{folder}/{file}")
	  @GET
	  public String showCss(@PathParam("folder") String folder, @PathParam("file") String file) throws IOException {
		   try {
			   String base = servletContext.getRealPath("/WEB-INF/classes/rest/first");
			   System.out.println(base);
	           File f = new File(String.format("%s/%s", base, folder + "/" + file));
	           FileReader fr = new FileReader(f);
	           int i = 0;
	           String str = "";
	           while(( i = fr.read()) != -1)
	               str += (char)i;
	           return str;
	       } catch (FileNotFoundException e) {
	         
		  //servletResponse.sendRedirect("../../index.html");
	       }
		   return "erreur";
		   
		  
	  }
	  
	  @Path("/css/{folder}/{file}")
	  @GET
	  public String showCss2(@PathParam("folder") String folder, @PathParam("file") String file) throws IOException {
		   try {
			   String base = servletContext.getRealPath("/WEB-INF/classes/rest/first");
			   System.out.println(base);
	           File f = new File(String.format("%s/%s", base, "css/" + folder + "/" + file));
	           FileReader fr = new FileReader(f);
	           int i = 0;
	           String str = "";
	           while(( i = fr.read()) != -1)
	               str += (char)i;
	           return str;
	       } catch (FileNotFoundException e) {
	         
		  //servletResponse.sendRedirect("../../index.html");
	       }
		   return "erreur2";
	  }
	  
	  @GET	
	  @Path("/pics/{file}")
	  @Produces("image/png")
	  public Response getFullImage(@PathParam("file") String file) throws IOException {

		  String base = servletContext.getRealPath("/WEB-INF/classes/rest/first");
		  System.out.println(base);
          File f = new File(String.format("%s/%s", base, "pics/" + file));
          
	      BufferedImage image = ImageIO.read(f);

	      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	      ImageIO.write(image, "png", baos);
	      byte[] imageData = baos.toByteArray();

	      // uncomment line below to send non-streamed
	       return Response.ok(imageData).build();

	      // uncomment line below to send streamed
	      // return Response.ok(new ByteArrayInputStream(imageData)).build();
	  }

}
