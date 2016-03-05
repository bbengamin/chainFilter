package com.epam.preprod.bohdanov.Task3.filter;

import java.io.File;

public class SizeFilter extends ChainFilter {
	private long from;
	private long to;

	public SizeFilter(long from, long to) {
		checkRangeWithExceptoin(from, to);
		this.from = from;
		this.to = to;
	}

	@Override
	protected boolean checkFile(File file) {
		long fileSize = file.length();

		return (fileSize >= from && fileSize <= to);
	}

	private void checkRangeWithExceptoin(long from, long to) {
		if (from < 0 || to < 0 || from > to) {
			throw new IllegalArgumentException();
		}
	}
}
