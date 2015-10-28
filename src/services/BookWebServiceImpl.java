package services;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebService;

import entities.Book;

@WebService(endpointInterface = "services.BookWebService", portName = "BookWebServicePort", serviceName = "BookWebService")
public class BookWebServiceImpl implements BookWebService {
	@Inject
	private BookService bookService;
	
	@Override
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@Override
	public List<Book> getBooksByTitle(@WebParam(name = "title") String title) {
		return bookService.getBooksByTitle(title);
	}

	@Override
	public void addBooks(@WebParam(name = "book") List<Book> books) {
		bookService.addBooks(books);
	}
}
