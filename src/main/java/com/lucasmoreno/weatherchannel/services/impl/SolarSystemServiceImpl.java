package com.lucasmoreno.weatherchannel.services.impl;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasmoreno.weatherchannel.entity.DefaultPlanetDistancesTypes;
import com.lucasmoreno.weatherchannel.entity.DefaultPlanetPositionType;
import com.lucasmoreno.weatherchannel.entity.Planet;
import com.lucasmoreno.weatherchannel.entity.SolarSystem;
import com.lucasmoreno.weatherchannel.services.ForecastService;
import com.lucasmoreno.weatherchannel.services.SolarSystemService;

/**
 * Service that handles Solar system logic and his construction as well in order
 * to calculate solar system conditions.
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

	/**
	 * Creates planets base on existing configuration.
	 * 
	 * @return List of Planets
	 */

	private List<Planet> createPlanets() {
		List<Planet> planets = new ArrayList<>();

		Planet planet = Planet.builder().distanceFromSun(DefaultPlanetDistancesTypes.VULCANO.getPlanetDistance())
				.position(DefaultPlanetPositionType.DEFAULT.getDefaultPlanetPosition()).translationSpeed(1)
				.cartesianCoordinates(
						this.calculateCartesianCoodrinates(DefaultPlanetPositionType.DEFAULT.getDefaultPlanetPosition(),
								DefaultPlanetDistancesTypes.VULCANO.getPlanetDistance()))
				.build();

		planets.add(planet);

		planet = Planet.builder().distanceFromSun(DefaultPlanetDistancesTypes.BETASOIDES.getPlanetDistance())
				.position(DefaultPlanetPositionType.DEFAULT.getDefaultPlanetPosition()).translationSpeed(3)
				.cartesianCoordinates(
						this.calculateCartesianCoodrinates(DefaultPlanetPositionType.DEFAULT.getDefaultPlanetPosition(),
								DefaultPlanetDistancesTypes.BETASOIDES.getPlanetDistance()))
				.build();

		planets.add(planet);

		planet = Planet.builder().distanceFromSun(DefaultPlanetDistancesTypes.FERENGIS.getPlanetDistance())
				.position(DefaultPlanetPositionType.DEFAULT.getDefaultPlanetPosition()).translationSpeed(-5)
				.cartesianCoordinates(
						this.calculateCartesianCoodrinates(DefaultPlanetPositionType.DEFAULT.getDefaultPlanetPosition(),
								DefaultPlanetDistancesTypes.FERENGIS.getPlanetDistance()))
				.build();
		planets.add(planet);
		return planets;
	}

	/**
	 * Generates the forecast conditions for {@value} years in the future.
	 */
	@Override
	public void generateForecastByYears(long years) {

		long dayByPeriod = years * DAYS_PER_YEAR;

		for (int day = 0; day < dayByPeriod; day++) {

			this.forecastService.generateForecast(this.solarSystem);
			this.translateOneDay();
			this.solarSystem.setDay(day);

		}

		this.forecastService.generateForecastReport(years);

	}

	/**
	 * Moves the planets one day using their speeds.
	 * 
	 */
	private void translateOneDay() {
		for (Planet planet : solarSystem.getPlanets()) {
			planet.setPosition(this.set360Position(planet));
			planet.setCartesianCoordinates(
					this.calculateCartesianCoodrinates(planet.getPosition(), planet.getDistanceFromSun()));
		}
		solarSystem.setAstronomicalEvents();
	}

	/**
	 * Sets the position base on 360 limit to avoid breaking 360 degrees.
	 * 
	 * @param Planet to be translate
	 * @return new position for that Planet
	 */
	private double set360Position(Planet planet) {
		double position = planet.getPosition() + planet.getTranslationSpeed();
		if (position > 360.0) {
			position -= 360;
		} else if (position < -360.0) {
			position += 360;
		}
		return position;
	}

	/**
	 * Planets are configured with polar coordinates and we need Cartesian
	 * coordinates to perform different calculations.
	 * 
	 * @param position.       Position on degrees.
	 * @param distanceFromSun Radius from coordinates P(0,0)
	 * @return CartesianCoordinates based on polar position.
	 */
	private Point2D calculateCartesianCoodrinates(double position, double distanceFromSun) {
		double xPosition = java.lang.Math.cos(java.lang.Math.toRadians(position)) * distanceFromSun;
		double yPosition = java.lang.Math.sin(java.lang.Math.toRadians(position)) * distanceFromSun;
		return new Point2D.Double(xPosition, yPosition);
	}

}
