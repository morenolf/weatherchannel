package com.lucasmoreno.weatherchannel.services;

import com.lucasmoreno.weatherchannel.dto.SolarSystemDto;
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
	
	public void generateForecast(long dayOfTheYear, SolarSystemDto solarSystemDto);

}
