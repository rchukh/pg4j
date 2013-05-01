package com.github.pg4j.commons.hibernate.dialect;

import org.hibernate.dialect.PostgreSQL82Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.BooleanType;

import com.github.pg4j.commons.hibernate.Functions;
import com.github.pg4j.commons.hibernate.function.ltree.LTreeIsAncestorFunction;
import com.github.pg4j.commons.hibernate.function.ltree.LTreeIsDescendantFunction;

/**
 * Hibernate {@link org.hibernate.dialect.PostgreSQL82Dialect} extended with
 * "ltree" functionality.
 * <p/>
 * Supports:
 * <ul>
 * <li><b>ltree @> ltree</b> -> {@link Functions#LTREE_IS_DESCENDANT}</li>
 * <li><b>ltree <@ ltree</b> -> {@link Functions#LTREE_IS_ANCESTOR}</li>
 * <li><b>ltree ~ lquery</b> -> {@link Functions#LTREE_LQUERY_MATCH}</li>
 * <li><b>lquery ~ ltree</b> -> {@link Functions#LQUERY_LTREE_MATCH}</li>
 * <li><b>ltree @ ltxtquery</b> -> {@link Functions#LTREE_LXTQUERY_MATCH}</li>
 * <li><b>ltxtquery @ ltree</b> -> {@link Functions#LXTQUERY_LTREE_MATCH}</li>
 * </ul>
 * 
 * @author "Roman Chukh <roman.chukh@gmail.com>"
 */
public class ExtendedPostgreSQL82Dialect extends PostgreSQL82Dialect {

    public ExtendedPostgreSQL82Dialect() {
	/**
	 * Is left argument an ancestor of right (or equal)? <br/>
	 * <code>ltree @> ltree</code>
	 */
	this.registerFunction(Functions.LTREE_IS_ANCESTOR,
		new LTreeIsAncestorFunction());
	/**
	 * Is left argument a descendant of right (or equal)? <br/>
	 * <code>ltree <@ ltree</code>
	 */
	this.registerFunction(Functions.LTREE_IS_DESCENDANT,
		new LTreeIsDescendantFunction());
	/**
	 * Does ltree match lquery? <br/>
	 * <code>ltree ~ lquery</code>
	 */
	this.registerFunction(Functions.LTREE_LQUERY_MATCH,
		new SQLFunctionTemplate(new BooleanType(), "(?1 ~ ?2::lquery)"));
	/**
	 * Does lquery match ltree? <br/>
	 * <code>lquery ~ ltree</code>
	 */
	this.registerFunction(Functions.LQUERY_LTREE_MATCH,
		new SQLFunctionTemplate(new BooleanType(), "(?1::lquery ~ ?2)"));
	/**
	 * Does ltree match ltxtquery? <br/>
	 * <code>ltree @ ltxtquery</code>
	 */
	// TODO: Test ltree @ ltxtquery
	this.registerFunction(Functions.LTREE_LXTQUERY_MATCH,
		new SQLFunctionTemplate(new BooleanType(),
			"(?1 @ ?2::ltxtquery)"));
	/**
	 * Does ltxtquery match ltree? <br/>
	 * <code>ltxtquery @ ltree</code>
	 */
	// TODO: Test ltxtquery @ ltree
	this.registerFunction(Functions.LXTQUERY_LTREE_MATCH,
		new SQLFunctionTemplate(new BooleanType(),
			"(?1::ltxtquery @ ?2)"));
    }

}
