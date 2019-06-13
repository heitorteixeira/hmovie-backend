package br.com.hmovie.dto.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ResultsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<MovieDTO> results;
	private Integer page;
	private Integer totalResults;
	private MovieDatesDTO dates;
	private Integer totalPages;
	
	public List<MovieDTO> getResults() {
		return results;
	}
	
	public void setResults(List<MovieDTO> results) {
		this.results = results;
	}
	
	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public MovieDatesDTO getDates() {
		return dates;
	}

	public void setDates(MovieDatesDTO dates) {
		this.dates = dates;
	}

	@JsonGetter("totalResults")
	public Integer getTotalResults() {
		return totalResults;
	}

	@JsonSetter("total_results")
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	@JsonGetter("totalPages")
	public Integer getTotalPages() {
		return totalPages;
	}

	@JsonSetter("total_pages")
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	
	
	
}
