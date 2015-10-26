package entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import util.IntegerAdapter;

@Entity
@NamedQueries({
	@NamedQuery(name="Book.selectAll", query="SELECT b FROM Book b"),
	@NamedQuery(name="Book.findByTitle", query="SELECT b FROM Book b WHERE b.title LIKE :title")
})
@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlID
	@XmlAttribute
	@XmlJavaTypeAdapter(type = Integer.class, value = IntegerAdapter.class)
	protected Integer id;
	
	protected String title;
	protected String isbn;
	protected Date publishDate;
	@Lob
	protected String storyline;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@XmlIDREF
	@XmlElementWrapper(name = "authors")
	@XmlElement(name = "author")
	protected List<Author> authors;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@XmlIDREF
	protected Publisher publisher;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getStoryline() {
		return storyline;
	}

	public void setStoryline(String storyline) {
		this.storyline = storyline;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
}
