package eu.webdude.movies.web.dto;

public class ExceptionDTO {

	private String userMessage;

	private String developerMessage;

	public ExceptionDTO(String userMessage) {
		this(userMessage, null);
	}

	public ExceptionDTO(String userMessage, String developerMessage) {
		this.userMessage = userMessage;
		this.developerMessage = developerMessage;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
}
