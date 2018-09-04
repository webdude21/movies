package eu.webdude.movies.model;

import javax.persistence.*;

@Entity(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "import_foreign_key")
	private String importForeignKey;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "birth_year")
	private String birthYear;

	@Column(name = "death_year")
	private String deathYear;

	@Column(name = "profession")
	private String profession;

	public String getImportForeignKey() {
		return importForeignKey;
	}

	public void setImportForeignKey(String importForeignKey) {
		this.importForeignKey = importForeignKey;
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

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

