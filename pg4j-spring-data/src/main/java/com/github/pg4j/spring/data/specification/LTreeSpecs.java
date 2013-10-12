package com.github.pg4j.spring.data.specification;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.Specification;

import com.github.pg4j.commons.jpa.criteria.ExpressionHelper;

/**
 * Basic Spring-Data helper for using ltree operations.
 *
 * @author <a href="mailto:roman.chukh@gmail.com">Roman Chukh</a>
 */
public final class LTreeSpecs {

	private LTreeSpecs() {
	}

	public static <T extends Persistable<?>> Specification<T> isDescendant(final SingularAttribute<T, String> field,
	                                                                       final String value) {
		return (root, query, cb) -> cb.isTrue(ExpressionHelper.ltreeIsDescendant(cb, root.get(field), value));
	}

	public static <T extends Persistable<?>> Specification<T> isAncestor(final SingularAttribute<T, String> field,
	                                                                     final String value) {
		return (root, query, cb) -> cb.isTrue(ExpressionHelper.ltreeIsAncestor(cb, root.get(field), value));
	}

	public static <T extends Persistable<?>> Specification<T> isMatch(final SingularAttribute<T, String> field,
	                                                                  final String pattern) {
		return (root, query, cb) -> cb.isTrue(ExpressionHelper.ltreeMatch(cb, root.get(field), pattern));
	}
}
