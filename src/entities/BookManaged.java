package entities;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import services.BookService;

@RequestScoped
@Named("reqBook")
public class BookManaged {
	@Inject
	BookService bookService;
	private List<Book> allBooks;
	
	public List<Book> getAllBooks() {
		if(allBooks == null)
			allBooks = bookService.getAllBooks();
		
		return allBooks;
	}
	
	public List<Book> getBooksByTitle(String title) {
		return bookService.getBooksByTitle(title);
	}
}
