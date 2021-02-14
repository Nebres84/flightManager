package com.flightManager.flightManager;

import com.flightManager.flightManager.DB.CargoEntity;
import com.flightManager.flightManager.DB.DBMockup;
import com.flightManager.flightManager.DB.FlightEntity;
import com.flightManager.flightManager.dto.FlightDTO;
import com.flightManager.flightManager.services.AirportService;
import com.flightManager.flightManager.services.FlightsService;
import com.google.gson.Gson;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@SpringBootTest
class FlightManagerApplicationTests {

	@Test
	void contextLoads() {
	}

}
