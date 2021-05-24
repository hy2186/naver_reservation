package com.ntscorp.reservation.exception.custom;

public class FileLoadException extends RuntimeException {
	public FileLoadException(Throwable exception) {
		super("File Load Error!", exception);
	}
}
