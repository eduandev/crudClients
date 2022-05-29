package com.eduardoandre.crudDeliver.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eduardoandre.crudDeliver.services.exceptions.NotFoundEntityException;

public class ResourceExceptionHandler {

	@ExceptionHandler(NotFoundEntityException.class)
	public ResponseEntity<StandardError> entityNotFond(NotFoundEntityException e, HttpServletRequest request) {

		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Pesquisa não encontrada");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
