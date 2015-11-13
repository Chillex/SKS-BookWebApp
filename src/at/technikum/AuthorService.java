package at.technikum;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AuthorService {
	@PersistenceContext
	private EntityManager em;
	
	public Author createAuthor(Author author) {
		em.persist(author);
		
		return author;
	}
	
	public List<Author> getAllAuthors() {
		return em.createNamedQuery("Author.findAll", Author.class)
				 .getResultList();
	}
	
	public Author getAuthor(Integer authorId) {
		return em.find(Author.class, authorId);
	}
	
	public Author updateAuthor(Integer authorId, Author updatedAuthor) {
		Author oldAuthor = em.find(Author.class, authorId);
		
		if(oldAuthor == null || updatedAuthor == null)
			return null;

		oldAuthor.setFirstname(updatedAuthor.getFirstname());
		oldAuthor.setLastname(updatedAuthor.getLastname());
		oldAuthor.setBirthdate(updatedAuthor.getBirthdate());
		
		return oldAuthor;
	}
	
	public void deleteAuthor(Integer authorId) {
		Author author = em.find(Author.class, authorId);
		em.remove(author);
	}
}
