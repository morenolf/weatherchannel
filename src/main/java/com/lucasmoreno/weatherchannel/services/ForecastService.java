package com.lucasmoreno.weatherchannel.services;


import com.lucasmoreno.weatherchannel.entity.SolarSystem;
import com.lucasmoreno.weatherchannel.entity.SolarSystemForecastEntity;

/**
 * 
 * 
 * 
 * @author Lucas Moreno
 *
 */
public interface ForecastService {

	public void generateForecastReport(long years);
	
	public void generateForecast(SolarSystem solarSystemDto);

	SolarSystemForecastEntity getForecastByDay(long day);

}
