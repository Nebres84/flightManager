package com.flightManager.flightManager.DB;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Load {

    Long id;
    BigDecimal weight;
    String weightUnit;
    Integer pieces;

    public Long getId() {
        return id;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Load load = (Load) o;
        return Objects.equals(getId(), load.getId()) &&
                Objects.equals(getWeight(), load.getWeight()) &&
                Objects.equals(getWeightUnit(), load.getWeightUnit()) &&
                Objects.equals(getPieces(), load.getPieces());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWeight(), getWeightUnit(), getPieces());
    }

}
