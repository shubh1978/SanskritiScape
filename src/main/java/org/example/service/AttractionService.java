package org.example.service;

import org.example.model.Attraction;
import org.example.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionService {

    @Autowired
    private AttractionRepository attractionRepository;

    public List<Attraction> getAttractionsByCityAndState(String city, String state) {
        return attractionRepository.findByCityAndState(city, state);
    }

    public Attraction saveAttraction(Attraction attraction) {
        return attractionRepository.save(attraction);
    }
}
