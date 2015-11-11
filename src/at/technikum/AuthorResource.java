package at.technikum;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/author")
@Transactional
public class AuthorResource {
	@PersistenceContext
	EntityManager em;
	@Context
	UriInfo ui;
	@Inject
	BookService bookService;
	
	@GET
	@Path("/{authorId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String getAuthorAsString(@PathParam("authorId") Long authorId) {
		Author author = em.find(Author.class, authorId);
		return (author != null ? author.toString() : null);
	}
	
	@GET
	@Path("/{authorId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Author getAuthorAsJSONXML(@PathParam("authorId") Long authorId) {
		return em.find(Author.class, authorId);
	}
	
}
