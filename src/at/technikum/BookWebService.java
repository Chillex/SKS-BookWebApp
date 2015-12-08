package at.technikum;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface BookWebService {
	@WebMethod
	public List<Book> getAllBooks();
	@WebMethod
	public List<Book> getBooksByTitle(@WebParam(name = "title") String title);
	@WebMethod
	public void addBooks(@WebParam(name = "book") List<Book> books);
}
