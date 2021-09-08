package com.lucasmoreno.weatherchannel.builder;
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

}
