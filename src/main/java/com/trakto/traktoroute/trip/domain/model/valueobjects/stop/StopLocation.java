package com.trakto.traktoroute.trip.domain.model.valueobjects.stop;

import java.math.BigDecimal;

public record StopLocation(BigDecimal latitude,
                           BigDecimal longitude){

    private static final BigDecimal MIN_LATITUDE = BigDecimal.valueOf(-90);
    private static final BigDecimal MAX_LATITUDE = BigDecimal.valueOf(90);

    private static final BigDecimal MIN_LONGITUDE = BigDecimal.valueOf(-180);
    private static final BigDecimal MAX_LONGITUDE = BigDecimal.valueOf(180);


    public StopLocation {
        if(latitude == null || latitude.compareTo(MIN_LATITUDE) < 0 || latitude.compareTo(MAX_LATITUDE) > 0){
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        if(longitude == null || longitude.compareTo(MIN_LONGITUDE) < 0 || longitude.compareTo(MAX_LONGITUDE) > 0){
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }
    }

}
