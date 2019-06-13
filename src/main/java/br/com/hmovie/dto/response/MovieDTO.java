package br.com.hmovie.dto.response;



import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class MovieDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer voteCount;
	private Boolean video;
	private Double voteAverage;
	private String title;
	private Double popularity;
	private String posterPath;
	private String originalLanguage;
	private String originalTitle;
	private List<Integer> genreIds;
	private String backdropPath;
	private Boolean adult;
	private String overview;
	private String releaseDate;
	
	private List<GenreDTO> genres;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonGetter("voteCount")
	public Integer getVoteCount() {
		return voteCount;
	}

	@JsonSetter("vote_count")
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public Boolean getVideo() {
		return video;
	}

	public void setVideo(Boolean video) {
		this.video = video;
	}

	@JsonGetter("voteAverage")
	public Double getVoteAverage() {
		return voteAverage;
	}

	@JsonSetter("vote_average")
	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPopularity() {
		return popularity;
	}

	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}

	@JsonGetter("posterPath")
	public String getPosterPath() {
		return posterPath;
	}

	@JsonSetter("poster_path")
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	@JsonGetter("originalLanguage")
	public String getOriginalLanguage() {
		return originalLanguage;
	}

	@JsonSetter("original_language")
	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	@JsonGetter("originalTitle")
	public String getOriginalTitle() {
		return originalTitle;
	}

	@JsonSetter("original_title")
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	@JsonGetter("genreIds")
	public List<Integer> getGenreIds() {
		return genreIds;
	}

	@JsonSetter("genre_ids")
	public void setGenreIds(List<Integer> genreIds) {
		this.genreIds = genreIds;
	}

	@JsonGetter("backdropPath")
	public String getBackdropPath() {
		return backdropPath;
	}

	@JsonSetter("backdrop_path")
	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public Boolean getAdult() {
		return adult;
	}

	public void setAdult(Boolean adult) {
		this.adult = adult;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	@JsonGetter("releaseDate")
	public String getReleaseDate() {
		return releaseDate;
	}

	@JsonSetter("release_date")
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<GenreDTO> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreDTO> genres) {
		this.genres = genres;
	}
	
	
	
	
}
