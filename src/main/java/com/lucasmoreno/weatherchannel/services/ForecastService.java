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
	public void generateForecast(long dayByPeriod, SolarSystemDto solarSystemDto);
	
	public void generateForecastReport();

	public SolarSystemForecastEntity calculateForecast(long day, SolarSystemDto solarSystemDto);

	public SolarSystemForecastEntity getTodayForecast(SolarSystemDto solarSystemDto);
}
