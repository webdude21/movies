package eu.webdude.movies.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profession", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Profession extends BaseModel {

	static final String ID_NAME = "profession_id";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = ID_NAME)
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "person_profession", joinColumns = @JoinColumn(name = ID_NAME), inverseJoinColumns = @JoinColumn(name = Person.ID_NAME))
	private Set<Person> people = new HashSet<>();

	public Profession(String name) {
		this.name = name;
	}

	public Profession() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Person> getPeople() {
		return people;
	}

	public void setPeople(Set<Person> people) {
		this.people = people;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (!(o instanceof Profession)) return false;

		Profession that = (Profession) o;

		return new EqualsBuilder()
			.append(getId(), that.getId())
			.append(getName(), that.getName())
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(getId())
			.append(getName())
			.toHashCode();
	}
}
