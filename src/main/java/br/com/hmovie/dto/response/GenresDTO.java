package br.com.hmovie.dto.response;

import java.io.Serializable;
import java.util.List;

public class GenresDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<GenreDTO> genres;

	public List<GenreDTO> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreDTO> genres) {
		this.genres = genres;
	}

}
