package com.lucasmoreno.weatherchannel.dto;

import java.awt.geom.Point2D;

/**
 * Data structure to represent a planet on a solar system.
 * 
 * @author Lucas Moreno
 *
 */
public class PlanetDto {

	private double distanceFromSun;
	private double position;
	private double translationSpeed;
	private Point2D cartesianCoordinates;

	public PlanetDto(double distanceFromSun, double position, double translationSpeed) {
		this.distanceFromSun = distanceFromSun;
		this.position = position;
		this.translationSpeed = translationSpeed;
	}

	public double getDistanceFromSun() {
		return distanceFromSun;
	}

	public void setDistanceFromSun(double distanceFromSun) {
		this.distanceFromSun = distanceFromSun;
	}

	public double getPosition() {
		return position;
	}

	public void setPosition(double position) {
		this.position = position;
	}

	public double getTranslationSpeed() {
		return translationSpeed;
	}

	public void setTranslationSpeed(double translationSpeed) {
		this.translationSpeed = translationSpeed;
	}

	public Point2D getCartesianCoordinates() {
		return cartesianCoordinates;
	}

	public void setCartesianCoordinates(Point2D cartesianCoordinates) {
		this.cartesianCoordinates = cartesianCoordinates;
	}

}
