package com.lucasmoreno.weatherchannel.services.impl;

import java.util.List;

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

	@Autowired
	private SolarSystemForecastRepository solarSystemforecastRepository;

	@Autowired
	private SolarSystemService solarSystemService;

	private List<SolarSystemForecastEntity> solarSystemForecastList;

	@Override
	public void generateForecast(long dayOfTheYear, SolarSystemDto solarSystemDto) {
		for (int i = 0; i <= dayOfTheYear; i++) {
			SolarSystemForecastEntity solarSystemForecastEntity = this.getTodayForecast(solarSystemDto);
			this.saveForecast(solarSystemForecastEntity);
			solarSystemForecastList.add(solarSystemForecastEntity);
		}
		this.generateForecastReport();
	}

	private void saveForecast(SolarSystemForecastEntity newSolarSystemForecastEntity) {
		this.solarSystemforecastRepository.save(newSolarSystemForecastEntity);
	}

	@Override
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
	public SolarSystemForecastEntity calculateForecast(long day, SolarSystemDto solarSystemDto) {
		SolarSystemForecastEntity solarSystemForecastEntity = new SolarSystemForecastEntity();

		solarSystemForecastEntity.setDay(day);

		return solarSystemForecastEntity;
	}

	@Override
	public void generateForecastReport() {
		// TODO Auto-generated method stub

	}

}
