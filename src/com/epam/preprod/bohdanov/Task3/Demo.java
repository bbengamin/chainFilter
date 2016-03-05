package com.epam.preprod.bohdanov.Task3;


import com.epam.preprod.bohdanov.Task3.filter.DateFilter;
import com.epam.preprod.bohdanov.Task3.filter.ExtensionFilter;
import com.epam.preprod.bohdanov.Task3.filter.FilterBuilder;
import com.epam.preprod.bohdanov.Task3.filter.NameFilter;
import com.epam.preprod.bohdanov.Task3.filter.SizeFilter;

public class Demo {
	private final static String NAME_FILTER_QUESTION = "искать по имени файла ? (y\\n) ";
	private final static String EXTENSION_FILTER_QUESTION = "искать по расширению файла ? (y\\n)  ";
	private final static String SIZE_FILTER_QUESTION = "искать по размеру файла ? (y\\n)  ";
	private final static String DATE_FILTER_QUESTION = "искать по дате изменения файла ? (y\\n)  ";

	private final static String FROM_SIZE_QUESTION = "введите нижнию границу диапазона размера файла: ";
	private final static String TO_SIZE_QUESTION = "введите верхнюю границу диапазона размера файла: ";
	private final static String FROM_DATE_QUESTION = "введите нижнию границу диапазона даты изменения файла в формате(yyyy-MM-dd): ";
	private final static String TO_DATE_QUESTION = "введите верхнюю границу диапазона даты изменения файла в формате(yyyy-MM-dd): ";
	private final static String EXTENSION_QUESTION = "введите расширение в формате (ext,ext,...,ext): ";
	private final static String FILE_NAME_QUESTION = "введите имя файла: ";

	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		df.setLenient(false);
		Scanner scanner = new Scanner(System.in);
		System.out.println(FILE_NAME_QUESTION);
		String fileName = scanner.nextLine();

		FileWrapper file = new FileWrapper(fileName);
		for (String string : file) {
			System.out.println(string);
		}

		FilterBuilder filter = new FilterBuilder();

		AksForNameFilter(scanner, filter);
		AksForExtensionFilter(scanner, filter);
		AksForSizeFilter(scanner, filter);
		AksForDateFilter(scanner, filter);

		FileFinder manager = new FileFinder("D:\\TestFolder");

		for (File temp : manager.getFiltredFileList(filter.build())) {
			System.out.println(temp.getAbsolutePath());
			System.out.println("Size: " + temp.length());
		}
	}

	private static void AksForNameFilter(Scanner scanner, FilterBuilder filter) {
		System.out.println(NAME_FILTER_QUESTION);
		if (scanner.nextLine().compareTo("y") == 0) {
			System.out.println(FILE_NAME_QUESTION);
			String fileName = scanner.nextLine();
			filter.addFilter(new NameFilter(fileName));
		}
	}

	private static void AksForExtensionFilter(Scanner scanner, FilterBuilder filter) {
		System.out.println(EXTENSION_FILTER_QUESTION);
		if (scanner.nextLine().compareTo("y") == 0) {
			System.out.println(EXTENSION_QUESTION);
			String[] extensions = scanner.nextLine().split(",");
			filter.addFilter(new ExtensionFilter(extensions));
		}
	}

	private static void AksForSizeFilter(Scanner scanner, FilterBuilder filter) {
		System.out.println(SIZE_FILTER_QUESTION);
		if (scanner.nextLine().compareTo("y") == 0) {
			System.out.println(FROM_SIZE_QUESTION);
			long from = scanner.nextLong();
			System.out.println(TO_SIZE_QUESTION);
			long to = scanner.nextLong();
			filter.addFilter(new SizeFilter(from, to));
		}
	}

	private static void AksForDateFilter(Scanner scanner, FilterBuilder filter) {
		System.out.println(DATE_FILTER_QUESTION);
		if (scanner.nextLine().compareTo("y") == 0) {
			System.out.println(FROM_DATE_QUESTION);
			Date from = parseDateFromStringWithOutEx(scanner.next());
			System.out.println(TO_DATE_QUESTION);
			Date to = parseDateFromStringWithOutEx(scanner.next());
			filter.addFilter(new DateFilter(from, to));
		}
	}

	private static Date parseDateFromStringWithOutEx(String date) {
		try {
			return df.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

}
