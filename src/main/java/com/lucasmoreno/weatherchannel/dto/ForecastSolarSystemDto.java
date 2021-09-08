package com.lucasmoreno.weatherchannel.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "forecast")
public class ForecastSolarSystemDto {

	@Id
	@Column(name = "day")
	private int day;
	
	@Column(name = "forecast")
	private int forecast;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getForecast() {
		return forecast;
	}

	public void setForecast(int forecast) {
		this.forecast = forecast;
	}
	
}
