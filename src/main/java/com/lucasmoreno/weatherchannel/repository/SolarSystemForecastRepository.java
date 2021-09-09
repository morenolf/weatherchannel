package com.lucasmoreno.weatherchannel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasmoreno.weatherchannel.entity.SolarSystemForecastEntity;

public interface SolarSystemForecastRepository extends JpaRepository<SolarSystemForecastEntity, Long>{

	
}
