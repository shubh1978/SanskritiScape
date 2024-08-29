package org.example.controller;

import org.example.model.Attraction;
import org.example.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attractions")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @GetMapping("/{city}/{state}")
    public List<Attraction> getAttractions(@PathVariable String city, @PathVariable String state) {
        return attractionService.getAttractionsByCityAndState(city, state);
    }

    @PostMapping
    public Attraction addAttraction(@RequestBody Attraction attraction) {
        return attractionService.saveAttraction(attraction);
    }
}
