package POJO;

import java.util.List;

public class Quotes {
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getLastItemIndex() {
		return lastItemIndex;
	}
	public void setLastItemIndex(int lastItemIndex) {
		this.lastItemIndex = lastItemIndex;
	}
	public List<results> getResults() {
		return results;
	}
	public void setResults(List<results> results) {
		this.results = results;
	}


	private int count;
	private int totalCount;
	private int page;
	private int totalPages;
	private int lastItemIndex;
	private List<results> results;
	
}
