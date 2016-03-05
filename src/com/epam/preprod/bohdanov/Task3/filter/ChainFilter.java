package com.epam.preprod.bohdanov.Task3.filter;

import java.io.File;

public abstract class ChainFilter {
	private ChainFilter next;

	public void setNext(ChainFilter next) {
		this.next = next;
	}

	public boolean doFilter(File file) {
		boolean result = checkFile(file);
		if (result && next != null) {
			return next.doFilter(file);
		}
		return result;
	}

	protected abstract boolean checkFile(File file);

}