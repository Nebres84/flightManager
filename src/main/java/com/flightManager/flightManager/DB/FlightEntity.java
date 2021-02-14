package com.flightManager.flightManager.DB;

import java.util.Objects;

public class FlightEntity {

    Long flightId;
    Integer flightNumber;
    String departureAirportIATACode;
    String arrivalAirportIATACode;
    String departureDate;

    public Long getFlightId() {
        return flightId;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureAirportIATACode() {
        return departureAirportIATACode;
    }

    public void setDepartureAirportIATACode(String departureAirportIATACode) {
        this.departureAirportIATACode = departureAirportIATACode;
    }

    public String getArrivalAirportIATACode() {
        return arrivalAirportIATACode;
    }

    public void setArrivalAirportIATACode(String arrivalAirportIATACode) {
        this.arrivalAirportIATACode = arrivalAirportIATACode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightEntity that = (FlightEntity) o;
        return Objects.equals(getFlightId(), that.getFlightId()) &&
                Objects.equals(getFlightNumber(), that.getFlightNumber()) &&
                Objects.equals(getDepartureAirportIATACode(), that.getDepartureAirportIATACode()) &&
                Objects.equals(getArrivalAirportIATACode(), that.getArrivalAirportIATACode()) &&
                Objects.equals(getDepartureDate(), that.getDepartureDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlightId(), getFlightNumber(), getDepartureAirportIATACode(), getArrivalAirportIATACode(), getDepartureDate());
    }

}
