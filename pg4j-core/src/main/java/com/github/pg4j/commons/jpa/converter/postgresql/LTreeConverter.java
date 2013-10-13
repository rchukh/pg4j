package com.github.pg4j.commons.jpa.converter.postgresql;

import javax.persistence.Converter;

import org.postgresql.util.PGobject;

import com.github.pg4j.commons.jpa.type.LTree;
import com.google.common.annotations.Beta;

/**
 * LTree converter.
 * 
 * @see <a href="http://www.postgresql.org/docs/9.3/static/ltree.html">PostgreSQL LTree documentation</a>
 */
@Beta
@Converter(autoApply = true)
public class LTreeConverter extends PGObjectConverter<LTree> {
	private static final String TYPE = "ltree";

	@Override
	protected String getType() {
		return TYPE;
	}

	@Override
	protected String getDatabaseValue(LTree attribute) {
		return (attribute == null) ? null : attribute.getPath();
	}

	@Override
	protected LTree getObjectValue(PGobject dbData) {
		return new LTree(dbData.getValue());
	}

}
