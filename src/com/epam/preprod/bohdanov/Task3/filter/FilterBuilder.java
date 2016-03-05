package com.epam.preprod.bohdanov.Task3.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterBuilder {
	List<ChainFilter> filterList;

	public FilterBuilder() {
		filterList = new ArrayList<ChainFilter>();
	}

	public void addFilter(ChainFilter newFilter) {
		filterList.add(newFilter);
	}

	public ChainFilter build() {
		ChainFilter filter = null;
		ChainFilter firstFilter = null;
		for (ChainFilter f : filterList) {
			if (firstFilter == null) {
				firstFilter = f;
				filter = f;
			} else {
				filter.setNext(f);
				filter = f;
			}
		}
		return firstFilter;
	}
}
