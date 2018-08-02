package lk.inli.catalogue;

import java.util.Date;
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * JAX-RS application. Root path is /movies
 *
 */

@Path("info")
public class CatalogueApp extends Application {

	public CatalogueApp() {
	}

	@GET
	@Path("hello")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchByName(@QueryParam("name") String name) {

		return "Hello, movies";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDirectory(@Context UriInfo uri) {
		Link selfLink = Link.fromUri(uri.getBaseUri()).rel("self").type(MediaType.APPLICATION_JSON).build();
		Link moviesLink = Link.fromUri(uri.getBaseUri() + "movies").rel("movies")
				.type(MediaType.APPLICATION_JSON).build();

		return Response.ok().lastModified(new Date())
				.header("catalogue-version", "v1")
				.location(uri.getRequestUri()).links(selfLink, moviesLink).build();
	}

}