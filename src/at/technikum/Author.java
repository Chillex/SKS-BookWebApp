package at.technikum;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@NamedQueries({
	@NamedQuery(name = "Author.find", query = "SELECT a FROM Author a WHERE a.firstname = :firstname AND a.lastname = :lastname AND a.birthdate = :birthdate"),
	@NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a")
})
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute(required = true)
	@XmlJavaTypeAdapter(type = Integer.class, value = IntegerAdapter.class)
	protected Integer id;

	@XmlAttribute(required = true)
	protected String firstname;
	
	@XmlAttribute(required = true)
	protected String lastname;
	
	@XmlAttribute(required = true)
	@XmlJavaTypeAdapter(type = Date.class, value = DateAdapter.class)
	protected Date birthdate;
	
	@ManyToMany(mappedBy = "authors")
	@XmlTransient
	protected List<Book> books;
	
	public Author() {}
	
	public Author(String firstname, String lastname, Date birthdate) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	@Override
	public String toString() {
		return "Author [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", birthdate=" + birthdate + ", books=" + books
				+ "]";
	}
	
}
