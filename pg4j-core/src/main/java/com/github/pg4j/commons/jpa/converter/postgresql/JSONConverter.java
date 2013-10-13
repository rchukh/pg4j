package com.github.pg4j.commons.jpa.converter.postgresql;

import java.io.IOException;

import org.postgresql.util.PGobject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pg4j.commons.exception.ConversionException;
import com.google.common.annotations.Beta;

@Beta
public abstract class JSONConverter<T> extends PGObjectConverter<T> {
	private static final String TYPE = "json";

	@Override
	protected String getType() {
		return TYPE;
	}

	@Override
	protected String getDatabaseValue(T attribute) {
		try {
			return getObjectMapper().writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			throw new ConversionException("Conversion to database failed: " + e.getMessage(), e);
		}
	}

	@Override
	protected T getObjectValue(PGobject dbData) {
		try {
			return getObjectMapper().readValue(dbData.getValue(), getObjectClass());
		} catch (IOException e) {
			throw new ConversionException("Conversion from database failed: " + e.getMessage(), e);
		}
	}

	/**
	 * Provides {@link ObjectMapper} which has all modules required to convert current {@code T} attribute to JSON.
	 * 
	 * @return ObjectMapper
	 */
	protected abstract ObjectMapper getObjectMapper();

	/**
	 * Provides current {@code T}'s {@link Class}. <b>This is required due to type-erasure.</b>
	 * 
	 * @return {@link Class} of {@link T}
	 */
	protected abstract Class<T> getObjectClass();
}
