package com.lucasmoreno.weatherchannel.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucasmoreno.weatherchannel.entity.ForecastType;
import com.lucasmoreno.weatherchannel.entity.Planet;
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
public class ForecastServiceImpl implements ForecastService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ForecastServiceImpl.class);

	@Autowired
	private SolarSystemForecastRepository solarSystemforecastRepository;

	@Autowired
	private List<SolarSystemForecastEntity> solarSystemForecastList;

	@Override
	public void generateForecast(SolarSystem solarSystem) {
		SolarSystemForecastEntity solarSystemForecastEntity = this.getTodayForecast(solarSystem);
		this.saveForecast(solarSystemForecastEntity);
		solarSystemForecastList.add(solarSystemForecastEntity);
	}

	private void saveForecast(SolarSystemForecastEntity newSolarSystemForecastEntity) {
		this.solarSystemforecastRepository.save(newSolarSystemForecastEntity);
	}

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
	
/*
	private SolarSystemForecastEntity calculateForecast(long day, SolarSystemDto solarSystemDto) {
		SolarSystemForecastEntity solarSystemForecastEntity = new SolarSystemForecastEntity();

		solarSystemForecastEntity.setDay(day);

		return solarSystemForecastEntity;
	}
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

	private long getForecastDays(ForecastType forecastType) {
		return solarSystemForecastList.stream().filter(c -> c.getForecast().equals(forecastType)).count();
	}

	private long getLongestRainyPeriod() {
		int count = 0;
		int max = 0;
		ForecastType lastForecast = ForecastType.NORMAL;
		List<Integer> counts = new ArrayList<>();
		
		for (SolarSystemForecastEntity solarSystemForecastEntity : this.solarSystemForecastList) {
			
			if (solarSystemForecastEntity.getForecast().equals(ForecastType.RAIN)) {
				if(lastForecast.equals(ForecastType.RAIN)) {
					count += 1;
				}else {
					counts.add(count);
					count = 1;					
				}
			}else {
				count = 0;
			}
			
			lastForecast = solarSystemForecastEntity.getForecast();
		}
		
		if(count != 0) {
			counts.add(count);
		}
		
		
		return counts.stream()
				.mapToInt(v -> v)
				.max()
				.orElseThrow(NoSuchElementException::new);
	}

}
