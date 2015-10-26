package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Book;

@Stateless
public class BookService {
	@PersistenceContext
	private EntityManager em;
	
	public List<Book> getAllBooks() {
		return em.createNamedQuery("Book.selectAll", Book.class)
				 .getResultList();
	}
	
	public List<Book> getBooksByTitle(String title) {
		return em.createNamedQuery("Book.findByTitle", Book.class)
				.setParameter("title", new StringBuilder("%").append(title).append("%").toString())
				.getResultList();
	}
}
