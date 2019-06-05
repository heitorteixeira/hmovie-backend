package br.com.hmovie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hmovie.dto.request.SearchMovieDTO;
import br.com.hmovie.dto.response.MovieDTO;
import br.com.hmovie.service.MovieService;

@RestController
@RequestMapping(value = "/hmovie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public List<MovieDTO> findAllUpcoming(@PathVariable Integer page) {
        return movieService.findAllUpcoming(page);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<MovieDTO> searchMovies(@RequestBody SearchMovieDTO dto) {
        return movieService.searchMovies(dto);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/sid/{posterPath}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(HttpServletResponse response, @PathVariable String posterPath) throws IOException {
        movieService.getImage(posterPath, response);
    }

}