package com.github.pg4j.spring.data.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.Specification;

import com.github.pg4j.commons.jpa.criteria.ExpressionHelper;

/**
 * Basic Spring-Data helper for using ltree operations.
 * 
 * @author "Roman Chukh <roman.chukh@gmail.com>"
 */
// TODO: Add JavaDoc to methods.
public final class LTreeSpecs {

    private LTreeSpecs() {
    }

    public static <T extends Persistable<?>> Specification<T> isDescendant(
	    final SingularAttribute<T, String> field, final String value) {
	return new Specification<T>() {
	    @Override
	    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
		    CriteriaBuilder cb) {
		return cb.isTrue(ExpressionHelper.ltreeIsDescendant(cb,
			root.get(field), value));
	    }
	};
    }

    /**
     * ltree @> ltree
     * 
     * @param field
     * @param value
     * @return
     */
    public static <T extends Persistable<?>> Specification<T> isAncestor(
	    final SingularAttribute<T, String> field, final String value) {
	return new Specification<T>() {
	    @Override
	    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
		    CriteriaBuilder cb) {
		return cb.isTrue(ExpressionHelper.ltreeIsAncestor(cb,
			root.get(field), value));
	    }
	};
    }

    public static <T extends Persistable<?>> Specification<T> isMatch(
	    final SingularAttribute<T, String> field, final String pattern) {
	return new Specification<T>() {
	    @Override
	    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
		    CriteriaBuilder cb) {
		return cb.isTrue(ExpressionHelper.ltreeMatch(cb,
			root.get(field), pattern));
	    }
	};
    }

}
