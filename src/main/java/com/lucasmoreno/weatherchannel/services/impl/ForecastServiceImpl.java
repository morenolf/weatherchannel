package com.lucasmoreno.weatherchannel.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasmoreno.weatherchannel.entity.ForecastType;
import com.lucasmoreno.weatherchannel.entity.SolarSystem;
import com.lucasmoreno.weatherchannel.entity.SolarSystemForecastEntity;
import com.lucasmoreno.weatherchannel.repository.SolarSystemForecastRepository;
import com.lucasmoreno.weatherchannel.services.ForecastService;

/**
 * 
 * Forecast service to handle the logic to calculate solar system weather
 * conditions.
 * 
 * @author Lucas Moreno
 *
 */
@Service
public class ForecastServiceImpl implements ForecastService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ForecastServiceImpl.class);

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
		LOGGER.info("Retrieving forecast by day");
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
		LOGGER.info("Creating forecast report");
		LOGGER.info("Number of dorughts in the next " + years + " years "
				+ String.valueOf(this.getForecastDays(ForecastType.DROUGHT)));
		LOGGER.info("Number of rainy days in the next " + years + " years "
				+ String.valueOf(this.getForecastDays(ForecastType.RAIN)));
		LOGGER.info("Longest period of rain in the next " + years + " years "
				+ String.valueOf(this.getLongestRainyPeriod()));
		LOGGER.info("Number of optimal condition days in the next " + years + " years "
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
	 * @return
	 */
	private long getLongestRainyPeriod() {

		ForecastType lastForecast = ForecastType.NORMAL;
		List<Integer> counts = new ArrayList<>();
		int count = 0;

		for (SolarSystemForecastEntity solarSystemForecastEntity : this.solarSystemForecastList) {

			if (solarSystemForecastEntity.getForecast().equals(ForecastType.RAIN)) {
				if (lastForecast.equals(ForecastType.RAIN)) {
					count += 1;
				} else {
					counts.add(count);
					count = 1;
				}
			} else {
				count = 0;
			}

			lastForecast = solarSystemForecastEntity.getForecast();
		}

		if (count != 0) {
			counts.add(count);
		}

		return counts.stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new);
	}

}
