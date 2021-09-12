package com.lucasmoreno.weatherchannel.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasmoreno.weatherchannel.entity.ForecastType;
import com.lucasmoreno.weatherchannel.entity.SolarSystem;
import com.lucasmoreno.weatherchannel.entity.SolarSystemForecastEntity;
import com.lucasmoreno.weatherchannel.repository.SolarSystemForecastRepository;
import com.lucasmoreno.weatherchannel.services.ForecastService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Forecast service to handle the logic to calculate solar system weather
 * conditions.
 * 
 * @author Lucas Moreno
 *
 */
@Service
@Slf4j
public class ForecastServiceImpl implements ForecastService {	

	@Autowired
	private SolarSystemForecastRepository solarSystemforecastRepository;

	private List<SolarSystemForecastEntity> solarSystemForecastList = new ArrayList<>();

	/**
	 * Retrieves forecast from repository for a specific day.
	 * 
	 * @return solarSystemForecastEntity with forecast for that day.
	 */
	@Override
	public SolarSystemForecastEntity getForecastByDay(long day) {
		log.info("Retrieving forecast by day");
		return solarSystemforecastRepository.getById(day);
	}

	/**
	 * Generates forecast for the solar system base on current solar distribution.
	 * 
	 */
	@Override
	public void generateForecast(SolarSystem solarSystem) {
		SolarSystemForecastEntity solarSystemForecastEntity = this.getTodayForecast(solarSystem);
		this.saveForecast(solarSystemForecastEntity);
		solarSystemForecastList.add(solarSystemForecastEntity);
	}

	/**
	 * Saves forecast on repository.
	 * 
	 * @param newSolarSystemForecastEntity
	 */
	private void saveForecast(SolarSystemForecastEntity newSolarSystemForecastEntity) {
		this.solarSystemforecastRepository.save(newSolarSystemForecastEntity);
	}

	/**
	 * Generates and returns forecast for solar system. Calculates the forecast
	 * event base on solar condition.
	 * 
	 * @param solarSystemDto
	 * @return SolarSystemForecastEntity
	 */
	public SolarSystemForecastEntity getTodayForecast(SolarSystem solarSystemDto) {
		SolarSystemForecastEntity solarSystemForecastEntity = new SolarSystemForecastEntity();
		solarSystemForecastEntity.setDay(solarSystemDto.getDay());
		if (solarSystemDto.isSolarSystemAlignment()) {
			solarSystemForecastEntity.setForecast(ForecastType.DROUGHT);
		} else if (solarSystemDto.isPlanetsAlignment()) {
			solarSystemForecastEntity.setForecast(ForecastType.RAIN);
		} else if (solarSystemDto.isGemotricalAlignment()) {
			solarSystemForecastEntity.setForecast(ForecastType.OPTIMAL);
		} else {
			solarSystemForecastEntity.setForecast(ForecastType.NORMAL);
		}

		return solarSystemForecastEntity;
	}

	/**
	 * Generates a report base on N years. 
	 * For that period of time calculates: 
	 * 		How many Rainy days. 
	 * 		How many droughts. 
	 * 		How many days with optimal conditions.
	 * 		Longest period of rain.
	 */
	@Override
	public void generateForecastReport(long years) {
		log.info("Creating forecast report");
		log.info("Number of dorughts in the next " + years + " years "
				+ String.valueOf(this.getForecastDays(ForecastType.DROUGHT)));
		log.info("Number of rainy days in the next " + years + " years "
				+ String.valueOf(this.getForecastDays(ForecastType.RAIN)));
		log.info("Longest period of rain in the next " + years + " years "
				+ String.valueOf(this.getLongestRainyPeriod()));
		log.info("Number of optimal condition days in the next " + years + " years "
				+ String.valueOf(this.getForecastDays(ForecastType.OPTIMAL)));

	}

	/**
	 * Gets the count for an specific forecast type.
	 * @param forecastType
	 * @return forecast type count.
	 */
	private long getForecastDays(ForecastType forecastType) {
		return solarSystemForecastList.stream().filter(c -> c.getForecast().equals(forecastType)).count();
	}

	/**
	 * Calculates longest period of rain.
	 * @return day
	 */
	private  Long getLongestRainyPeriod() {
		return Collections.max(solarSystemForecastList, Comparator.comparing(SolarSystemForecastEntity::getTriangleArea)).getDay();
	}

}
