package com.github.pg4j.commons.hibernate;

/**
 * Supported functions shortcuts.
 * 
 * @author "Roman Chukh <roman.chukh@gmail.com>"
 */
public interface Functions {
    /**
     * Alternative to operation: <br />
     * "ltree <@ ltree"
     */
    String LTREE_IS_DESCENDANT = "ltree4j_is_descendant";

    /**
     * Alternative to operation: <br />
     * "ltree @> ltree"
     */
    String LTREE_IS_ANCESTOR = "ltree4j_is_ancestor";

    /**
     * Alternative to operation: <br />
     * "ltree ~ lquery"
     */
    String LTREE_LQUERY_MATCH = "ltree4j_ltree_lquery_match";

    /**
     * Alternative to operation: <br />
     * "lquery ~ ltree"
     */
    String LQUERY_LTREE_MATCH = "ltree4j_lquery_ltree_match";

    /**
     * Alternative to operation: <br />
     * "lquery ~ ltree"
     */
    String LTREE_LXTQUERY_MATCH = "ltree4j_ltree_ltxtquery_match";

    /**
     * Alternative to operation: <br />
     * "lquery ~ ltree"
     */
    String LXTQUERY_LTREE_MATCH = "ltree4j_ltxtquery_ltree_match";
}
