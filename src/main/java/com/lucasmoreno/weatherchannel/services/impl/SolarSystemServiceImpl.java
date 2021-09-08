package com.lucasmoreno.weatherchannel.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasmoreno.weatherchannel.builder.impl.PlanetBuilder;
import com.lucasmoreno.weatherchannel.director.SolarSystemDirector;
import com.lucasmoreno.weatherchannel.dto.CartesianCoordinatesDto;
import com.lucasmoreno.weatherchannel.dto.PlanetDto;
import com.lucasmoreno.weatherchannel.services.SolarSystemService;

/**
 * Service that handles Solar system logic and his construction as well in order
 * to calculate forecast.
 * 
 * @author Lucas Moreno
 *
 */
@Service
public class SolarSystemServiceImpl implements SolarSystemService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SolarSystemServiceImpl.class);

	private List<PlanetDto> planets;

	@Autowired
	private SolarSystemDirector solarSystemDirector;

	/**
	 * Creates the Solar system based on builder pattern using known planets and
	 * their properties
	 */
	public SolarSystemServiceImpl() {
		this.planets = new ArrayList<>();

		PlanetBuilder planetBuilder = new PlanetBuilder();
		solarSystemDirector.constructBetasoides(planetBuilder);
		this.planets.add(planetBuilder.getResult());
		solarSystemDirector.constructVulcan(planetBuilder);
		this.planets.add(planetBuilder.getResult());
		solarSystemDirector.constructBetasoides(planetBuilder);
		this.planets.add(planetBuilder.getResult());
	}

}
