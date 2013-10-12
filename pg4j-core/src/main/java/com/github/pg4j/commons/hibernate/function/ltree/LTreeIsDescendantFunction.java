package com.github.pg4j.commons.hibernate.function.ltree;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.Type;

/**
 * Hibernate support for {@code ltree <@ ltree} PostgreSql functionality.
 *
 * @author <a href="mailto:roman.chukh@gmail.com">Roman Chukh</a>
 */
public final class LTreeIsDescendantFunction extends LTreeFunction {
	private static final String FORMAT = "(%s <@ %s)";

	@Override
	public String render(Type firstArgumentType, @SuppressWarnings("rawtypes") List args,
			SessionFactoryImplementor factory) throws QueryException {
		// Null check, conversion, etc.
		this.prepare(args);
		String firstParam = this.prepareParam(this.getFirstParam());
		String secondParam = this.prepareParam(this.getSecondParam());
		return String.format(FORMAT, firstParam, secondParam);
	}
}
