package com.lucasmoreno.weatherchannel.entity;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.lucasmoreno.weatherchannel.exception.SolarSystemException;

public class SolarSystemTest {

	private static final List<Boolean> ALL_CONDITIONS_RESULT = Arrays.asList(true, true, true);
	private static final List<Boolean> ALL_CONDITIONS2_RESULT = Arrays.asList(false, false, false);
	
	private static List<Double> ALL_CONDITIONS_CONFIG_PLANET1 = Arrays.asList(500.0, 1.0);
	private static List<Double> ALL_CONDITIONS_CONFIG_PLANET2 = Arrays.asList(1000.0, 1.0);
	private static List<Double> ALL_CONDITIONS_CONFIG_PLANET3 = Arrays.asList(2000.0, 1.0);
	private static List<List<Double>> ALL_CONDITIONS_CONFIG_LIST = Arrays.asList(ALL_CONDITIONS_CONFIG_PLANET1,
			ALL_CONDITIONS_CONFIG_PLANET2, ALL_CONDITIONS_CONFIG_PLANET3);

	private static List<Double> ALL_CONDITIONS_CONFIG2_PLANET1 = Arrays.asList(500.0, 1.0);
	private static List<Double> ALL_CONDITIONS_CONFIG2_PLANET2 = Arrays.asList(1000.0, 4.0);
	private static List<Double> ALL_CONDITIONS_CONFIG2_PLANET3 = Arrays.asList(2000.0, 8.0);
	private static List<List<Double>> ALL_CONDITIONS2_CONFIG_LIST = Arrays.asList(ALL_CONDITIONS_CONFIG2_PLANET1,
			ALL_CONDITIONS_CONFIG2_PLANET2, ALL_CONDITIONS_CONFIG2_PLANET3);

	/**
	 * Test the default Solar System position under 10 years of translation. This
	 * test should have as result: Solar alignment. There is no planet alignment
	 * without sun because of solar default conditions. Triangle alignment with sun
	 * inside.
	 */

	@Test
	public void testDefaultSolarSystem() {
		SolarSystem solarSystem = null;
		boolean isSunAlignment = false;
		boolean isPlanetsAlignment = false;
		boolean isTrianglealignment = false;
		int years = 360 * 10;
		try {
			solarSystem = new SolarSystem(this.createDefaultPlanets());

			for (int i = 0; i < years; i++) {

				if (solarSystem.isSolarSystemAlignment()) {
					isSunAlignment = true;
				}

				if (solarSystem.isPlanetsAlignment()) {
					isPlanetsAlignment = true;
				}

				if (solarSystem.isGemotricalAlignment()){
					isTrianglealignment = true;
				}

				this.translate(solarSystem);
			}
		} catch (Exception e) {
			fail();
		}
		assertTrue(isSunAlignment);
		assertTrue(!isPlanetsAlignment);
		assertTrue(isTrianglealignment);
	}

	@Test
	public void test1() {

		this.testAllSolarSystemConditions(ALL_CONDITIONS_CONFIG_LIST, ALL_CONDITIONS_RESULT);

	}
	
	@Test
	public void test2() {

		List<Boolean> results = this.testAllSolarSystemConditions(ALL_CONDITIONS2_CONFIG_LIST, ALL_CONDITIONS2_RESULT);
		for(Boolean result : results) {
			assertTrue(result);
		}		

	}

	public List<Boolean> testAllSolarSystemConditions(List<List<Double>> conditions, List<Boolean> results) {
		List<Boolean> result = new ArrayList<>();
		SolarSystem solarSystem = null;
		Boolean isSunAlignment = false;
		Boolean isPlanetsAlignment = false;
		Boolean isTriangleAlignment = false;
		int years = 360 * 10;
		try {
			solarSystem = new SolarSystem(this.createAlterDefaultPlanets(conditions));

			for (int i = 0; i < years; i++) {

				if (solarSystem.isSolarSystemAlignment()) {
					isSunAlignment = true;
				}

				if (solarSystem.isPlanetsAlignment()) {
					isPlanetsAlignment = true;
				}

				if (solarSystem.isGemotricalAlignment()) {
					isTriangleAlignment = true;
				}

				this.translate(solarSystem);
			}
		} catch (Exception e) {
			fail();
		}

		result.add(results.get(0) == isSunAlignment);
		result.add(results.get(0) == isPlanetsAlignment);
		result.add(results.get(0) == isTriangleAlignment);
		return result;
	}

	private void translate(SolarSystem solarSystem) throws SolarSystemException {
		for (Planet planet : solarSystem.getPlanets()) {
			planet.setPosition(setPosition360(planet));
			planet.setCartesianCoordinates(
					this.calculateCartesianCoodrinates(planet.getPosition(), planet.getDistanceFromSun()));
		}
		solarSystem.setAstronomicalEvents();
	}

	private double setPosition360(Planet planet) {
		double position = planet.getPosition() + planet.getTranslationSpeed();
		if (position > 360.0) {
			position -= 360;
		} else if (position < -360.0) {
			position += 360;
		}
		return position;
	}

	public List<Planet> createDefaultPlanets() {
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

	public List<Planet> createAlterDefaultPlanets(List<List<Double>> configList) {
		List<Planet> planets = new ArrayList<>();

		Planet planet = Planet.builder().distanceFromSun(configList.get(0).get(0)).position(configList.get(0).get(1))
				.cartesianCoordinates(
						this.calculateCartesianCoodrinates(configList.get(0).get(1), configList.get(0).get(0)))
				.build();

		planets.add(planet);

		planet = Planet.builder().distanceFromSun(configList.get(1).get(0)).position(configList.get(1).get(1))
				.cartesianCoordinates(
						this.calculateCartesianCoodrinates(configList.get(1).get(1), configList.get(1).get(0)))
				.build();

		planets.add(planet);

		planet = Planet.builder().distanceFromSun(configList.get(2).get(0)).position(configList.get(2).get(1))
				.cartesianCoordinates(
						this.calculateCartesianCoodrinates(configList.get(2).get(1), configList.get(2).get(0)))
				.build();
		planets.add(planet);
		return planets;
	}

	private Point2D calculateCartesianCoodrinates(double position, double distanceFromSun) {
		double xPosition = distanceFromSun * java.lang.Math.cos(java.lang.Math.toRadians(position));
		double yPosition = distanceFromSun * java.lang.Math.sin(java.lang.Math.toRadians(position));
		return new Point2D.Double(xPosition, yPosition);
	}

}
