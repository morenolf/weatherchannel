package com.lucasmoreno.weatherchannel.entity;

import java.awt.geom.Point2D;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lucasmoreno.weatherchannel.exception.SolarSystemException;
import com.lucasmoreno.weatherchannel.services.impl.ForecastServiceImpl;
import com.lucasmoreno.weatherchannel.utils.MathUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Representation of the solar system with his planets and sun.
 * 
 * @author Lucas Moreno
 *
 */
@Slf4j
public class SolarSystem {

	private List<Planet> planets;
	private Point2D sunPosition;
	private long day;
	private boolean solarSystemAlignment;
	private boolean planetsAlignment;
	private boolean gemotricalAlignment;
	private double areaSize;

	/**
	 * Construct solar system base on existing planets.
	 * 
	 * @param planets
	 * @throws SolarSystemException
	 */
	public SolarSystem(List<Planet> planets) throws SolarSystemException {
		validatePlanets(planets);
		this.planets = planets;
		this.sunPosition = new Point2D.Double(0.0, 0.0);
		this.setAstronomicalEvents();
	}

	/**
	 * Validates the planets configuration.
	 * Solar System doesn't allow: 
	 * Empty solar system.
	 * Less or more than 3 planets. 
	 * 
	 * @param planets
	 * @throws SolarSystemException
	 */
	private void validatePlanets(List<Planet> planets) throws SolarSystemException {
		String message = "";
		if (planets.isEmpty()) {
			message = "Solar system doesn't allow empty solar system. Planets must be input.";
		} else if (planets.size() > 2 && planets.size() < 3 ) {
			message = "Solar system doens't have implemented any other configuration than 3 plants.";
		} else {
			for (Planet planet : planets) {
				if (planet.getDistanceFromSun() < 0.0) {
					message = "Solar system doesn't allow negative planet distance from sun.";
				}
			}
		}
		if (!message.isEmpty()) {
			log.error(message);
			throw new SolarSystemException(message);
		}
	}

	/**
	 * After creating solar system, this defines the different astronomical events
	 * at the moment.
	 * 
	 * @throws SolarSystemException
	 * 
	 */
	public void setAstronomicalEvents() throws SolarSystemException{
		this.validatePlanets(this.planets);
		Point2D pointA = this.getPlanets().get(0).getCartesianCoordinates();
		Point2D pointB = this.getPlanets().get(1).getCartesianCoordinates();
		Point2D pointC = this.getPlanets().get(2).getCartesianCoordinates();
		this.setSolarSystemAlignment(this.allPlanetsAlignWithSun(pointA, pointB, pointC));
		this.setPlanetsAlignment(this.allPlanetsAlign(pointA, pointB, pointC));
		this.setAreaSize(this.calculateAreaSize(pointA, pointB, pointC));
		this.setGemotricalAlignment(this.isGemoetricalAlignmentWithSunInside(pointA, pointB, pointC));
	}

	/**
	 * Based on 3 points P(x,y) and the sun, calculates if all of them are align.
	 * 
	 * @param pointA
	 * @param pointB
	 * @param pointC
	 * @return If the 3 points P(x,y) and the sun are align or not.
	 */

	private boolean allPlanetsAlignWithSun(Point2D pointA, Point2D pointB, Point2D pointC) {

		return MathUtils.areCollinear(pointA, pointB, pointC) && MathUtils.areCollinear(pointB, pointC, this.sunPosition);
	}

	/**
	 * Based on 3 points P(x,y), calculates if all of them are align.
	 * 
	 * @param pointA
	 * @param pointB
	 * @param pointC
	 * @return If the 3 points P(x,y) are align or not.
	 */
	private boolean allPlanetsAlign(Point2D pointA, Point2D pointB, Point2D pointC) {

		return MathUtils.areCollinear(pointA, pointB, pointC) && !MathUtils.areCollinear(pointB, pointC, this.sunPosition);
	}

	/**
	 * Based on 3 points P(x,y) calculates if they formed a triangle and then return
	 * the area of the triangle.
	 * 
	 * @param pointA
	 * @param pointB
	 * @param pointC
	 * @return area of a triangle. If no triangle exist, return 0.0.
	 */

	private double calculateAreaSize(Point2D pointA, Point2D pointB, Point2D pointC) {

		if (!MathUtils.areCollinear(pointA, pointB, pointC)) {
			return MathUtils.calculateTriangleArea(pointA, pointB, pointC);
		}
		return 0.0;
	}

	/**
	 * Base on 3 points P(x,y), returns if a 4rd point it's inside that triangle.
	 * 
	 * @param pointA
	 * @param pointB
	 * @param pointC
	 * @return If a determine point it's inside a triangle. If there is no triangle,
	 *         return false.
	 */
	private boolean isGemoetricalAlignmentWithSunInside(Point2D pointA, Point2D pointB, Point2D pointC) {

		return MathUtils.accuratePointInTriangle(this.sunPosition, pointA, pointB, pointC);

	}

	public boolean isSolarSystemAlignment() {
		return solarSystemAlignment;
	}

	public void setSolarSystemAlignment(boolean solarSystemAlingment) {
		this.solarSystemAlignment = solarSystemAlingment;
	}

	public boolean isPlanetsAlignment() {
		return planetsAlignment;
	}

	public void setPlanetsAlignment(boolean planetsAlignment) {
		this.planetsAlignment = planetsAlignment;
	}

	public boolean isGemotricalAlignment() {
		return gemotricalAlignment;
	}

	public void setGemotricalAlignment(boolean gemotricalAlingment) {
		this.gemotricalAlignment = gemotricalAlingment;
	}

	public List<Planet> getPlanets() {
		return planets;
	}

	public Point2D getSunPosition() {
		return sunPosition;
	}

	public void setSunPosition(Point2D sunPosition) {
		this.sunPosition = sunPosition;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}

	public long getDay() {
		return this.day;
	}

	public void setDay(long day) {
		this.day = day;
	}

	public double getAreaSize() {
		return areaSize;
	}

	public void setAreaSize(double areaSize) {
		this.areaSize = areaSize;
	}
}
