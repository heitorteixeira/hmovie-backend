package br.com.hmovie.dto.request;

import java.io.Serializable;

public class SearchMovieDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer page;
	private String query;
	private Boolean includeAdult;
	
	
	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public Boolean getIncludeAdult() {
		return includeAdult;
	}
	
	public void setIncludeAdult(Boolean includeAdult) {
		this.includeAdult = includeAdult;
	}

	
}
