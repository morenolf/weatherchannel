package com.lucasmoreno.weatherchannel.dto;

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
	private CartesianCoordinatesDto cartesianCoordinatesDto;

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

	public CartesianCoordinatesDto getCartesianCoordinatesDto() {
		return cartesianCoordinatesDto;
	}

	public void setCartesianCoordinatesDto(CartesianCoordinatesDto cartesianCoordinatesDto) {
		this.cartesianCoordinatesDto = cartesianCoordinatesDto;
	}

}
