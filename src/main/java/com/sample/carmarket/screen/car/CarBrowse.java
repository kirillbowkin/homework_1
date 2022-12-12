package com.sample.carmarket.screen.car;

import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Car;

@UiController("Car.browse")
@UiDescriptor("car-browse.xml")
@LookupComponent("carsTable")
public class CarBrowse extends StandardLookup<Car> {
}