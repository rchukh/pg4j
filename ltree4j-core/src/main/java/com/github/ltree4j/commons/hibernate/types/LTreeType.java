package com.github.ltree4j.commons.hibernate.types;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

/**
 * Custom hibernate type for ltree extension in PostrgreSQL.
 * 
 * @author Roman Chukh <rchukh@gmx.com>
 */
public class LTreeType implements UserType {

    @Override
    public int[] sqlTypes() {
	return new int[] { Types.OTHER };
    }

    @Override
    public Class<String> returnedClass() {
	return String.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
	if (x == null) {
	    return y == null;
	} else {
	    return x.equals(y);
	}
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
	return x != null ? x.hashCode() : 0;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names,
	    SessionImplementor session, Object owner)
	    throws HibernateException, SQLException {
	assert (names.length == 1);
	String ltree = rs.getString(names[0]);
	return rs.wasNull() ? null : ltree;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,
	    SessionImplementor session) throws HibernateException, SQLException {
	if (value == null) {
	    st.setNull(index, Types.OTHER);
	} else {
	    st.setObject(index, value, Types.OTHER);
	}
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
	if (value == null) {
	    return null;
	}
	return new StringBuffer((String) value).toString();
    }

    @Override
    public boolean isMutable() {
	return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
	return (String) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner)
	    throws HibernateException {
	return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner)
	    throws HibernateException {
	return this.deepCopy(original);
    }

}