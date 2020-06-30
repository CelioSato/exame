package com.celiosato.exames.services.exception;

public class EmptyResultException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EmptyResultException(String msg) {
		super(msg);
	}
	
	public EmptyResultException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
