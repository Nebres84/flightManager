package com.flightManager.flightManager.services;

import com.flightManager.flightManager.DB.Baggage;
import com.flightManager.flightManager.DB.Cargo;
import com.flightManager.flightManager.DB.DBMockup;
import com.flightManager.flightManager.DB.FlightEntity;
import com.flightManager.flightManager.dto.FlightDTO;
import com.flightManager.flightManager.utils.FlightManagerConstants;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightsService extends Services {

    public FlightsService(DBMockup dbMockup) {
        super(dbMockup);
    }

    public List<FlightDTO> getFlightDTOs(LocalDate date, Integer flightNumber) {

        List<FlightEntity> flightEntities = flightEntityList.stream()
                .filter(flight -> parseISO8061DateFromString(flight.getDepartureDate()).isEqual(date))
                .filter(flight -> flightNumber.equals(flight.getFlightNumber()))
                .collect(Collectors.toList());
        return buildFlightDTOsList(flightEntities);
    }

    private List<FlightDTO> buildFlightDTOsList(List<FlightEntity> flightEntities) {
      List<FlightDTO> flightDTOS = new ArrayList<>();
       for (FlightEntity flightEntity : flightEntities) {
           FlightDTO flightDTO = new FlightDTO();
           flightDTO.setFlightId(flightEntity.getFlightId());
           flightDTO.setFlightNumber(flightEntity.getFlightNumber());
           flightDTO.setBaggageWeight(countBaggageWeight(flightEntity.getFlightId()));
           flightDTO.setCargoWeight(countCargoWeight(flightEntity.getFlightId()));
           flightDTO.setTotalWeight(countTotalWeight(flightDTO));
           flightDTOS.add(flightDTO);
       }
       return flightDTOS;
    }

    private Pair<BigDecimal, BigDecimal> countTotalWeight(FlightDTO flightDTO) {
        BigDecimal totalKgWeight = flightDTO.getCargoWeight().getLeft().add(flightDTO.getBaggageWeight().getLeft());
        BigDecimal totalLbWeight = flightDTO.getCargoWeight().getRight().add(flightDTO.getBaggageWeight().getRight());
        return Pair.of(totalKgWeight, totalLbWeight);
    }

    private Pair<BigDecimal, BigDecimal> countBaggageWeight(Long flightId) {

        List<Baggage> baggageList = cargoEntityList.stream()
                .filter(cargoEntity -> flightId.equals(cargoEntity.getFlightId()))
                .flatMap(cargoEntity -> cargoEntity.getBaggageList().stream())
                .collect(Collectors.toList());
        BigDecimal kgWeight = BigDecimal.ZERO;
        BigDecimal lbWeight = BigDecimal.ZERO;
        for (Baggage baggage : baggageList) {
            if(FlightManagerConstants.KG.equalsIgnoreCase(baggage.getWeightUnit())) {
                kgWeight = kgWeight.add(baggage.getWeight());
                lbWeight = lbWeight.add(convertWeight(baggage.getWeight(), true));
            } else {
                lbWeight = lbWeight.add(baggage.getWeight());
                kgWeight = kgWeight.add(convertWeight(baggage.getWeight(), false));
            }
        }

    return Pair.of(kgWeight, lbWeight);
    }

    private Pair<BigDecimal, BigDecimal> countCargoWeight(Long flightId) {

        List<Cargo> cargoList = cargoEntityList.stream()
                .filter(cargoEntity -> flightId.equals(cargoEntity.getFlightId()))
                .flatMap(cargoEntity -> cargoEntity.getCargoList().stream())
                .collect(Collectors.toList());
        BigDecimal kgWeight = BigDecimal.ZERO;
        BigDecimal lbWeight = BigDecimal.ZERO;
        for (Cargo cargo : cargoList) {
            if(FlightManagerConstants.KG.equalsIgnoreCase(cargo.getWeightUnit())) {
                kgWeight = kgWeight.add(cargo.getWeight());
                lbWeight = lbWeight.add(convertWeight(cargo.getWeight(), true));
            } else {
                lbWeight = lbWeight.add(cargo.getWeight());
                kgWeight = kgWeight.add(convertWeight(cargo.getWeight(), false));
            }
        }

        return Pair.of(kgWeight, lbWeight);
    }

}
