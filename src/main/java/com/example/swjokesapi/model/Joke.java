package com.example.swjokesapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Joke
{
    private UUID id;
    private String jokeText;

    public Joke(String jokeText)
    {
        id=UUID.randomUUID();
        this.jokeText = jokeText;
    }
}
