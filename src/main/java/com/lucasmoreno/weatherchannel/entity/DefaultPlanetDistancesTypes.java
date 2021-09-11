package com.lucasmoreno.weatherchannel.entity;

public enum DefaultPlanetDistancesTypes {

	VULCANO(500.0),FERENGIS(2000.0),BETASOIDES(1000.0);
	
	private double planetDistance;
	 
	private DefaultPlanetDistancesTypes(double planetDistance) {
		this.planetDistance = planetDistance;
	}
 
	public double getPlanetDistance() {
		return planetDistance;
	}
}
