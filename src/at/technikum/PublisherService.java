package at.technikum;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PublisherService {
	@PersistenceContext
	EntityManager em;
	
	public Publisher createPublisher(Publisher publisher) {
		em.persist(publisher);
		
		return publisher;
	}
	
	public List<Publisher> getAllPublishers() {
		return em.createNamedQuery("Publisher.findAll", Publisher.class)
				 .getResultList();
	}
	
	public Publisher getPublisher(Integer publisherId) {
		return em.find(Publisher.class, publisherId);
	}
	
	public Publisher updatePublisher(Integer publisherId, Publisher updatedPublisher) {
		Publisher oldPublisher = em.find(Publisher.class, publisherId);
		
		if(oldPublisher == null || updatedPublisher == null)
			return null;

		oldPublisher.setName(updatedPublisher.getName());
		oldPublisher.setCountrycode(updatedPublisher.getCountrycode());
		oldPublisher.setPostcode(updatedPublisher.getPostcode());
		
		return oldPublisher;
	}
	
	public void deletePublisher(Integer publisherId) {
		Publisher publisher = em.find(Publisher.class, publisherId);
		em.remove(publisher);
	}
}
