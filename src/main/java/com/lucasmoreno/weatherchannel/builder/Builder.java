package com.lucasmoreno.weatherchannel.builder;

import com.lucasmoreno.weatherchannel.dto.CartesianCoordinatesDto;

/**
 * 
 * Builder interface for solar system builder pattern
 * 
 * @author Lucas Moreno
 *
 */
public interface Builder {

	void setDistanceFromSun(double distanceFromSun);

	void setPosition(double position);

	void setTranslationSpeed(double translationSpeed);

	void setCartesianCoordinates(CartesianCoordinatesDto cartesianCoordinatesDto);

}
