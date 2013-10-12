package com.github.pg4j.commons.hibernate.function.ltree;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.type.BooleanType;
import org.hibernate.type.Type;

public abstract class LTreeFunction implements SQLFunction {
	private String firstParam;
	private String secondParam;

	@Override
	public boolean hasArguments() {
		return true;
	}

	@Override
	public boolean hasParenthesesIfNoArguments() {
		return false;
	}

	@Override
	public Type getReturnType(Type firstArgumentType, Mapping mapping) throws QueryException {
		return new BooleanType();
	}

	public String getFirstParam() {
		return this.firstParam;
	}

	public String getSecondParam() {
		return this.secondParam;
	}

	protected void prepare(List<?> args) throws QueryException {
		if (args.size() != 2) {
			throw new IllegalArgumentException("The function must be passed 2 arguments");
		}
		this.firstParam = (String) args.get(0);
		this.secondParam = (String) args.get(1);

		if (this.getFirstParam() == null || this.getFirstParam().isEmpty() || this.getSecondParam() == null
				|| this.getSecondParam().isEmpty()) {
			throw new IllegalArgumentException("The function must be passed 2 arguments with NOT_EMPTY values.");
		}
	}

	protected String prepareParam(String param) {
		StringBuilder builder = new StringBuilder();
		// TODO: MAJOR | Other possible input params?
		if (param.equals("?")) {
			// If this is some text value, convert to ltree
			builder.append("text2ltree(");
			builder.append(param);
			builder.append(")");
		} else {
			// If this is path expression, use directly
			builder.append(param);
		}
		return builder.toString();
	}

}
