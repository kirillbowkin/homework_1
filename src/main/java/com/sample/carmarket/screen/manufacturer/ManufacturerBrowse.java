package com.sample.carmarket.screen.manufacturer;

import com.sample.carmarket.app.service.CarsService;
import com.sample.carmarket.entity.EngineType;
import com.sample.carmarket.entity.Manufacturer;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;

@UiController("Manufacturer.browse")
@UiDescriptor("manufacturer-browse.xml")
@LookupComponent("table")
public class ManufacturerBrowse extends MasterDetailScreen<Manufacturer> {
    @Autowired
    private Notifications notifications;
    @Autowired
    private Table<Manufacturer> table;
    @Autowired
    private CarsService carsService;

    @Subscribe("table.calculateCars")
    public void onTableCalculateCars(Action.ActionPerformedEvent event) {
        Manufacturer selectedManufacturer = Objects.requireNonNull(table.getSingleSelected());

        Map<EngineType, Long> engineTypesCount = carsService.calculateCars(selectedManufacturer.getId());

        notifications.create()
                .withCaption(
                        "Electric cars = " + engineTypesCount.getOrDefault(EngineType.ELECTRIC, 0L) +
                        ", gasoline cars = " + engineTypesCount.getOrDefault(EngineType.GASOLINE, 0L)
                )
                .withType(Notifications.NotificationType.TRAY)
                .show();
    }

}