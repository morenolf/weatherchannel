package com.lucasmoreno.weatherchannel.entity;

import java.awt.geom.Point2D;

import lombok.Builder;

/**
 * Data structure to represent a planet on a solar system.
 * 
 * @author Lucas Moreno
 *
 */
@Builder
public class Planet {

	private double distanceFromSun;
	private double position;
	private double translationSpeed;
	private Point2D cartesianCoordinates;

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
