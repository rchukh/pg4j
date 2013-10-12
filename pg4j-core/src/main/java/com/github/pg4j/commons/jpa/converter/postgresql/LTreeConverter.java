package com.github.pg4j.commons.jpa.converter.postgresql;

import javax.persistence.Converter;

import org.postgresql.util.PGobject;

import com.github.pg4j.commons.jpa.type.LTree;

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
