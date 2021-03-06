package at.technikum;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.SecurityDomain;

@Stateless
@SecurityDomain("BookSD")
public class BookService {
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private SessionContext ctx;

	@RolesAllowed({"BSWrite", "BSRead"})
	public List<Book> getAllBooks() {
		return em.createNamedQuery("Book.selectAll", Book.class)
				 .getResultList();
	}

	@RolesAllowed({"BSWrite", "BSRead"})
	public List<Book> getBooksByTitle(String title) {
		return em.createNamedQuery("Book.findByTitle", Book.class)
				.setParameter("title", new StringBuilder("%").append(title).append("%").toString())
				.getResultList();
	}

	@RolesAllowed("BSWrite")
	public void addBooks(List<Book> books) {
		try {
			for(Book book : books) {
				Publisher publisher = em.createNamedQuery("Publisher.find", Publisher.class)
										.setParameter("name", book.getPublisher().getName())
										.setParameter("postcode", book.getPublisher().getPostcode())
										.setParameter("countrycode", book.getPublisher().getCountrycode())
										.getSingleResult();
				
				List<Author> authors = new ArrayList<Author>();
				for(Author author: book.getAuthors()) {
					Author a = em.createNamedQuery("Author.find", Author.class)
								.setParameter("firstname", author.getFirstname())
								.setParameter("lastname", author.getLastname())
								.setParameter("birthdate", author.getBirthdate())
								.getSingleResult();
					
					authors.add(a);
				}
				
				book.setPublisher(publisher);
				book.setAuthors(authors);
				
				em.persist(book);
			}
		} catch(NoResultException e) {
			ctx.setRollbackOnly();
		}
	}
}
