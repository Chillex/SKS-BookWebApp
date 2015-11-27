package at.technikum;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.SecurityDomain;

@Stateless
@SecurityDomain("BookSD")
public class PublisherService {
	@PersistenceContext
	EntityManager em;

	@RolesAllowed("BSWrite")
	public Publisher createPublisher(Publisher publisher) {
		em.persist(publisher);
		
		return publisher;
	}

	@RolesAllowed({"BSWrite", "BSRead"})
	public List<Publisher> getAllPublishers() {
		return em.createNamedQuery("Publisher.findAll", Publisher.class)
				 .getResultList();
	}

	@RolesAllowed({"BSWrite", "BSRead"})
	public Publisher getPublisher(Integer publisherId) {
		return em.find(Publisher.class, publisherId);
	}

	@RolesAllowed("BSWrite")
	public Publisher updatePublisher(Integer publisherId, Publisher updatedPublisher) {
		Publisher oldPublisher = em.find(Publisher.class, publisherId);
		
		if(oldPublisher == null || updatedPublisher == null)
			return null;

		oldPublisher.setName(updatedPublisher.getName());
		oldPublisher.setCountrycode(updatedPublisher.getCountrycode());
		oldPublisher.setPostcode(updatedPublisher.getPostcode());
		
		return oldPublisher;
	}

	@RolesAllowed("BSWrite")
	public void deletePublisher(Integer publisherId) {
		Publisher publisher = em.find(Publisher.class, publisherId);
		em.remove(publisher);
	}
}
