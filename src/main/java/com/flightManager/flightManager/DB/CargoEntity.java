package com.flightManager.flightManager.DB;

import java.util.List;
import java.util.Objects;

public class CargoEntity {

    Long flightId;
    List<Baggage>baggageList;
    List<Cargo> cargoList;

    public Long getFlightId() {
        return flightId;
    }

    public List<Baggage> getBaggageList() {
        return baggageList;
    }

    public void setBaggageList(List<Baggage> baggageList) {
        this.baggageList = baggageList;
    }

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoEntity that = (CargoEntity) o;
        return Objects.equals(getFlightId(), that.getFlightId()) &&
                Objects.equals(getBaggageList(), that.getBaggageList()) &&
                Objects.equals(getCargoList(), that.getCargoList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlightId(), getBaggageList(), getCargoList());
    }

}
