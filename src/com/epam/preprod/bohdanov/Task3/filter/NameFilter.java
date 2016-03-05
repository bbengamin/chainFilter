package com.epam.preprod.bohdanov.Task3.filter;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class NameFilter extends ChainFilter {
	private String fileName;

	public NameFilter(String fileName) {
		if (fileName == null)
			throw new NullPointerException();
		this.fileName = fileName;
	}

	@Override
	protected boolean checkFile(File file) {
		String fileNameWithOutEx = FilenameUtils.removeExtension(file.getName());
		return fileNameWithOutEx.contains(fileName);
	}

}
