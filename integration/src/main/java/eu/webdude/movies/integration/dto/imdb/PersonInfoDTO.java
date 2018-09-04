package eu.webdude.movies.integration.dto.imdb;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = "\t")
public class PersonInfoDTO {

	@DataField(name = "nconst", pos = 1, trim = true)
	private String nameRelation;

	@DataField(name = "primaryName", pos = 2, trim = true)
	private String name;

	@DataField(name = "birthYear", pos = 3, trim = true)
	private String birthYear;

	@DataField(name = "deathYear", pos = 4, trim = true)
	private String deathYear;

	@DataField(name = "primaryProfession", pos = 5, trim = true)
	private String primaryProfession;

	@DataField(name = "knownForTitles", pos = 6, trim = true)
	private String knownForTitles;

	public String getNameRelation() {
		return nameRelation;
	}

	public void setNameRelation(String nameRelation) {
		this.nameRelation = nameRelation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPrimaryProfession() {
		return primaryProfession;
	}

	public void setPrimaryProfession(String primaryProfession) {
		this.primaryProfession = primaryProfession;
	}

	public String getKnownForTitles() {
		return knownForTitles;
	}

	public void setKnownForTitles(String knownForTitles) {
		this.knownForTitles = knownForTitles;
	}
}
