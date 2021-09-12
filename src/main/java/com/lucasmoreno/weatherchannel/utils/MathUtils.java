package com.lucasmoreno.weatherchannel.utils;

import java.awt.geom.Point2D;

public class MathUtils {
	private static final double EPS = 0.001;
	private static final double EPS_SQUARE = EPS * EPS;

	/**
	 * Given 3 points P(x,y) calculates the area of a possible triangle.
	 * 
	 * @param pointA
	 * @param pointC
	 * @param pointB
	 * @return
	 */

	public static double calculateTriangleArea(Point2D pointA, Point2D pointB, Point2D pointC) {
		return Math.abs((pointA.getX() - pointC.getX()) * (pointB.getY() - pointA.getY())
				- (pointA.getX() - pointB.getX()) * (pointC.getY() - pointA.getY())) * 0.5;
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

	public static boolean accuratePointInTriangle(Point2D p, Point2D p1, Point2D p2, Point2D p3) {
		if (!pointInTriangleBoundingBox(p, p1, p2, p3)) {
			return false;
		}
		if (nativePointInTriangle(p, p1, p2, p3)) {
			return true;
		}
		if (distanceSquarePointToSegment(p1, p2, p) <= EPS_SQUARE) {
			return true;
		}
		if (distanceSquarePointToSegment(p2, p3, p) <= EPS_SQUARE) {
			return true;
		}
		return distanceSquarePointToSegment(p3, p1, p) <= EPS_SQUARE;
	}

	private static boolean pointInTriangleBoundingBox(Point2D p, Point2D p1, Point2D p2, Point2D p3) {
		var xMin = Math.min(p1.getX(), Math.min(p2.getX(), p3.getX())) - EPS;
		var xMax = Math.max(p1.getX(), Math.max(p2.getX(), p3.getX())) + EPS;
		var yMin = Math.min(p1.getY(), Math.min(p2.getY(), p3.getY())) - EPS;
		var yMax = Math.max(p1.getY(), Math.max(p2.getY(), p3.getY())) + EPS;
		return !(p.getX() < xMin || xMax < p.getX() || p.getY() < yMin || yMax < p.getY());
	}

	private static double side(Point2D p1, Point2D p2, Point2D p) {
		return (p2.getY() - p1.getY()) * (p.getX() - p1.getX()) + (-p2.getX() + p1.getX()) * (p.getY() - p1.getY());
	}

	private static boolean nativePointInTriangle(Point2D p, Point2D p1, Point2D p2, Point2D p3) {
		boolean checkSide1 = side(p1, p2, p) >= 0;
		boolean checkSide2 = side(p2, p3, p) >= 0;
		boolean checkSide3 = side(p3, p1, p) >= 0;
		return checkSide1 && checkSide2 && checkSide3;
	}

	private static double distanceSquarePointToSegment(Point2D p, Point2D p1, Point2D p2) {
		double p1_p2_squareLength = (p2.getX() - p1.getX()) * (p2.getX() - p1.getX())
				+ (p2.getY() - p1.getY()) * (p2.getY() - p1.getY());
		double dotProduct = ((p.getX() - p1.getX()) * (p2.getX() - p1.getX())
				+ (p.getY() - p1.getY()) * (p2.getY() - p1.getY())) / p1_p2_squareLength;
		if (dotProduct < 0) {
			return (p.getX() - p1.getX()) * (p.getX() - p1.getX()) + (p.getY() - p1.getY()) * (p.getY() - p1.getY());
		}
		if (dotProduct <= 1) {
			double p_p1_squareLength = (p1.getX() - p.getX()) * (p1.getX() - p.getX())
					+ (p1.getY() - p.getY()) * (p1.getY() - p.getY());
			return p_p1_squareLength - dotProduct * dotProduct * p1_p2_squareLength;
		}
		return (p.getX() - p2.getX()) * (p.getX() - p2.getX()) + (p.getY() - p2.getY()) * (p.getY() - p2.getY());
	}

}
