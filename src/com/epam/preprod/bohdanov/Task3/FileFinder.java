package com.epam.preprod.bohdanov.Task3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.epam.preprod.bohdanov.Task3.filter.ChainFilter;

public class FileFinder {
	private String dirPath;

	public FileFinder(String dirPath) {
		this.dirPath = dirPath;
	}

	private ArrayList<File> listFilesWithSubFolders(String dirPath) {
		ArrayList<File> files = new ArrayList<File>();
		File dir = new File(dirPath);
		for (File file : dir.listFiles()) {
			if (file.isDirectory())
				files.addAll(listFilesWithSubFolders(file.getAbsolutePath()));
			else
				files.add(file);
		}
		return files;
	}

	public List<File> getFiltredFileList(ChainFilter chainFilter) {
		ChainFilter filter = chainFilter;
		List<File> fileList = listFilesWithSubFolders(dirPath);
		List<File> resultList = new ArrayList<>();

		for (File file : fileList) {
			if (filter.doFilter(file)) {
				resultList.add(file);
			}
		}
		return resultList;
	}

}
