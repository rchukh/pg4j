package com.github.pg4j.commons.jpa.converter.postgresql;

import java.sql.SQLException;

import javax.persistence.AttributeConverter;

import org.postgresql.util.PGobject;

import com.github.pg4j.commons.exception.ConversionException;

/**
 * <p>
 * Abstract implementation of misc. {@link PGobject} converter. Can be used to create new PostgreSQL type support.
 * </p>
 * Limitations:
 * <ul>
 * <li>{@link PGobject} uses {@link String} as value</li>
 * </ul>
 * 
 * @param <T>
 *            Attribute class
 */
public abstract class PGObjectConverter<T> implements AttributeConverter<T, PGobject> {

	@Override
	public PGobject convertToDatabaseColumn(T attribute) {
		PGobject dbData;
		try {
			dbData = new PGobject();
			dbData.setType(getType());
			dbData.setValue(getDatabaseValue(attribute));
		} catch (SQLException e) {
			throw new ConversionException("Conversion to database failed: " + e.getMessage(), e);
		}
		return dbData;
	}

	@Override
	public T convertToEntityAttribute(PGobject dbData) {
		if (dbData == null) {
			return null;
		}
		// Fail-fast on wrong type
		if (!dbData.getType().equals(getType())) {
			throw new ConversionException("Conversion from database failed: Different database type found.");
		}
		return getObjectValue(dbData);
	}

	protected abstract String getType();

	protected abstract String getDatabaseValue(/* @Nullable */T attribute);

	protected abstract T getObjectValue(/* @NotNull */PGobject dbData);
}
