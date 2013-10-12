package com.github.pg4j.commons.hibernate;

/**
 * Supported functions shortcuts.
 * 
 * @author <a href="mailto:roman.chukh@gmail.com">Roman Chukh</a>
 */
public interface Functions {
	/**
	 * Alternative to operation: {@code ltree <@ ltree}
	 */
	String LTREE_IS_DESCENDANT = "ltree4j_is_descendant";
	/**
	 * Alternative to operation: {@code ltree @> ltree}
	 */
	String LTREE_IS_ANCESTOR = "ltree4j_is_ancestor";
	/**
	 * Alternative to operation: {@code ltree ~ lquery}
	 */
	String LTREE_LQUERY_MATCH = "ltree4j_ltree_lquery_match";
	/**
	 * Alternative to operation: {@code lquery ~ ltree}
	 */
	String LQUERY_LTREE_MATCH = "ltree4j_lquery_ltree_match";
	/**
	 * Alternative to operation: {@code lquery ~ ltree}
	 */
	String LTREE_LXTQUERY_MATCH = "ltree4j_ltree_ltxtquery_match";
	/**
	 * Alternative to operation: {@code lquery ~ ltree}
	 */
	String LXTQUERY_LTREE_MATCH = "ltree4j_ltxtquery_ltree_match";
}
