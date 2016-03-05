package com.epam.preprod.bohdanov.Task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class FileWrapper implements Iterable<String> {
	private String fileName;

	public FileWrapper(String fileName) throws FileNotFoundException {
		CheckFileExistAndCanReadWithException(fileName);
		this.fileName = fileName;
	}

	private class Itr implements Iterator<String> {
		private Scanner scanner;

		public Itr() {
			try {
				scanner = new Scanner(new File(fileName));
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			}
		}

		@Override
		public boolean hasNext() {
			return scanner.hasNextLine();
		}

		@Override
		public String next() {
			return scanner.nextLine();
		}

	}

	public Iterator<String> iterator() {
		return new Itr();
	}

	private void CheckFileExistAndCanReadWithException(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		if (!file.canRead()) {
			throw new FileNotFoundException();
		}
	}

}
