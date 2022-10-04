package com.example.swjokesapi.controller;

import com.example.swjokesapi.model.Joke;
import com.example.swjokesapi.model.Query;
import com.example.swjokesapi.service.JokesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Joke>> allJokes()
    {
        //return the response using a constructor
        return new ResponseEntity<>(service.allJokes(), HttpStatus.OK) ;
    }

    //how do we get inputs through a request
    //******************************************

    @GetMapping("query")
    public ResponseEntity<Object> filterJokes(@RequestBody Query query)
    {
        //we won't allow this end-point to be used with an empty query
        if(query.getQueryValue() == null  || query.getQueryValue().isEmpty())
        {
            //this is 400 level response (bad request - client error)
            //the body I'm responding with could be any object
            return new ResponseEntity<>("The query cannot be null or empty.", HttpStatus.BAD_REQUEST);
        }

        //an alternative using factory methods
        return ResponseEntity.ok(service.searchJokes(query.getQueryValue()));
    }

    //******************************************

    @PostMapping("")
    public ResponseEntity<Object> addJoke(@RequestBody Joke tempJoke)
    {
        // no bad jokes (i.e. no empty joke). don't add those
        if(tempJoke.getJokeText() == null  || tempJoke.getJokeText().isEmpty())
        {
            //this is 400 level response (bad request - client error)
            //the body I'm responding with could be any object
            return new ResponseEntity<>("The query cannot be null or empty.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.addJoke(tempJoke.getJokeText()), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Object> editAJoke(@RequestBody Joke tempJoke)
    {
        //can't edit if ID of joke is not found
        if(!service.jokeExists(tempJoke.getId()))
        {
            //404 (not found)
            return new ResponseEntity<>("Joke does not exist!", HttpStatus.NOT_FOUND);
        }
        //cant add an empty joke
        else if(tempJoke.getJokeText() == null  || tempJoke.getJokeText().isEmpty())
        {
            return new ResponseEntity<>("The joke text cannot be null or empty.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.updateJoke(tempJoke.getId(), tempJoke.getJokeText()), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteJoke(@RequestBody Joke tempJoke)
    {
        //cant add an empty joke
        if(tempJoke.getId() == null)
    {
        return new ResponseEntity<>("The joke ID cannot be null or empty.", HttpStatus.BAD_REQUEST);
    }
        //joke ID must exist
        else if(!service.jokeExists(tempJoke.getId()))
        {
            //404 (not found)
            return new ResponseEntity<>("Joke does not exist!", HttpStatus.NOT_FOUND);
        }

        //delete the joke, return HttpStatus.OK
        service.deleteJoke(tempJoke.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
