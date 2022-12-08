package com.sample.carmarket.screen.manufacturer;

import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Manufacturer;

@UiController("Manufacturer.browse")
@UiDescriptor("manufacturer-browse.xml")
@LookupComponent("table")
public class ManufacturerBrowse extends MasterDetailScreen<Manufacturer> {
}