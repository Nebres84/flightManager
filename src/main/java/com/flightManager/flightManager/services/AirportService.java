package com.flightManager.flightManager.services;

import com.flightManager.flightManager.DB.DBMockup;
import com.flightManager.flightManager.DB.FlightEntity;
import com.flightManager.flightManager.DB.Load;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService extends Services {

    public AirportService(DBMockup dbMockup) {
        super(dbMockup);
    }

    public Long countFlights(String airportCode, LocalDate date,  boolean isArrivingQuery) {
        return flightEntityList.stream()
                .filter(flight -> parseISO8061DateFromString(flight.getDepartureDate()).isEqual(date))
                .filter(flight -> airportCode.equalsIgnoreCase(isArrivingQuery ? flight.getArrivalAirportIATACode() : flight.getDepartureAirportIATACode()))
                .count();
    }

    public List<Long> getFlightsIdListAccordingToAirportCode(String airportCode, LocalDate date, boolean isArrivingRequest) {
        return flightEntityList.stream()
                .filter(flight -> parseISO8061DateFromString(flight.getDepartureDate()).isEqual(date))
                .filter(flight -> airportCode.equalsIgnoreCase(isArrivingRequest ? flight.getArrivalAirportIATACode() : flight.getDepartureAirportIATACode()))
                .map(FlightEntity::getFlightId)
                .collect(Collectors.toList());
    }

    public long countTotalBaggagePiecesAccordingToAirportCode(String airportCode, LocalDate date, boolean isArrivingRequest) {
        List<Long> flightsIds = getFlightsIdListAccordingToAirportCode(airportCode, date, isArrivingRequest);
        long result = 0L;
        for (Long id : flightsIds) {
            result += cargoEntityList.stream()
                    .filter(cargoEntity -> id.equals(cargoEntity.getFlightId()))
                    .flatMap(cargoEntity -> cargoEntity.getBaggageList().stream())
                    .map(Load::getPieces)
                    .mapToLong(Integer::longValue)
                    .sum();
        }
        return result;
    }

}
