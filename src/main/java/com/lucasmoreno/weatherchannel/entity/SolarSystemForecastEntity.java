package com.lucasmoreno.weatherchannel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "forecast")
public class SolarSystemForecastEntity {

	@Id
	@Column(name = "DAY")
	private Long day;
	
	@Column(name = "FORECAST")
	@Enumerated(EnumType.STRING)
	private ForecastType forecast;

	@Column(name = "TRIANGLEAREA")
	private double triangleArea;
	
	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	public ForecastType getForecast() {
		return forecast;
	}

	public void setForecast(ForecastType forecast) {
		this.forecast = forecast;
	}

	public double getTriangleArea() {
		return triangleArea;
	}

	public void setTriangleArea(double triangleArea) {
		this.triangleArea = triangleArea;
	}
	
}
