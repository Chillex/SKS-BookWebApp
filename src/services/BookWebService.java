package services;

import java.util.List;

import javax.jws.WebService;

import entities.Book;

@WebService
public interface BookWebService {
	public List<Book> getAllBooks();
	public List<Book> getBooksByTitle(String title);
}
