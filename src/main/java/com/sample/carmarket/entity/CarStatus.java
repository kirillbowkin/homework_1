package com.sample.carmarket.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum CarStatus implements EnumClass<Integer> {

    IN_STOCK(10),
    SOLD(20);

    private Integer id;

    CarStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static CarStatus fromId(Integer id) {
        for (CarStatus at : CarStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}