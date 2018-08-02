package lk.inli.catalogue;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//@ApplicationPath("services")
//@Path("movies")
public class MovieService {

	public MovieService() {
	}

	//@GET
	//@Path("hello")
	//@Produces(MediaType.APPLICATION_JSON)
	public String searchByName(@QueryParam("name") String name) {

		return "Hello, movies";
	}

}
