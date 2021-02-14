package com.flightManager.flightManager.services;

import com.flightManager.flightManager.DB.CargoEntity;
import com.flightManager.flightManager.DB.DBMockup;
import com.flightManager.flightManager.DB.FlightEntity;
import com.flightManager.flightManager.utils.FlightManagerConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.List;

public abstract class Services {

    final List<FlightEntity> flightEntityList;
    final List<CargoEntity> cargoEntityList;

    @Autowired
    public Services(DBMockup dbMockup) {
        this.flightEntityList = dbMockup.getFlightEntityList();
        this.cargoEntityList = dbMockup.getCargoEntityList();

    }

    public BigDecimal convertWeight(BigDecimal weight, boolean isToLbConversion) {
        if(isToLbConversion) {
            return weight.divide(new BigDecimal(FlightManagerConstants.LB_COVERT_VALUE), MathContext.DECIMAL32);
        } else {
            return weight.multiply(new BigDecimal(FlightManagerConstants.LB_COVERT_VALUE), MathContext.DECIMAL32);
        }
    }

    public LocalDate parseISO8061DateFromString(String date) {
        DateTimeFormatter parser = ISODateTimeFormat.dateTimeNoMillis();
        date = date.replaceAll("\\s+", "");
        org.joda.time.LocalDateTime localDateTime = parser.withZone(DateTimeZone.forID(FlightManagerConstants.UTC)).parseDateTime(date).toLocalDateTime();
        return LocalDate.of(localDateTime.getYear(), localDateTime.getMonthOfYear(), localDateTime.getDayOfMonth());
    }

    public LocalDate parseDateFromRequest(String date) {
        return LocalDate.parse(date);
    }

}
