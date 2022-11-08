package com.example.swjokesapi.controller;

import com.example.swjokesapi.service.JokesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Web controller to route to home page.
 * @author Conor
 * @version 1.0
 */
@Controller
public class WebController
{
    JokesService service;

    /**
     * Constructor for web controller.
     * @param thisService service object for web routing
     */
    public WebController(JokesService thisService)
    {
        this.service = thisService;
    }

    @RequestMapping("")
    public String homePage()
    {
        return "home.html";
    }

    @Override
    public String toString()
    {
        return "WebController{" +
                "service=" + service +
                '}';
    }
}
