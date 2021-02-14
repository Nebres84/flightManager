package com.flightManager.flightManager.dto;

import java.util.Objects;

public class AirportDTO {

    Long numberOfFlights;
    Long sumOfAllBaggagePieces;
    Boolean arrivalRequest;
    String airportCode;

    public Long getNumberOfFlights() {
        return numberOfFlights;
    }

    public void setNumberOfFlights(Long numberOfFlights) {
        this.numberOfFlights = numberOfFlights;
    }

    public Long getSumOfAllBaggagePieces() {
        return sumOfAllBaggagePieces;
    }

    public void setSumOfAllBaggagePieces(Long sumOfAllBaggagePieces) {
        this.sumOfAllBaggagePieces = sumOfAllBaggagePieces;
    }

    public Boolean getArrivalRequest() {
        return arrivalRequest;
    }

    public void setArrivalRequest(Boolean arrivalRequest) {
        this.arrivalRequest = arrivalRequest;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirportDTO that = (AirportDTO) o;
        return Objects.equals(numberOfFlights, that.numberOfFlights) &&
                Objects.equals(sumOfAllBaggagePieces, that.sumOfAllBaggagePieces) &&
                Objects.equals(arrivalRequest, that.arrivalRequest) &&
                Objects.equals(airportCode, that.airportCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfFlights, sumOfAllBaggagePieces, arrivalRequest, airportCode);
    }

}
