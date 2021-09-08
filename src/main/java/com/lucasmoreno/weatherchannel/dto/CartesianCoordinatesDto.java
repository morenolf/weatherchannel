package com.lucasmoreno.weatherchannel.dto;

/**
 * 
 * Cartesian coordinates for multiple uses on geometry calculations.
 * 
 * @author Lucas Moreno
 *
 */

public class CartesianCoordinatesDto {

	private double xPosition;
	private double yPosition;

	public CartesianCoordinatesDto(double xPosition, double yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public double getxPosition() {
		return xPosition;
	}

	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}

	public double getyPosition() {
		return yPosition;
	}

	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}

}
