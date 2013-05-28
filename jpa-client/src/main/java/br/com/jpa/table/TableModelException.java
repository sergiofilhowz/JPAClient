package br.com.jpa.table;

public class TableModelException extends RuntimeException {

	private static final long serialVersionUID = 9202738892993735501L;

	public TableModelException(String message, Throwable cause) {
		super(message, cause);
	}

	public TableModelException(String message) {
		super(message);
	}

	public TableModelException(Throwable cause) {
		super(cause);
	}

}
