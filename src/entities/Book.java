package entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	
	protected String title;
	protected String isbn;
	protected Date publishDate;
	protected String storyline;
	protected List<Author> authors;
	protected Publisher publisher;
}
