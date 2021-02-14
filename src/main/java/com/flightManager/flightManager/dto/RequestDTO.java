package com.flightManager.flightManager.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class RequestDTO {

    String requestedDate;
    BigDecimal flightNumber;
    String airportCode;
    boolean arrivalRequest;

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public BigDecimal getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(BigDecimal flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public boolean getArrivalRequest() {
        return arrivalRequest;
    }

    public void setArrivalRequest(boolean arrivalRequest) {
        this.arrivalRequest = arrivalRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestDTO that = (RequestDTO) o;
        return Objects.equals(requestedDate, that.requestedDate) &&
                Objects.equals(flightNumber, that.flightNumber) &&
                Objects.equals(airportCode, that.airportCode) &&
                Objects.equals(arrivalRequest, that.arrivalRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestedDate, flightNumber, airportCode, arrivalRequest);
    }

}
