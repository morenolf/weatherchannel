package com.lucasmoreno.weatherchannel.builder.impl;

import java.awt.geom.Point2D;

import com.lucasmoreno.weatherchannel.builder.Builder;
import com.lucasmoreno.weatherchannel.dto.PlanetDto;

public class PlanetBuilder implements Builder {

	private double distanceFromSun;
	private double position;
	private double translationSpeed;
	private Point2D cartesianCoordinates;
	
	@Override
	public void setDistanceFromSun(double distanceFromSun) {
		this.distanceFromSun = distanceFromSun;

	}

	@Override
	public void setPosition(double position) {
		this.position = position;

	}

	@Override
	public void setTranslationSpeed(double translationSpeed) {
		this.translationSpeed = translationSpeed;

	}

	public PlanetDto getResult() {
		return new PlanetDto(this.distanceFromSun, this.position, this.translationSpeed);
	}

	@Override
	public void setCartesianCoordinates(Point2D cartesianCoordinates) {
		this.cartesianCoordinates = cartesianCoordinates;		
	}

	public Point2D getCartesianCoordinates() {
		return cartesianCoordinates;
	}



}
