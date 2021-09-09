package com.lucasmoreno.weatherchannel.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucasmoreno.weatherchannel.dto.ForecastType;
import com.lucasmoreno.weatherchannel.dto.SolarSystemDto;
import com.lucasmoreno.weatherchannel.entity.SolarSystemForecastEntity;
import com.lucasmoreno.weatherchannel.repository.SolarSystemForecastRepository;
import com.lucasmoreno.weatherchannel.services.ForecastService;
import com.lucasmoreno.weatherchannel.services.SolarSystemService;

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
	private SolarSystemService solarSystemService;

	private List<SolarSystemForecastEntity> solarSystemForecastList;

	@Override
	public void generateForecast(long dayOfTheYear, SolarSystemDto solarSystemDto) {
		SolarSystemForecastEntity solarSystemForecastEntity = this.getTodayForecast(solarSystemDto);
		this.saveForecast(solarSystemForecastEntity);
		solarSystemForecastList.add(solarSystemForecastEntity);
	}

	private void saveForecast(SolarSystemForecastEntity newSolarSystemForecastEntity) {
		this.solarSystemforecastRepository.save(newSolarSystemForecastEntity);
	}

	public SolarSystemForecastEntity getTodayForecast(SolarSystemDto solarSystemDto) {
		SolarSystemForecastEntity solarSystemForecastEntity = new SolarSystemForecastEntity();
		solarSystemForecastEntity.setDay(solarSystemService.getDay());
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
	
		return 0;
	}

}
