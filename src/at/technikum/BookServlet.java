package at.technikum;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/books")
@SuppressWarnings("serial")
public class BookServlet extends HttpServlet {
	
	@Inject
	private BookService bookService;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Book Web Service</title>");
		out.println("</head>");
		out.println("<body>");
		
		out.print("<h1>All Books:</h1>");
		
		for (Book book : bookService.getAllBooks()) {
			out.print("<h2>" + book.getTitle());
			out.print("</h2>");
		}
		
		out.println("</body>");
		out.println("</html>");
	}
}
