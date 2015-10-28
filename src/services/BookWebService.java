package services;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import entities.Book;

@WebService
public interface BookWebService {
	public List<Book> getAllBooks();
	public List<Book> getBooksByTitle(@WebParam(name = "title") String title);
	public void addBooks(@WebParam(name = "book") List<Book> books);
}
