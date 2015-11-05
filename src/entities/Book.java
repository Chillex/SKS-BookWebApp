package entities;

import java.util.Date;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import util.DateAdapter;
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
	@XmlAttribute(required = true)
	@XmlJavaTypeAdapter(type = Integer.class, value = IntegerAdapter.class)
	protected Integer id;

	@XmlAttribute(required = true)
	protected String isbn;
	
	@XmlAttribute(required = true)
	protected String title;
	
	@XmlAttribute(required = true)
	protected String subtitle;
	
	@XmlAttribute(required = true)
	protected String description;
	
	@XmlAttribute(required = true)
	protected int pages;
	
	@XmlAttribute(required = true)
	protected String language;

	// NOT NEEDED ACCORDING TO XML - WHAT TO DO?
	/*@XmlAttribute(required = true)
	@XmlJavaTypeAdapter(type = Date.class, value = DateAdapter.class)
	protected Date publishDate;*/
	
	// NOT NEEDED ACCORDING TO XML - WHAT TO DO?
	/*@XmlAttribute(required = true)
	@Lob
	protected String storyline;*/
	
	@ManyToMany(fetch = FetchType.EAGER)
	@XmlElementWrapper(name = "authors")
	@XmlElement(name = "author")
	protected List<Author> authors;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@XmlElement(required = true)
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
	
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	/*public Date getPublishDate() {
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
	}*/

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
