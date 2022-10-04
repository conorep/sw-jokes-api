package com.example.swjokesapi.service;

import com.example.swjokesapi.model.Joke;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JokesService
{
    private List<Joke> jokes = new ArrayList<>(
            List.of(
                  new Joke("How does Darth Vader like his toast? On the dark side."),
                    new Joke("Why did the tapeworm stay far away from Palpatine? He didn’t want anyone to say he was in Sidious."),
                    new Joke("We don’t want to sound racist but... All stormtroopers look the same to us."),
                    new Joke("What did Darth Vader say when he walked into a vegetarian restaurant? “I find your lack of steak disturbing.”"),
                    new Joke("What was Tarkin's favorite brand of toilet paper? Charmin to the last."),
                    new Joke("What does your Canadian friend cooking dinner for you have in common with the Empire from Star Wars? Pal-poutine."),
                    new Joke("How did Darth Vader cheat at poker? He kept altering the deal."),
                    new Joke("Stormtroopers in quarantine are like, “I miss people.” I’m not too sympathetic. They always miss people.")
            )
    );


    //create
    public Joke addJoke(String jokeText)
    {
        Joke added = new Joke(jokeText);
        jokes.add(added);
        return added;
    }


    //read
    public List<Joke> allJokes()
    {
        return jokes;
    }

    //queryValue could be any substring
    public List<Joke> searchJokes(String queryValue)
    {
        return jokes.stream()
                .filter(joke -> joke.getJokeText().toLowerCase()
                .contains(queryValue.toLowerCase()))
                .toList();
    }


    //update
    public Joke updateJoke(UUID id, String newJokeText)
    {
        //find the element
        //optional: a way to pass around an object and get around 'null' objects
        //will store a null value OR the joke
        Optional<Joke> foundAJoke = jokes.stream()
                .filter(joke -> joke.getId().equals(id))
                .findFirst();

        //NOTE: could also return an optional. not doing that here, though
        if(foundAJoke.isPresent())
        {
            //update it
            Joke updateJoke = foundAJoke.get();
            updateJoke.setJokeText(newJokeText);
            return updateJoke;
        } else
        {
            return null;
        }
    }


    //delete
    public void deleteJoke(UUID id)
    {
        //search through stream and keep all jokes not matching the UUID
        jokes = jokes.stream()
                .filter(joke -> !joke.getId().equals(id))
                .toList();
        //TODO: report a failure to find
    }

    //exists...?
    public boolean jokeExists(UUID id)
    {
        return jokes.stream()
                .anyMatch(joke -> joke.getId().equals(id));
    }
}
