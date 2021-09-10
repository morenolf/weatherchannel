package com.lucasmoreno.weatherchannel.services.impl;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasmoreno.weatherchannel.entity.Planet;
import com.lucasmoreno.weatherchannel.entity.SolarSystem;
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

	private SolarSystem solarSystem;

	@Autowired
	private ForecastService forecastService;

	/**
	 * Creates the Solar system based on builder pattern using known planets and
	 * their properties
	 */
	public SolarSystemServiceImpl() {
		LOGGER.info("Creating solar system");
		this.solarSystem = new SolarSystem(this.createPlanets());
	}

	private List<Planet> createPlanets() {
		List<Planet> planets = new ArrayList<>();

		double distanceFromSun = 500.0;
		double position = 0.0;

		Planet planet = Planet.builder().distanceFromSun(distanceFromSun).position(position).translationSpeed(1)
				.cartesianCoordinates(new Point2D.Double(position, distanceFromSun)).build();

		planets.add(planet);

		distanceFromSun = 2000.0;
		position = 0.0;
		planet = Planet.builder().distanceFromSun(distanceFromSun).position(position).translationSpeed(3)
				.cartesianCoordinates(new Point2D.Double(position, distanceFromSun)).build();

		planets.add(planet);

		distanceFromSun = 1000.0;
		position = 0.0;
		planet = Planet.builder().distanceFromSun(distanceFromSun).position(position).translationSpeed(-5)
				.cartesianCoordinates(new Point2D.Double(position, distanceFromSun)).build();
		planets.add(planet);

		return planets;
	}

	/**
	 * Generates the forecast conditions for {@value} years in the future.
	 */
	public void generateForecastByYears(long years) {

		long dayByPeriod = years * DAYS_PER_YEAR;

		for (int day = 0; day < dayByPeriod; day++) {

			this.forecastService.generateForecast(this.solarSystem);
			this.translateOneDay();
			this.solarSystem.setDay(day);

		}

		this.forecastService.generateForecastReport(years);

	}

	@Override
	public void translateOneDay() {
		for (Planet planet : solarSystem.getPlanets()) {
			planet.setPosition(planet.getPosition() + planet.getTranslationSpeed());
			planet.setCartesianCoordinates(
					this.calculateCartesianCoodrinates(planet.getPosition(), planet.getDistanceFromSun()));
		}
		solarSystem.setAstronomicalEvents();
	}

	public Point2D calculateCartesianCoodrinates(double position, double distanceFromSun) {
		double xPosition = java.lang.Math.cos(java.lang.Math.toRadians(position)) * distanceFromSun;
		double yPosition = java.lang.Math.sin(java.lang.Math.toRadians(position)) * distanceFromSun;
		return new Point2D.Double(xPosition, yPosition);
	}

}
