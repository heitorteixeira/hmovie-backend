package br.com.hmovie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hmovie.dto.response.MovieDTO;
import br.com.hmovie.service.MovieService;

@RestController
@RequestMapping(value = "/hmovie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public List<MovieDTO> findAllUpcoming(@PathVariable Integer page) {
        return movieService.findAllUpcoming(page);
    }


}
