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

@Path("/publisher")
@Transactional
public class PublisherResource {
	@PersistenceContext
	EntityManager em;
	@Context
	UriInfo ui;
	@Inject
	BookService bookService;
	
	@GET
	@Path("/{publisherId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String getPublisherAsString(@PathParam("publisherId") Long publisherId) {
		Publisher publisher = em.find(Publisher.class, publisherId);
		return (publisher != null ? publisher.toString() : null);
	}
	
	@GET
	@Path("/{publisherId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Publisher getPublisherAsJSONXML(@PathParam("publisherId") Long publisherId) {
		return em.find(Publisher.class, publisherId);
	}
}
