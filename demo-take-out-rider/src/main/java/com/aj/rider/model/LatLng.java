/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.rider.model;

import lombok.Data;

/**
 * LatLng
 *
 * @author An Jun
 * @date 2021-06-21
 */
@Data
public class LatLng {

    private Double lat;

    private Double lng;

    private String name;

    private boolean isValid;

    public LatLng(Double lat, Double lng, String name) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.isValid = lat != null && lng != null && name != null;
    }
}
