package com.epam.preprod.bohdanov.Task3.filter;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class ExtensionFilter extends ChainFilter {
	private String[] extensions;

	public ExtensionFilter(String... extensions) {
		checkForNullWithExceptions(extensions);
		this.extensions = extensions;
	}

	@Override
	protected boolean checkFile(File file) {
		String ext = FilenameUtils.getExtension(file.getName());
		for (String string : extensions) {
			if (string.compareTo(ext) == 0) {
				return true;
			}
		}
		return false;
	}

	private void checkForNullWithExceptions(String[] extensions) {
		for (String string : extensions) {
			if (string == null) {
				throw new NullPointerException();
			}
		}
	}
}
