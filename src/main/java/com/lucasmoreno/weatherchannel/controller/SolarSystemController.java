package com.lucasmoreno.weatherchannel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasmoreno.weatherchannel.entity.SolarSystemForecastEntity;
import com.lucasmoreno.weatherchannel.exception.SolarSystemException;
import com.lucasmoreno.weatherchannel.exception.SolarSystemServiceException;
import com.lucasmoreno.weatherchannel.repository.SolarSystemForecastRepository;
import com.lucasmoreno.weatherchannel.services.SolarSystemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "forecast")
@Slf4j
public class SolarSystemController {

    @Autowired
    private SolarSystemService solarSystemService;
    
    @Autowired
    private SolarSystemForecastRepository solarSystemForecastRepository;

    @GetMapping(value = "/generateForecast/{years}")
    public void generateForecast(@PathVariable int years) throws SolarSystemException, SolarSystemServiceException {
    	log.error("Getting forecast for day testing: " + years);
    	solarSystemService.generateForecastByYears(years);
    }
    
    @GetMapping(value = "/day/{day}")
    public SolarSystemForecastEntity getForecastByDay(@PathVariable long day) throws SolarSystemException, SolarSystemServiceException {
    	return solarSystemForecastRepository.getById(day);    	
    }

}
