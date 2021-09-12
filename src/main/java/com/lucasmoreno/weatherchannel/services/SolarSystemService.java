package com.lucasmoreno.weatherchannel.services;

import com.lucasmoreno.weatherchannel.exception.SolarSystemException;
import com.lucasmoreno.weatherchannel.exception.SolarSystemServiceException;

/**
 * 
 * Interface for Solar system for multiple possible system.
 * 
 * @author Lucas Moreno
 *
 */
public interface SolarSystemService {

	public void generateForecastByYears(long years) throws SolarSystemException, SolarSystemServiceException;

}
