package com.lucasmoreno.weatherchannel.dto;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 
 * Representation of the solar system with it's planets and sun.
 * 
 * @author Lucas Moreno
 *
 */

public class SolarSystemDto {

	private List<PlanetDto> planets;
	private Point2D sunPosition;
	private long day;
	private boolean solarSystemAlignment;
	private boolean planetsAlignment;
	private boolean gemotricalAlignment;

	public SolarSystemDto(List<PlanetDto> planets) {
		this.planets = planets;
		this.sunPosition = new Point2D.Double(0.0, 0.0);
		this.setAstronomicalEvents();
	}

	public void setAstronomicalEvents() {

		this.setSolarSystemAlignment(this.allPlanetsAlignWithSun());
		this.setPlanetsAlignment(this.onlyPlanetsAlign());
		this.setGemotricalAlignment(this.gemoetricalAlignmentWithSunInside());

	}

	private boolean gemoetricalAlignmentWithSunInside() {

		// TODO
		/*
		this.planets.get(0);
		double w1 = (e.x * (a.y - p.y) + e.y * (p.x - a.x)) / (d.x * e.y - d.y * e.x);
		double w2 = (p.y - a.y - w1 * d.y) / e.y;
		*/
		return false;
	}
	
	private double calculateTriangleArea(Point2D pointA, Point2D pointB, Point2D pointC) {
		return Math.abs((pointA.getX()*(pointB.getY()-pointC.getY()) + pointB.getX()*(pointC.getY()-pointA.getY())+
				pointC.getX()*(pointA.getY()-pointB.getY()))/2.0);
	}

	private boolean onlyPlanetsAlign() {
		PlanetDto farestPlanetFromSun = this.planets.stream().max(Comparator.comparing(PlanetDto::getDistanceFromSun))
				.orElseThrow(NoSuchElementException::new);

		PlanetDto closestPlanetFromSun = this.planets.stream().min(Comparator.comparing(PlanetDto::getDistanceFromSun))
				.orElseThrow(NoSuchElementException::new);

		Line2D line = new Line2D.Double(closestPlanetFromSun.getCartesianCoordinates(),
				farestPlanetFromSun.getCartesianCoordinates());

		for (PlanetDto planet : planets) {
			if (!line.contains(planet.getCartesianCoordinates())) {
				return false;
			}
		}

		return true;
	}

	private boolean allPlanetsAlignWithSun() {
		PlanetDto farestPlanetFromSun = this.planets.stream().max(Comparator.comparing(PlanetDto::getDistanceFromSun))
				.orElseThrow(NoSuchElementException::new);

		Line2D line = new Line2D.Double(this.sunPosition, farestPlanetFromSun.getCartesianCoordinates());

		for (PlanetDto planet : planets) {
			if (!line.contains(planet.getCartesianCoordinates())) {
				return false;
			}
		}

		return true;
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

	public List<PlanetDto> getPlanets() {
		return planets;
	}

	public Point2D getSunPosition() {
		return sunPosition;
	}

	public void setSunPosition(Point2D sunPosition) {
		this.sunPosition = sunPosition;
	}

	public void setPlanets(List<PlanetDto> planets) {
		this.planets = planets;
	}

	public long getDay() {
		return this.day;
	}

	public void setDay(long day) {
		this.day = day;
	}
}
