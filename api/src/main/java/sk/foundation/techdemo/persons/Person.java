package sk.foundation.techdemo.persons;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;
import sk.foundation.techdemo.infrastructure.db.IdentifiableEntity;

@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(
		name = "PERSON",
		uniqueConstraints = { @UniqueConstraint(name = "UK_EMAIL", columnNames = "EMAIL") },
		indexes = { @Index(name = "IDX_LN_FN", columnList = "LAST_NAME asc, FIRST_NAME asc") })
public class Person extends IdentifiableEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "STATE")
	private String state;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

}
