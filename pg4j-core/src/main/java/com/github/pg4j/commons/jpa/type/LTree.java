package com.github.pg4j.commons.jpa.type;

import java.util.Iterator;

import com.google.common.annotations.Beta;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

@Beta
public final class LTree {
	private static final Splitter SEPARATOR = Splitter.on('.');
	private String path;
	private String current;
	private ImmutableList<String> parents;

	public LTree() {
	}

	public LTree(String path) {
		this.setPath(path);
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
		ImmutableList.Builder<String> parentsBuilder = ImmutableList.builder();
		Iterator<String> branches = SEPARATOR.splitToList(path).iterator();
		while (branches.hasNext()) {
			String branch = branches.next();
			if (branches.hasNext()) {
				parentsBuilder.add(branch);
			} else {
				this.current = branch;
			}
		}
		this.parents = parentsBuilder.build();
	}

	public String getCurrent() {
		return current;
	}

	public ImmutableList<String> getParents() {
		return parents;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof LTree) {
			final LTree that = (LTree) obj;
			return Objects.equal(this.getPath(), that.getPath());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(path);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("path", path).add("current", current).add("parents", parents)
				.toString();
	}
}
