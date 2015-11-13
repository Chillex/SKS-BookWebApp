package at.technikum;

import java.net.URI;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

@Path("/author")
@Transactional
public class AuthorResource {
	@Context
	UriInfo ui;
	@Inject
	AuthorService authorService;
	
	// create
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createAuthor(Author newAuthor) {
		Author author = authorService.createAuthor(newAuthor);
		
		URI authorURI = ui.getAbsolutePathBuilder()
						  .path(author.getId().toString())
						  .build();
		
		return Response.created(authorURI)
					   .entity(author)
					   .build();
	}
	
	// retrieve
	@GET
	@Path("/{authorId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAuthorAsJSONXML(@PathParam("authorId") Integer authorId) {
		Author author = authorService.getAuthor(authorId);
		
		if(author == null)
			throw new WebApplicationException(Status.NOT_FOUND);
		
		return Response.ok(author)
					   .build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAllAuthors() {
		return Response.ok(authorService.getAllAuthors())
					   .build();
	}
	
	// update
	@PUT
	@Path("/{authorId}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateAuthor(@PathParam("authorId") Integer authorId, Author updatedAuthor) {
		Author author = authorService.updateAuthor(authorId, updatedAuthor);
		
		if(author == null)
			throw new WebApplicationException(Status.NOT_FOUND);
		
		return Response.ok(author)
					   .build();
	}
	
	// delete
	@DELETE
	@Path("/{authorId}")
	public Response deleteAuthor(@PathParam("authorId") Integer authorId) {
		authorService.deleteAuthor(authorId);
		
		return Response.ok().build();
	}
}
