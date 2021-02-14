package com.flightManager.flightManager.controlers;

import com.flightManager.flightManager.dto.AirportDTO;
import com.flightManager.flightManager.dto.FlightDTO;
import com.flightManager.flightManager.dto.RequestDTO;
import com.flightManager.flightManager.services.AirportService;
import com.flightManager.flightManager.services.FlightsService;
import com.flightManager.flightManager.utils.FlightManagerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FlightManagerController implements FlightManagerConstants {

    private final AirportService airportService;
    private final FlightsService flightsService;

    @Autowired
    public FlightManagerController(AirportService airportService, FlightsService flightsService) {
        this.airportService = airportService;
        this.flightsService = flightsService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute(REQUEST, new RequestDTO());
        model.addAttribute(FLIGHT_RESULTS, false);
        model.addAttribute(AIRPORT_RESULTS, false);
        return INDEX;
    }

    @PostMapping("/index")
    public String showSearchResults(Model model, @ModelAttribute(REQUEST) RequestDTO requestDTO) {

        if(requestDTO.getFlightNumber() != null) {
            return doBusinessLogicForFlightSearch(model, requestDTO);
        } else {
            return doBusinessLogicForAirportSearch(model, requestDTO);
        }
    }

    private String doBusinessLogicForAirportSearch(Model model, RequestDTO requestDTO) {
        Long numberOfFlights = airportService.countFlights(requestDTO.getAirportCode(),
                flightsService.parseDateFromRequest(requestDTO.getRequestedDate()),
                requestDTO.getArrivalRequest());

        Long sumOfAllBaggagePieces = airportService.countTotalBaggagePiecesAccordingToAirportCode(requestDTO.getAirportCode(),
                flightsService.parseDateFromRequest(requestDTO.getRequestedDate()),
                requestDTO.getArrivalRequest());
        if(numberOfFlights == 0L) {
            return cleanUpIndexPage(model);
        }
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setNumberOfFlights(numberOfFlights);
        airportDTO.setSumOfAllBaggagePieces(sumOfAllBaggagePieces);
        airportDTO.setAirportCode(requestDTO.getAirportCode());
        airportDTO.setArrivalRequest(requestDTO.getArrivalRequest());
        model.addAttribute(AIRPORT_DTO, airportDTO);
        model.addAttribute(AIRPORT_RESULTS, true);
        model.addAttribute(FLIGHT_RESULTS, false);

        return INDEX;
    }

    private String doBusinessLogicForFlightSearch(Model model, RequestDTO requestDTO) {
        List<FlightDTO> flightDTOs = flightsService.getFlightDTOs(
                flightsService.parseDateFromRequest(requestDTO.getRequestedDate()),
                requestDTO.getFlightNumber().intValue());

        if(flightDTOs.isEmpty()) {
            return cleanUpIndexPage(model);
        }

        model.addAttribute(FLIGHTS_DTOS, flightDTOs);
        model.addAttribute(NO_RESULT, false);
        model.addAttribute(AIRPORT_RESULTS, false);
        model.addAttribute(FLIGHT_RESULTS, true);

        return INDEX;
    }

    private String cleanUpIndexPage(Model model) {
        model.addAttribute(NO_RESULT, true);
        model.addAttribute(AIRPORT_RESULTS, false);
        model.addAttribute(FLIGHT_RESULTS, false);
        return index(model);
    }

}
