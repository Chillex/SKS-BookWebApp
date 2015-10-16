package entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	
	protected String firstname;
	protected String lastname;
	protected Date birthdate;
}
