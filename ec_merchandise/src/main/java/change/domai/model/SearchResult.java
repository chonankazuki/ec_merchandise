package change.domai.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Merchandise> searchResults = new ArrayList<>();
	public List<Merchandise> getSearchResults() {
		return searchResults;
	}
	public void setSearchResults(List<Merchandise> searchResults) {
		this.searchResults = searchResults;
	}

}
