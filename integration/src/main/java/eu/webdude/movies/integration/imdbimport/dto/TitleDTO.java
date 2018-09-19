package eu.webdude.movies.integration.imdbimport.dto;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = "\t")
public class TitleDTO {

	@DataField(name = "titleId", trim = true, pos = 1)
	private String titleId;

	@DataField(name = "ordering", trim = true, pos = 2)
	private int ordering;

	@DataField(name = "title", trim = true, pos = 3)
	private String title;

	@DataField(name = "region", trim = true, pos = 4)
	private String region;

	@DataField(name = "language", trim = true, pos = 5)
	private String language;

	@DataField(name = "types", trim = true, pos = 6)
	private String types;

	@DataField(name = "attributes", trim = true, pos = 7)
	private String attributes;

	@DataField(name = "isOriginalTitle", trim = true, pos = 8)
	private boolean isOriginalTitle;

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String[] getTypes() {
		return types.split(",");
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String[] getAttributes() {
		return attributes.split(",");
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public boolean isOriginalTitle() {
		return isOriginalTitle;
	}

	public void setOriginalTitle(boolean originalTitle) {
		isOriginalTitle = originalTitle;
	}
}
