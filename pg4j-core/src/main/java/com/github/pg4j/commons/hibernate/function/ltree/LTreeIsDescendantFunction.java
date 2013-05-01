package com.github.pg4j.commons.hibernate.function.ltree;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.Type;

/**
 * Hibernate support for <code>ltree <@ ltree</code> PostgreSql functionality.
 * 
 * @author "Roman Chukh <roman.chukh@gmail.com>"
 */
public final class LTreeIsDescendantFunction extends LTreeFunction {
    @Override
    public String render(Type firstArgumentType,
	    @SuppressWarnings("rawtypes") List args,
	    SessionFactoryImplementor factory) throws QueryException {
	// Null check, conversion, etc.
	this.prepare(args);

	StringBuilder result = new StringBuilder();
	result.append("(");
	result.append(this.prepareParam(this.getFirstParam()));
	result.append(" <@ ");
	result.append(this.prepareParam(this.getSecondParam()));
	return result.append(")").toString();
    }
}
