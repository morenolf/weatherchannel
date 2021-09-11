package com.lucasmoreno.weatherchannel.entity;

public enum DefaultPlanetPositionType {
	DEFAULT(0.0);
	
	private double planetPosition;
	 
	private DefaultPlanetPositionType(double planetPosition) {
		this.planetPosition = planetPosition;
	}
 
	public double getDefaultPlanetPosition() {
		return planetPosition;
	}
}
