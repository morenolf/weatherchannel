package com.lucasmoreno.weatherchannel.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucasmoreno.weatherchannel.dto.ForecastSolarSystemRepository;
import com.lucasmoreno.weatherchannel.services.ForecastService;

/**
 * 
 * Forecast service to handle the logic to calculate solar system weather
 * conditions.
 * 
 * @author Lucas Moreno
 *
 */
public class ForecastServiceImpl implements ForecastService {

	@Autowired 
	private ForecastSolarSystemRepository forecastSolarSystemRepository;

	
}
