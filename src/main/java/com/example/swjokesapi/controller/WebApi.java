package com.example.swjokesapi.controller;

import com.example.swjokesapi.model.Joke;
import com.example.swjokesapi.model.Query;
import com.example.swjokesapi.service.JokesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    this does two things:
        1. @Controller: to respond to requests
        2. @ResponseBody: to return data from each method, not html page names
*/
@RestController
@RequestMapping("api/v1/joke")
public class WebApi
{
    public JokesService service;

    public WebApi(JokesService service)
    {
        this.service = service;
    }


    @GetMapping("")
    public List<Joke> allJokes()
    {
        return service.allJokes();
    }

    //how do we get inputs through a request
    //******************************************

    @GetMapping("query")
    public List<Joke> filterJokes(@RequestBody Query query)
    {
        return service.searchJokes(query.getQueryValue());
    }

    //******************************************

    @PostMapping("")
    public Joke addJoke(@RequestBody Joke tempJoke)
    {
        return service.addJoke(tempJoke.getJokeText());
    }

    @PutMapping("")
    public Joke editAJoke(@RequestBody Joke tempJoke)
    {
        return service.updateJoke(tempJoke.getId(), tempJoke.getJokeText());
    }

    @DeleteMapping("")
    public void deleteJoke(@RequestBody Joke tempJoke)
    {
        service.deleteJoke(tempJoke.getId());
    }
}
