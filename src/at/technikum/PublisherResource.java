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
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

@Path("/publisher")
@Transactional
public class PublisherResource {
	@Context
	UriInfo ui;
	@Inject
	PublisherService publisherService;
	
	// create
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createPublisher(Publisher newPublisher) {
		Publisher publisher = publisherService.createPublisher(newPublisher);
		
		URI publisherURI = ui.getAbsolutePathBuilder()
						  .path(publisher.getId().toString())
						  .build();
		
		return Response.created(publisherURI)
					   .entity(publisher)
					   .build();
	}
	
	// retrieve
	@GET
	@Path("/{publisherId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getPublisherAsJSONXML(@PathParam("publisherId") Integer publisherId) {
		Publisher publisher = publisherService.getPublisher(publisherId);
		
		if(publisher == null)
			throw new WebApplicationException(Status.NOT_FOUND);
		
		return Response.ok(publisher)
					   .build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAllPublishers() {
		return Response.ok(publisherService.getAllPublishers())
					   .build();
	}
	
	// update
	@PUT
	@Path("/{publisherId}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updatePublisher(@PathParam("publisherId") Integer publisherId, Publisher updatedPublisher) {
		Publisher publisher = publisherService.updatePublisher(publisherId, updatedPublisher);
		
		if(publisher == null)
			throw new WebApplicationException(Status.NOT_FOUND);
		
		return Response.ok(publisher)
					   .build();
	}
	
	// delete
	@DELETE
	@Path("/{publisherId}")
	public Response deletePublisher(@PathParam("publisherId") Integer publisherId) {
		publisherService.deletePublisher(publisherId);
		
		return Response.ok().build();
	}
}
