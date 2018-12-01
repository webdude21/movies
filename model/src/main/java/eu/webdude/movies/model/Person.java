package eu.webdude.movies.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "person")
public class Person extends RemoteReferencedModel {

	static final String ID_NAME = "person_id";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = ID_NAME)
	private Long id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "birth_year")
	private String birthYear;

	@Column(name = "death_year")
	private String deathYear;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "person_profession", joinColumns = @JoinColumn(name = ID_NAME), inverseJoinColumns = @JoinColumn(name = Profession.ID_NAME))
	private Set<Profession> professions = new HashSet<>();

	public static String getIdName() {
		return ID_NAME;
	}

	public Set<Profession> getProfessions() {
		return professions;
	}

	public void setProfessions(Set<Profession> professions) {
		this.professions = professions;
	}

	public void addProfession(Profession profession) {
		professions.add(profession);
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getDeathYear() {
		return deathYear;
	}

	public void setDeathYear(String deathYear) {
		this.deathYear = deathYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (!(o instanceof Person)) return false;

		Person person = (Person) o;

		return new EqualsBuilder()
			.append(getId(), person.getId())
			.append(getFullName(), person.getFullName())
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(getId())
			.append(getFullName())
			.toHashCode();
	}
}