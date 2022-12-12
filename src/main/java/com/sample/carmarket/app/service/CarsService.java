package com.sample.carmarket.app.service;

import com.sample.carmarket.entity.Car;
import com.sample.carmarket.entity.CarStatus;
import com.sample.carmarket.entity.EngineType;
import io.jmix.core.DataManager;
import io.jmix.core.entity.KeyValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarsService {

    @Autowired
    private DataManager dataManager;

    public Map<EngineType, Long> calculateCars(UUID manufacturerId) {
        List<KeyValueEntity> values = dataManager.loadValues("" +
                        "select c.model.engineType, count(c.model.engineType) " +
                        "from Car c where c.model.manufacturer.id = :manufacturerId " +
                        "group by c.model.engineType")
                .parameter("manufacturerId", manufacturerId)
                .properties("engineType", "count")
                .list();

        return values.stream()
                .collect(Collectors.toMap(
                        kv -> EngineType.fromId(kv.getValue("engineType")),
                        kv -> kv.<Long>getValue("count")
                ));
    }

    public void markAsSold(Car car) {
        car.setStatus(CarStatus.SOLD);
        car.setDate_of_sale(LocalDate.now());

        dataManager.save(car);
    }
}