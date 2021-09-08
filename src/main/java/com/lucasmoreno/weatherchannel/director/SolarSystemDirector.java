package com.lucasmoreno.weatherchannel.director;

import com.lucasmoreno.weatherchannel.builder.Builder;
import com.lucasmoreno.weatherchannel.dto.CartesianCoordinatesDto;

/**
 * Director for builder pattern dedicated to build Solar system with their
 * different planets
 * 
 * @author Lucas Moreno
 *
 */

public class SolarSystemDirector {

	/**
	 * Creates a Ferengi planet builder based on Ferengi attributes.
	 * 
	 * @param Planet builder
	 */
	public void constructFerengi(Builder builder) {
		double distanceFromSun = 500.0;
		double position = 0.0;
		builder.setDistanceFromSun(distanceFromSun);
		builder.setPosition(0.0);
		builder.setTranslationSpeed(1);
		this.calculateCartesianCoodrinates(position, distanceFromSun);
	}

	/**
	 * Creates a Vulcan planet builder based on Vulcan attributes.
	 * 
	 * @param Planet builder
	 */

	public void constructVulcan(Builder builder) {
		double distanceFromSun = 2000.0;
		double position = 0.0;
		builder.setDistanceFromSun(distanceFromSun);
		builder.setPosition(0.0);
		builder.setTranslationSpeed(3);
		this.calculateCartesianCoodrinates(position, distanceFromSun);
	}

	/**
	 * Creates a Betasoides planet builder based on Betasoides attributes.
	 * 
	 * @param Planet builder
	 */

	public void constructBetasoides(Builder builder) {
		double distanceFromSun = 1000.0;
		double position = 0.0;
		builder.setDistanceFromSun(distanceFromSun);
		builder.setPosition(0.0);
		builder.setTranslationSpeed(-5);
		this.calculateCartesianCoodrinates(position, distanceFromSun);
	}

	public CartesianCoordinatesDto calculateCartesianCoodrinates(double position, double distanceFromSun) {
		double xPosition = java.lang.Math.cos(java.lang.Math.toRadians(position)) * distanceFromSun;
		double yPosition = java.lang.Math.sin(java.lang.Math.toRadians(position)) * distanceFromSun;
		return new CartesianCoordinatesDto(xPosition, yPosition);
	}

}
