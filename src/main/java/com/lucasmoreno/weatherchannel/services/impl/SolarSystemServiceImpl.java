package com.lucasmoreno.weatherchannel.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasmoreno.weatherchannel.builder.impl.PlanetBuilder;
import com.lucasmoreno.weatherchannel.director.SolarSystemDirector;
import com.lucasmoreno.weatherchannel.dto.CartesianCoordinatesDto;
import com.lucasmoreno.weatherchannel.dto.PlanetDto;
import com.lucasmoreno.weatherchannel.dto.SolarSystemDto;
import com.lucasmoreno.weatherchannel.entity.SolarSystemForecastEntity;
import com.lucasmoreno.weatherchannel.services.ForecastService;
import com.lucasmoreno.weatherchannel.services.SolarSystemService;

/**
 * Service that handles Solar system logic and his construction as well in order
 * to calculate forecast.
 * 
 * @author Lucas Moreno
 *
 */
@Service
public class SolarSystemServiceImpl implements SolarSystemService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SolarSystemServiceImpl.class);

	private static final long DAYS_PER_YEAR = 360;

	private SolarSystemDto solarSystemDto;

	@Autowired
	private SolarSystemDirector solarSystemDirector;

	@Autowired
	private ForecastService forecastService;

	/**
	 * Creates the Solar system based on builder pattern using known planets and
	 * their properties
	 */
	public SolarSystemServiceImpl() {
		LOGGER.info("Creating solar system");
		List<PlanetDto> planets = new ArrayList<>();

		PlanetBuilder planetBuilder = new PlanetBuilder();
		solarSystemDirector.constructBetasoides(planetBuilder);
		planets.add(planetBuilder.getResult());
		solarSystemDirector.constructVulcan(planetBuilder);
		planets.add(planetBuilder.getResult());
		solarSystemDirector.constructBetasoides(planetBuilder);
		planets.add(planetBuilder.getResult());
		this.solarSystemDto = new SolarSystemDto(planets);
	}

	/**
	 * Generates the forecast conditions for {@value} years in the future.
	 */
	public void generateForecastByYears(long years) {

		long dayByPeriod = years * DAYS_PER_YEAR;

		for (int i = 0; i < dayByPeriod; i++) {

			this.forecastService.generateForecast(dayByPeriod, this.solarSystemDto);
			this.translateOneDay();

		}
		
		this.forecastService.generateForecastReport();

	}

	@Override
	public long getDay() {
		return this.solarSystemDto.getDay();
	}

	@Override
	public void translateOneDay() {
		for (PlanetDto planet : solarSystemDto.getPlanets()) {
			planet.setPosition(planet.getPosition() + planet.getTranslationSpeed());
			planet.setCartesianCoordinatesDto(this.calculateCartesianCoodrinates(planet.getPosition(), planet.getDistanceFromSun()));
		}
		solarSystemDto.setAstronomicalEvents();
	}
	
	public CartesianCoordinatesDto calculateCartesianCoodrinates(double position, double distanceFromSun) {
		double xPosition = java.lang.Math.cos(java.lang.Math.toRadians(position)) * distanceFromSun;
		double yPosition = java.lang.Math.sin(java.lang.Math.toRadians(position)) * distanceFromSun;
		return new CartesianCoordinatesDto(xPosition, yPosition);
	}

}
