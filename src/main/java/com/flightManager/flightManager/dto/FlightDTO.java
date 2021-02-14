package com.flightManager.flightManager.dto;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.Objects;

public class FlightDTO {

    Long flightId;
    Integer flightNumber;
    Pair<BigDecimal, BigDecimal> cargoWeight;
    Pair<BigDecimal, BigDecimal> baggageWeight;
    Pair<BigDecimal, BigDecimal> totalWeight;


    public Pair<BigDecimal, BigDecimal> getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(Pair<BigDecimal, BigDecimal> cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public Pair<BigDecimal, BigDecimal> getBaggageWeight() {
        return baggageWeight;
    }

    public void setBaggageWeight(Pair<BigDecimal, BigDecimal> baggageWeight) {
        this.baggageWeight = baggageWeight;
    }

    public Pair<BigDecimal, BigDecimal> getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Pair<BigDecimal, BigDecimal> totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDTO flightDTO = (FlightDTO) o;
        return Objects.equals(getFlightId(), flightDTO.getFlightId()) &&
                Objects.equals(getFlightNumber(), flightDTO.getFlightNumber()) &&
                Objects.equals(getCargoWeight(), flightDTO.getCargoWeight()) &&
                Objects.equals(getBaggageWeight(), flightDTO.getBaggageWeight()) &&
                Objects.equals(getTotalWeight(), flightDTO.getTotalWeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlightId(), getFlightNumber(), getCargoWeight(), getBaggageWeight(), getTotalWeight());
    }

}
