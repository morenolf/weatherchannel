package com.lucasmoreno.weatherchannel.utils;

import java.awt.geom.Point2D;

public class MathUtils {

	/**
	 * Given 3 points P(x,y) calculates the area of a possible triangle.
	 * 
	 * @param pointA
	 * @param pointC
	 * @param pointB
	 * @return
	 */

	public static double calculateTriangleArea(Point2D pointA, Point2D pointB, Point2D pointC) {
		return Math
				.abs((pointA.getX() * (pointB.getY() - pointC.getY()) + pointB.getX() * (pointB.getY() - pointA.getY())
						+ pointC.getX() * (pointA.getY() - pointB.getY())) / 2.0);
	}

	/**
	 * Given 3 points P(x,y) calculates if they area align.
	 * 
	 * @param pointA
	 * @param pointB
	 * @param pointC
	 * @return if 3 points area align or not.
	 */
	public static boolean areCollinear(Point2D pointA, Point2D pointB, Point2D pointC) {

		double a = (pointB.getY() - pointA.getY()) / (pointB.getX() - pointA.getX());
		double b = (pointC.getY() - pointB.getY()) / (pointC.getX() - pointB.getX());
		return MathUtils.equalsDouble(a, b);
	}

	/**
	 * Given 2 double numbers calculates if they are equal considering 0.0001 error
	 * margin.
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	public static boolean equalsDouble(Double one, Double two) {
		final Double d1 = one != null ? one : 0.0;
		final Double d2 = two != null ? two : 0.0;

		return Math.abs(d1 - d2) < 0.0001;
	}

}
