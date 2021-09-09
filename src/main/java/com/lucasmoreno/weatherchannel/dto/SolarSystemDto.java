package com.lucasmoreno.weatherchannel.dto;

import java.util.List;

/**
 * 
 * Representation of the solar system with it's planets and sun.
 * 
 * @author Lucas Moreno
 *
 */

public class SolarSystemDto {

	private List<PlanetDto> planets;
	private CartesianCoordinatesDto sunPosition;
	private long day;
	private boolean solarSystemAlignment;
	private boolean planetsAlignment;
	private boolean gemotricalAlignment;

	public SolarSystemDto(List<PlanetDto> planets) {
		this.planets = planets;
		sunPosition.setxPosition(0.0);
		sunPosition.setyPosition(0.0);
		this.setAstronomicalEvents();
	}

	public void setAstronomicalEvents() {

		this.setSolarSystemAlignment(this.allPlanetsAlignWithSun());
		this.setPlanetsAlignment(this.onlyPlanetsAlign());
		this.setGemotricalAlignment(this.gemoetricalAlignmentWithSunInside());

	}

	private boolean gemoetricalAlignmentWithSunInside() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean onlyPlanetsAlign() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean allPlanetsAlignWithSun() {
		//TODO
		return false;
	}

	public boolean isSolarSystemAlignment() {
		return solarSystemAlignment;
	}

	public void setSolarSystemAlignment(boolean solarSystemAlingment) {
		this.solarSystemAlignment = solarSystemAlingment;
	}

	public boolean isPlanetsAlignment() {
		return planetsAlignment;
	}

	public void setPlanetsAlignment(boolean planetsAlignment) {
		this.planetsAlignment = planetsAlignment;
	}

	public boolean isGemotricalAlignment() {
		return gemotricalAlignment;
	}

	public void setGemotricalAlignment(boolean gemotricalAlingment) {
		this.gemotricalAlignment = gemotricalAlingment;
	}

	public List<PlanetDto> getPlanets() {
		return planets;
	}

	public CartesianCoordinatesDto getSunPosition() {
		return sunPosition;
	}

	public void setPlanets(List<PlanetDto> planets) {
		this.planets = planets;
	}

	public void setSunPosition(CartesianCoordinatesDto sunPosition) {
		this.sunPosition = sunPosition;
	}

	public long getDay() {
		return this.day;
	}

	public void setDay(long day) {
		this.day = day;
	}
}
