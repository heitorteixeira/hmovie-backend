package br.com.hmovie.exception;

public class HmovieException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public HmovieException(String msg) {
		super(msg);
	}
	
	public HmovieException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
