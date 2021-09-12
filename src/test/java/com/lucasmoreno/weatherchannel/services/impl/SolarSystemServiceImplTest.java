package com.lucasmoreno.weatherchannel.services.impl;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lucasmoreno.weatherchannel.exception.SolarSystemException;
import com.lucasmoreno.weatherchannel.exception.SolarSystemServiceException;
import com.lucasmoreno.weatherchannel.services.ForecastService;
import com.lucasmoreno.weatherchannel.services.SolarSystemService;

@ExtendWith(MockitoExtension.class) 
public class SolarSystemServiceImplTest {

	@Mock
	private ForecastService forecastService;
	
	@InjectMocks
	SolarSystemService solarSystemService;
	
	@Test
	public void test() {
		try {			
			Mockito.doNothing().when(forecastService).generateForecastReport(any());
			Mockito.doNothing().when(forecastService).generateForecast(any());
			
			solarSystemService = new SolarSystemServiceImpl();
			solarSystemService.generateForecastByYears(10);
		} catch (SolarSystemException | SolarSystemServiceException e) {
			fail();
		}
	}	
	
	@Test
	public void testErrorWithZeroYears() {
		try {
			solarSystemService = new SolarSystemServiceImpl();
			solarSystemService.generateForecastByYears(0);
		} catch (SolarSystemException | SolarSystemServiceException e) {
			assertNotEquals(e, null);
		}
	}

	@Test
	public void testErrorWithNegativeYears() {
		try {
			solarSystemService = new SolarSystemServiceImpl();
			solarSystemService.generateForecastByYears(-1);
		} catch (SolarSystemException | SolarSystemServiceException e) {
			assertNotEquals(e, null);
		}
	}
	
}
