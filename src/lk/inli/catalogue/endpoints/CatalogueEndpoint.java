package lk.inli.catalogue.endpoints;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import lk.inli.catalogue.model.Product;
import lk.inli.catalogue.model.ProductAccessible;
import lk.inli.catalogue.model.ProductArrayAccess;

@Path("movies")
final public class CatalogueEndpoint {

	final private Date moviesUpdatedDate = new Date();
	final private ProductAccessible dataAccess = new ProductArrayAccess();
	private static final int STARTING_OFFSET = 0;
	private static final int PAGE_SIZE = 4;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovies(@Context UriInfo uri, @QueryParam("offset") @DefaultValue("0") long offset) {
		// Calculate the effective offset
		long datasetSize = dataAccess.getMovieListSize();
		long start = offset;
		if (start < STARTING_OFFSET) {
			start = STARTING_OFFSET;
		}
		if (start >= datasetSize) {
			start = datasetSize;
		}

		// Setup navigation links
		Link selfLink = Link.fromUri(uri.getBaseUri() + "movies?offset={offset}").rel("self")
				.type(MediaType.APPLICATION_JSON).build(offset);
		long nextOffset = (offset + PAGE_SIZE < datasetSize) ? offset + PAGE_SIZE
				: PAGE_SIZE * (datasetSize / PAGE_SIZE);
		Link nextLink = Link.fromUri(uri.getBaseUri() + "movies?offset={offset}").rel("next")
				.type(MediaType.APPLICATION_JSON).build(nextOffset);
		long prevOffset = (offset - PAGE_SIZE > STARTING_OFFSET) ? offset - PAGE_SIZE : STARTING_OFFSET;
		Link prevLink = Link.fromUri(uri.getBaseUri() + "movies?offset={offset}").rel("prev")
				.type(MediaType.APPLICATION_JSON).build(prevOffset);
		Link firstLink = Link.fromUri(uri.getBaseUri() + "movies?offset={offset}").rel("first")
				.type(MediaType.APPLICATION_JSON).build(STARTING_OFFSET);
		Link lastLink = Link.fromUri(uri.getBaseUri() + "movies?offset={offset}").rel("last")
				.type(MediaType.APPLICATION_JSON).build(PAGE_SIZE * (datasetSize / PAGE_SIZE));
		Link countLink = Link.fromUri(uri.getBaseUri() + "movies/count").rel("count").type(MediaType.APPLICATION_JSON)
				.build();
		Link rndLink = Link.fromUri(uri.getBaseUri() + "movies/random").rel("random").type(MediaType.APPLICATION_JSON)
				.build();

		// Get the list of movies from starting point
		List<Product> list = dataAccess.getMovieList(start);
		return Response.ok(list).header("movie-count", datasetSize).header("current-movie-list-size", list.size())
				.header("offset", start).links(selfLink, nextLink, prevLink, firstLink, lastLink, countLink, rndLink)
				.lastModified(moviesUpdatedDate).location(uri.getRequestUri()).build();
	}
	
	@GET
	@Path("type")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchMovies(@Context UriInfo uri, @QueryParam("filter") String filter) {
		// Get the list of movies filtered
		List<Product> list = dataAccess.getMovieList(0).stream()
				.filter(movie -> movie.getType().equalsIgnoreCase(filter))
				.collect(Collectors.toList());
		
		if (null == list || list.size() == 0) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.ok(list).build();
		}
	}

	@GET
	@Path("count")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovieCount(@Context UriInfo uri) {
		long numberOfMovies = dataAccess.getMovieListSize();
		return Response.ok(numberOfMovies).header("movie-count", numberOfMovies).lastModified(moviesUpdatedDate)
				.location(uri.getRequestUri()).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovie(@Context UriInfo uri, @PathParam("id") String idString) {
		Response response;
		if (idString.trim().equalsIgnoreCase("random")) {
			Product movie = dataAccess.getRandomMovie();
			response = Response.ok(movie).lastModified(movie.getLastUpdated()).location(uri.getRequestUri()).build();
		} else {
			try {
				long identifier = Long.parseLong(idString);
				if (identifier >= dataAccess.getMovieListSize()) {
					response = Response.status(Response.Status.NOT_FOUND).build();
				} else {
					Product movie = dataAccess.getMovieById(identifier);
					response = Response.ok(movie).lastModified(movie.getLastUpdated()).location(uri.getRequestUri())
							.build();
				}
			} catch (NumberFormatException ne) {
				response = Response.status(Response.Status.BAD_REQUEST).build();
			}

		}
		return response;
	}
}