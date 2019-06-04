package br.com.hmovie.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hmovie.dto.response.MovieDTO;


@RestController
@RequestMapping(value = "/hmovie")
public class MovieController {


    @RequestMapping(method = RequestMethod.GET)
    public MovieDTO findAll() {
    	MovieDTO movieDTO = new MovieDTO();
    	movieDTO.setId(1L);
        return movieDTO;
    }


}
