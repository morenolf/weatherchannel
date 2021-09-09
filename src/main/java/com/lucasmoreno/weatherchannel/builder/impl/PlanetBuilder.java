package com.lucasmoreno.weatherchannel.builder.impl;

import com.lucasmoreno.weatherchannel.builder.Builder;
import com.lucasmoreno.weatherchannel.dto.CartesianCoordinatesDto;
import com.lucasmoreno.weatherchannel.dto.PlanetDto;

public class PlanetBuilder implements Builder {

	private double distanceFromSun;
	private double position;
	private double translationSpeed;
	private CartesianCoordinatesDto cartesianCoordinatesDto;
	
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
	public void setCartesianCoordinates(CartesianCoordinatesDto cartesianCoordinatesDto) {
		this.cartesianCoordinatesDto = cartesianCoordinatesDto;		
	}

	public CartesianCoordinatesDto getCartesianCoordinatesDto() {
		return cartesianCoordinatesDto;
	}



}
