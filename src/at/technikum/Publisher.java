package at.technikum;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@NamedQueries({
	@NamedQuery(name = "Publisher.find", query = "SELECT p FROM Publisher p WHERE p.name = :name AND p.postcode = :postcode AND p.countrycode = :countrycode"),
	@NamedQuery(name = "Publisher.findAll", query = "SELECT p FROM Publisher p")
})
@XmlAccessorType(XmlAccessType.FIELD)
public class Publisher {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute(required = true)
	@XmlJavaTypeAdapter(type = Integer.class, value = IntegerAdapter.class)
	protected Integer id;

	@XmlAttribute(required = true)
	protected String name;
	
	@XmlAttribute(required = true)
	protected String postcode;
	
	@XmlAttribute(required = true)
	protected String countrycode;
	
	@OneToMany(mappedBy = "publisher")
	@XmlTransient
	protected List<Book> books;
	
	public Publisher() {}
	
	public Publisher(String name, String postcode, String countrycode) {
		this.name = name;
		this.postcode = postcode;
		this.countrycode = countrycode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", name=" + name + ", postcode="
				+ postcode + ", countrycode=" + countrycode + ", books="
				+ books + "]";
	}
	
	/*public String toString() {
		return name + ": " + postcode + " / " + countrycode;
	}*/
}
