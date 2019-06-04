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

    @Value("${api.language}")
    private String apiLanguage;

    public List<MovieDTO> findAllUpcoming(Integer page) {
        try {
            
            ObjectMapper mapper = new ObjectMapper();
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> responseMovies = restTemplate.getForEntity(getUrlUpcomingMovies(page), String.class);

            if (responseMovies.getStatusCode().value() >= 200 && responseMovies.getStatusCode().value() < 300) {
                ResultsDTO result = mapper.readValue(responseMovies.getBody(), ResultsDTO.class);
                return result.getResults();
            } else {
                throw new HmovieException("Error when access ThemovieDb API: " + responseMovies.getStatusCode().getReasonPhrase());
            }
        } catch (IOException e) {
            LOG.info(e.getMessage());
            throw new HmovieException(e.getMessage());
        }
    }

    private String getUrlUpcomingMovies(Integer page) {
        String url = apiUrl + apiUrlMovieUpcoming + "?" + "api_key=" + apiKey;

        url = apiLanguage != null ? url + "&language=" + apiLanguage : url;
        url = page != null ? url + "&page=" + page : url;

        return url;
    }

}
