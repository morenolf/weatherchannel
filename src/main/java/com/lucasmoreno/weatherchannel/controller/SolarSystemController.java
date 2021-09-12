package com.lucasmoreno.weatherchannel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    @GetMapping(value = "/testing/{day}")
    public SolarSystemForecastEntity getTesting(@PathVariable Long day) {
    	log.error("Getting forecast for day testing: " + day);
    	SolarSystemForecastEntity testingx = solarSystemForecastRepository.getById(day);
    	return testingx;
    }
    
    @GetMapping(value = "/generateForecast/{years}")
    public void generateForecast(@PathVariable int years) {
    	log.error("Getting forecast for day testing: " + years);
    	try {
			solarSystemService.generateForecastByYears(years);
		} catch (SolarSystemException | SolarSystemServiceException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
