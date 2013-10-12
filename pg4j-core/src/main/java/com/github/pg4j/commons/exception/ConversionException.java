package com.github.pg4j.commons.exception;

/**
 * Exception which can be throwed by {@link javax.persistence.Converter} implementation to specify the conversion
 * problems.
 */
public class ConversionException extends RuntimeException {
	public ConversionException(String message) {
		super(message);
	}

	public ConversionException(String message, Throwable cause) {
		super(message, cause);
	}
}
