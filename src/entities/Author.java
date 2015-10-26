package entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import util.IntegerAdapter;

@Entity
@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlID
	@XmlAttribute
	@XmlJavaTypeAdapter(type = Integer.class, value = IntegerAdapter.class)
	protected Integer id;
	
	protected String firstname;
	protected String lastname;
	protected Date birthdate;
	
	@ManyToMany(mappedBy = "authors")
	@XmlIDREF
	@XmlElementWrapper(name = "books")
	protected List<Book> books;
	
	public Author() {}
	
	public Author(String firstname, String lastname, Date birthdate) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
