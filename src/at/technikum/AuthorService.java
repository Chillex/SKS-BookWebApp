package at.technikum;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.SecurityDomain;

@Stateless
@SecurityDomain("BookSD")
public class AuthorService {
	@PersistenceContext
	private EntityManager em;

	@RolesAllowed({"BSWrite", "BSRead"})
	public Author createAuthor(Author author) {
		em.persist(author);
		
		return author;
	}

	@RolesAllowed({"BSWrite", "BSRead"})
	public List<Author> getAllAuthors() {
		return em.createNamedQuery("Author.findAll", Author.class)
				 .getResultList();
	}

	@RolesAllowed({"BSWrite", "BSRead"})
	public Author getAuthor(Integer authorId) {
		return em.find(Author.class, authorId);
	}

	@RolesAllowed("BSWrite")
	public Author updateAuthor(Integer authorId, Author updatedAuthor) {
		Author oldAuthor = em.find(Author.class, authorId);
		
		if(oldAuthor == null || updatedAuthor == null)
			return null;

		oldAuthor.setFirstname(updatedAuthor.getFirstname());
		oldAuthor.setLastname(updatedAuthor.getLastname());
		oldAuthor.setBirthdate(updatedAuthor.getBirthdate());
		
		return oldAuthor;
	}

	@RolesAllowed("BSWrite")
	public void deleteAuthor(Integer authorId) {
		Author author = em.find(Author.class, authorId);
		em.remove(author);
	}
}
