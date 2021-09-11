package com.lucasmoreno.weatherchannel.entity;

import java.awt.geom.Point2D;
import java.util.List;

import com.lucasmoreno.weatherchannel.utils.MathUtils;

/**
 * 
 * Representation of the solar system with his planets and sun.
 * 
 * @author Lucas Moreno
 *
 */

public class SolarSystem {

	private List<Planet> planets;
	private Point2D sunPosition;
	private long day;
	private boolean solarSystemAlignment;
	private boolean planetsAlignment;
	private boolean gemotricalAlignment;
	private double areaSize;

	/**
	 * Construct solar system base on existing planets.
	 * 
	 * @param planets
	 */
	public SolarSystem(List<Planet> planets) {
		this.planets = planets;
		this.sunPosition = new Point2D.Double(0.0, 0.0);
		this.setAstronomicalEvents();
	}

	/**
	 * After creating solar system, this defines the different astronomical events
	 * at the moment.
	 * 
	 */
	public void setAstronomicalEvents() {

		Point2D pointA = this.getPlanets().get(0).getCartesianCoordinates();
		Point2D pointB = this.getPlanets().get(1).getCartesianCoordinates();
		Point2D pointC = this.getPlanets().get(2).getCartesianCoordinates();
		this.setSolarSystemAlignment(this.allPlanetsAlignWithSun(pointA, pointB, pointC));
		this.setPlanetsAlignment(this.allPlanetsAlign(pointA, pointB, pointC));
		this.setAreaSize(this.calculateAreaSize(pointA, pointB, pointC));
	}

	/**
	 * Based on 3 points P(x,y) and the sun, calculates if all of them are align.
	 * 
	 * @param pointA
	 * @param pointB
	 * @param pointC
	 * @return If the 3 points P(x,y) and the sun are align or not.
	 */

	private boolean allPlanetsAlignWithSun(Point2D pointA, Point2D pointB, Point2D pointC) {

		return this.allPlanetsAlign(pointA, pointB, pointC)
				&& MathUtils.areCollinear(pointB, pointC, this.sunPosition);
	}

	/**
	 * Based on 3 points P(x,y), calculates if all of them are align.
	 * 
	 * @param pointA
	 * @param pointB
	 * @param pointC
	 * @return If the 3 points P(x,y) are align or not.
	 */
	private boolean allPlanetsAlign(Point2D pointA, Point2D pointB, Point2D pointC) {

		return MathUtils.areCollinear(pointA, pointB, pointC);
	}

	/**
	 * Based on 3 points P(x,y) calculates if they formed a triangle and then return
	 * the area of the triangle.
	 * 
	 * @param pointA
	 * @param pointB
	 * @param pointC
	 * @return area of a triangle. If no triangle exist, return 0.0.
	 */

	private double calculateAreaSize(Point2D pointA, Point2D pointB, Point2D pointC) {

		if (!MathUtils.areCollinear(pointA, pointB, pointC)) {
			return MathUtils.calculateTriangleArea(pointA, pointB, pointC);
		}
		return 0.0;
	}

	
	/**
	 * Base on 3 points P(x,y), returns if a 4rd point it's inside that triangle.
	 * 
	 * @param pointA
	 * @param pointB
	 * @param pointC
	 * @return If a determine point it's inside a triangle. If there is no triangle, return false.
	 */
	public boolean isGemoetricalAlignmentWithSunInside(Point2D pointA, Point2D pointB, Point2D pointC) {

		if (this.areaSize != 0.0) {
			double areaABC = MathUtils.calculateTriangleArea(pointA, pointB, pointC);
			double areaPBC = MathUtils.calculateTriangleArea(this.sunPosition, pointB, pointC);
			double areaPAC = MathUtils.calculateTriangleArea(this.sunPosition, pointA, pointC);
			double areaPAB = MathUtils.calculateTriangleArea(this.sunPosition, pointA, pointB);

			return areaABC == areaPBC + areaPAC + areaPAB;
		}

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

	public List<Planet> getPlanets() {
		return planets;
	}

	public Point2D getSunPosition() {
		return sunPosition;
	}

	public void setSunPosition(Point2D sunPosition) {
		this.sunPosition = sunPosition;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}

	public long getDay() {
		return this.day;
	}

	public void setDay(long day) {
		this.day = day;
	}

	public double getAreaSize() {
		return areaSize;
	}

	public void setAreaSize(double areaSize) {
		this.areaSize = areaSize;
	}
}
