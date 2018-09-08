package eu.webdude.movies.web.rest;

import eu.webdude.movies.web.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.jms.JmsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(JmsException.class)
	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
	@ResponseBody
	public ExceptionDTO jmsException(HttpServletRequest req, JmsException ex) {
		ex.printStackTrace();
		return new ExceptionDTO("The system cannot currently accept your request." +
			" The service that should handle this request is not available!", ex.getMessage());
	}
}
