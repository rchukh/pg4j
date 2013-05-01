package com.github.ltree4j.commons.jpa.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;

import com.github.ltree4j.commons.hibernate.Functions;

/**
 * JPA Helper for using ltree functions in
 * {@link javax.persistence.criteria.CriteriaBuilder}.
 * 
 * @author "Roman Chukh <rchukh@gmx.com>"
 */
// TODO: Add JavaDoc to methods
public final class ExpressionHelper {

    private ExpressionHelper() {
    }

    public static Expression<Boolean> ltreeIsDescendant(CriteriaBuilder cb,
	    Path<String> field, String value) {
	return cb.function(Functions.LTREE_IS_DESCENDANT, Boolean.class, field,
		cb.literal(value));
    }

    public static Expression<Boolean> ltreeIsDescendant(CriteriaBuilder cb,
	    Path<String> field, Path<String> secondField) {
	return cb.function(Functions.LTREE_IS_DESCENDANT, Boolean.class, field,
		secondField);
    }

    public static Expression<Boolean> ltreeIsAncestor(CriteriaBuilder cb,
	    Path<String> field, Path<String> secondField) {
	return cb.function(Functions.LTREE_IS_ANCESTOR, Boolean.class, field,
		secondField);
    }

    public static Expression<Boolean> ltreeIsAncestor(CriteriaBuilder cb,
	    Path<String> field, String value) {
	return cb.function(Functions.LTREE_IS_ANCESTOR, Boolean.class, field,
		cb.literal(value));
    }

    public static Expression<Boolean> ltreeMatch(CriteriaBuilder cb,
	    Path<String> field, String pattern) {
	return cb.function(Functions.LTREE_LQUERY_MATCH, Boolean.class, field,
		cb.literal(pattern));
    }
}