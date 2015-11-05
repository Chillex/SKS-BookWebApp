package at.technikum;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface BookWebService {
	public List<Book> getAllBooks();
	public List<Book> getBooksByTitle(@WebParam(name = "title") String title);
	public void addBooks(@WebParam(name = "book") List<Book> books);
}
