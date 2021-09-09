package com.lucasmoreno.weatherchannel.builder;

import java.awt.geom.Point2D;

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

	void setCartesianCoordinates(Point2D cartesianCoordinatesDto);

}
