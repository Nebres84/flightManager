package com.flightManager.flightManager.DB;


import com.flightManager.flightManager.utils.FlightManagerConstants;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
This class representing DB. In normal situation this functionality will be handle by Hibernate framework and @Repository annotation  will be used.
**/

@Component
public class DBMockup {

    private static final List<FlightEntity> flightEntityList;
    private static final List<CargoEntity> cargoEntityList;

    static {
        flightEntityList = readFlightsFromJson();
        cargoEntityList = readCargosFromJson();
    }

    public List<FlightEntity> getFlightEntityList() {
        return flightEntityList;
    }


    public List<CargoEntity> getCargoEntityList() {
        return cargoEntityList;
    }


    private static List<FlightEntity> readFlightsFromJson() {

        List<FlightEntity> flightEntities = new ArrayList<>();
       try {
           Resource resource = new ClassPathResource(FlightManagerConstants.MOCKUP_DB_FLIGHT_SOURCE);
           JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(resource.getInputStream()));
           Type flightListType = new TypeToken<ArrayList<FlightEntity>>(){}.getType();
           flightEntities = new Gson().fromJson(jsonElement, flightListType);

       } catch (IOException e) {
           e.printStackTrace();
       }
       return flightEntities;
    }

   private static List<CargoEntity> readCargosFromJson() {

        List<CargoEntity> cargoEntityList = new ArrayList<>();
       try {
           Resource resource = new ClassPathResource(FlightManagerConstants.MOCKUP_DB_CARGO_SOURCE);
           JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(resource.getInputStream()));
           JsonArray jsonArray = jsonElement.getAsJsonArray();

           jsonArray.forEach( json -> {
               CargoEntity cargoEntity = new Gson().fromJson(json, CargoEntity.class);
               JsonObject object = json.getAsJsonObject();
               JsonElement jsonInnerBaggageElement = object.get(FlightManagerConstants.BAGGAGE_NODE);
               Type baggageListType = new TypeToken<ArrayList<Baggage>>(){}.getType();
               List<Baggage> baggageList = new Gson().fromJson(jsonInnerBaggageElement, baggageListType);
               cargoEntity.setBaggageList(baggageList);
               JsonElement jsonInnerCargoElement = object.get(FlightManagerConstants.CARGO_NODE);
               Type cargoListType = new TypeToken<ArrayList<Cargo>>(){}.getType();
               List<Cargo> cargoList = new Gson().fromJson(jsonInnerCargoElement, cargoListType);
               cargoEntity.setCargoList(cargoList);
               cargoEntityList.add(cargoEntity);
           });
           return cargoEntityList;

       } catch (IOException e) {
           e.printStackTrace();
       }
       return cargoEntityList;
    }

}
