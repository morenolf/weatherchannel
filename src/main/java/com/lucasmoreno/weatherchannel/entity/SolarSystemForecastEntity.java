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
	@Column(name = "day")
	private long day;
	
	@Column(name = "forecast")
	@Enumerated(EnumType.STRING)
	private ForecastType forecast;

	@Column(name = "triangleArea")
	private long triangleArea;
	
	public long getDay() {
		return day;
	}

	public void setDay(long day) {
		this.day = day;
	}

	public ForecastType getForecast() {
		return forecast;
	}

	public void setForecast(ForecastType forecast) {
		this.forecast = forecast;
	}

	public long getTriangleArea() {
		return triangleArea;
	}

	public void setTriangleArea(long triangleArea) {
		this.triangleArea = triangleArea;
	}
	
}
