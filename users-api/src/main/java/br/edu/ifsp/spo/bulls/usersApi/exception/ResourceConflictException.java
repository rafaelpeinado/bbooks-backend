package br.edu.ifsp.spo.bulls.usersApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceConflictException extends RuntimeException {
    private static final long serialVersionUID = 252538959719425669L;

	public ResourceConflictException(String message) {
		super(message);
	}
   
}