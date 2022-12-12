package com.sample.carmarket.screen.car;

import com.sample.carmarket.app.service.CarsService;
import com.sample.carmarket.entity.CarStatus;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@UiController("Car.browse")
@UiDescriptor("car-browse.xml")
@LookupComponent("carsTable")
public class CarBrowse extends StandardLookup<Car> {
    @Autowired
    private GroupTable<Car> carsTable;
    @Autowired
    private Notifications notifications;
    @Autowired
    private CarsService carsService;

    @Subscribe("carsTable.markAsSold")
    public void onCarsTableMarkAsSold(Action.ActionPerformedEvent event) {
        Car car = Objects.requireNonNull(carsTable.getSingleSelected());

        if(Objects.isNull(car.getStatus())) {
            notifications.create()
                    .withCaption("Not in stock")
                    .withType(Notifications.NotificationType.ERROR)
                    .show();
            return;
        }

        if(car.getStatus().equals(CarStatus.SOLD)) {
            notifications.create()
                    .withCaption("Already sold")
                    .withType(Notifications.NotificationType.ERROR)
                    .show();
            return;
        }


        carsService.markAsSold(car);
        notifications.create()
                .withCaption("Done")
                .withType(Notifications.NotificationType.TRAY)
                .show();


    }
}