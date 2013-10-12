package com.github.pg4j.commons.hibernate.dialect;

import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.BooleanType;

import com.github.pg4j.commons.hibernate.Functions;
import com.github.pg4j.commons.hibernate.function.ltree.LTreeIsAncestorFunction;
import com.github.pg4j.commons.hibernate.function.ltree.LTreeIsDescendantFunction;

/**
 * <p>
 * Hibernate {@link org.hibernate.dialect.PostgreSQL9Dialect} extended with "ltree" functionality.
 * </p>
 * Supports:
 * <ul>
 * <li>{@code ltree @> ltree} - {@link Functions#LTREE_IS_DESCENDANT}</li>
 * <li>{@code ltree <@ ltree} - {@link Functions#LTREE_IS_ANCESTOR}</li>
 * <li>{@code ltree ~ lquery} - {@link Functions#LTREE_LQUERY_MATCH}</li>
 * <li>{@code lquery ~ ltree} - {@link Functions#LQUERY_LTREE_MATCH}</li>
 * <li>{@code ltree @ ltxtquery} - {@link Functions#LTREE_LXTQUERY_MATCH}</li>
 * <li>{@code ltxtquery @ ltree} - {@link Functions#LXTQUERY_LTREE_MATCH}</li>
 * </ul>
 * 
 * @author <a href="mailto:roman.chukh@gmail.com">Roman Chukh</a>
 */
public class ExtendedPostgreSQL9Dialect extends PostgreSQL9Dialect {

	public ExtendedPostgreSQL9Dialect() {
		this.registerFunction(Functions.LTREE_IS_ANCESTOR, new LTreeIsAncestorFunction());
		this.registerFunction(Functions.LTREE_IS_DESCENDANT, new LTreeIsDescendantFunction());
		this.registerFunction(Functions.LTREE_LQUERY_MATCH, new SQLFunctionTemplate(new BooleanType(),
				"(?1 ~ ?2::lquery)"));
		this.registerFunction(Functions.LQUERY_LTREE_MATCH, new SQLFunctionTemplate(new BooleanType(),
				"(?1::lquery ~ ?2)"));
		// TODO: Test ltree @ ltxtquery
		this.registerFunction(Functions.LTREE_LXTQUERY_MATCH, new SQLFunctionTemplate(new BooleanType(),
				"(?1 @ ?2::ltxtquery)"));
		// TODO: Test ltxtquery @ ltree
		this.registerFunction(Functions.LXTQUERY_LTREE_MATCH, new SQLFunctionTemplate(new BooleanType(),
				"(?1::ltxtquery @ ?2)"));
	}

}
