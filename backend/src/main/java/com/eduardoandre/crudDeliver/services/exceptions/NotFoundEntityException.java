package com.eduardoandre.crudDeliver.services.exceptions;

public class NotFoundEntityException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NotFoundEntityException(String msg) {
		super(msg);
	} 

}
