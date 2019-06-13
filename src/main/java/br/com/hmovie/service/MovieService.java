package br.com.hmovie.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hmovie.dto.request.SearchMovieDTO;
import br.com.hmovie.dto.response.GenreDTO;
import br.com.hmovie.dto.response.GenresDTO;
import br.com.hmovie.dto.response.MovieDTO;
import br.com.hmovie.dto.response.ResultsDTO;
import br.com.hmovie.exception.HmovieException;

@Service
public class MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.url.movie.upcoming}")
    private String apiUrlMovieUpcoming;

    @Value("${api.url.movie.genre}")
    private String apiUrlMovieGenre;

    @Value("${api.url.search.movie}")
    private String apiUrlSearchMovie;

    @Value("${api.language}")
    private String apiLanguage;

    @Value("${api.url.images}")
    private String apiUrlImages;

    private Map<String, InputStream> images = new HashMap<String, InputStream>();
    private Map<Integer, String> genres = new HashMap<Integer, String>();

    public List<MovieDTO> findAllUpcoming(Integer page) {
        try {
            findAllGenres();
            ObjectMapper mapper = new ObjectMapper();
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> responseMovies = restTemplate.getForEntity(getUrlUpcomingMovies(page), String.class);

            if (responseMovies.getStatusCode().value() >= 200 && responseMovies.getStatusCode().value() < 300) {
                ResultsDTO result = mapper.readValue(responseMovies.getBody(), ResultsDTO.class);
                setGenreIntoMovies(result);
                findImages(result.getResults());
                return result.getResults();
            } else {
                throw new HmovieException("Error when access ThemovieDb API: " + responseMovies.getStatusCode().getReasonPhrase());
            }
        } catch (IOException e) {
            LOG.info(e.getMessage());
            throw new HmovieException(e.getMessage());
        }
    }

    private void setGenreIntoMovies(ResultsDTO result) {
        for (MovieDTO movie : result.getResults()) {
            List<GenreDTO> lstGenres = new ArrayList<GenreDTO>();
            for (Integer idGenre : movie.getGenreIds()) {
                GenreDTO gDB = new GenreDTO(idGenre, genres.get(idGenre));
                if (gDB != null) {
                    lstGenres.add(gDB);
                }
            }
            movie.setGenres(lstGenres);
        }
    }

    private void findAllGenres() {
        try {
            if (genres.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<String> response = restTemplate.getForEntity(getUrlGenres(), String.class);

                if (response.getStatusCode().value() >= 200 && response.getStatusCode().value() < 300) {
                    GenresDTO result = mapper.readValue(response.getBody(), GenresDTO.class);
                    for (GenreDTO g : result.getGenres()) {
                        genres.put(g.getId(), g.getName());
                    }
                } else {
                    throw new HmovieException("Error when access ThemovieDb API: " + response.getStatusCode().getReasonPhrase());
                }
            }
        } catch (IOException e) {
            LOG.info(e.getMessage());
            throw new HmovieException(e.getMessage());
        }
    }

    public List<MovieDTO> searchMovies(SearchMovieDTO requestDto) {
        try {
            findAllGenres();
            ObjectMapper mapper = new ObjectMapper();
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> responseMovies = restTemplate.getForEntity(getUrlSearchMovies(requestDto), String.class);

            if (responseMovies.getStatusCode().value() >= 200 && responseMovies.getStatusCode().value() < 300) {
                ResultsDTO result = mapper.readValue(responseMovies.getBody(), ResultsDTO.class);
                setGenreIntoMovies(result);
                findImages(result.getResults());
                return result.getResults();

            } else {
                throw new HmovieException("Error when access ThemovieDb API: " + responseMovies.getStatusCode().getReasonPhrase());
            }
        } catch (IOException e) {
            LOG.info(e.getMessage());
            throw new HmovieException(e.getMessage());
        }
    }

    private void findImages(List<MovieDTO> movies) {
        try {
            for (MovieDTO m : movies) {
                if (m.getPosterPath() != null) {
                    String path = m.getPosterPath().substring(1);
                    URL urlInput = new URL(apiUrlImages + path);
                    InputStream is = urlInput.openStream();

                    if (!images.containsKey(path)) {
                        images.put(path, is);
                    }
                }
            }
        } catch (IOException e) {
            LOG.info(e.getMessage());
            throw new HmovieException(e.getMessage());
        }

    }

    public void getImage(String posterPath, HttpServletResponse response) {
        try {
            if (images.containsKey(posterPath)) {
                InputStream is = images.get(posterPath);
                if (is != null) {
                    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                    StreamUtils.copy(is, response.getOutputStream());
                }
            } else {
                System.out.println(posterPath);
                LOG.info("Not find image: " + posterPath);
            }
        } catch (IOException e) {
            LOG.info(e.getMessage());
            throw new HmovieException(e.getMessage());
        }
    }

    private String getUrlSearchMovies(SearchMovieDTO requestDto) {
        String url = apiUrl + apiUrlSearchMovie + "?" + "api_key=" + apiKey;

        url = apiLanguage != null ? url + "&language=" + apiLanguage : url;
        url = requestDto.getQuery() != null ? url + "&query=" + requestDto.getQuery() : url;
        url = requestDto.getPage() != null ? url + "&page=" + requestDto.getPage() : url;
        url = requestDto.getIncludeAdult() != null ? url + "&include_adult=" + requestDto.getIncludeAdult() : url;

        return url;
    }

    private String getUrlGenres() {
        String url = apiUrl + apiUrlMovieGenre + "?" + "api_key=" + apiKey;

        url = apiLanguage != null ? url + "&language=" + apiLanguage : url;

        return url;
    }

    private String getUrlUpcomingMovies(Integer page) {
        String url = apiUrl + apiUrlMovieUpcoming + "?" + "api_key=" + apiKey;

        url = apiLanguage != null ? url + "&language=" + apiLanguage : url;
        url = page != null ? url + "&page=" + page : url;

        return url;
    }

}