package com.trakto.traktoroute.trip.domain.model.valueobjects;

import java.math.BigDecimal;

public record TripLocation (String address,
                            BigDecimal latitude,
                            BigDecimal longitude){

    private static final BigDecimal MIN_LATITUDE = BigDecimal.valueOf(-90);
    private static final BigDecimal MAX_LATITUDE = BigDecimal.valueOf(90);

    private static final BigDecimal MIN_LONGITUDE = BigDecimal.valueOf(-180);
    private static final BigDecimal MAX_LONGITUDE = BigDecimal.valueOf(180);


    public TripLocation{
        if(address == null || address.isBlank()){
            throw new IllegalArgumentException("Invalid trip location");
        }
        if(latitude == null || latitude.compareTo(MIN_LATITUDE) < 0 || latitude.compareTo(MAX_LATITUDE) > 0){
            throw new IllegalArgumentException("Invalid trip location");
        }
        if(longitude == null || longitude.compareTo(MIN_LONGITUDE) < 0 || longitude.compareTo(MAX_LONGITUDE) > 0){
            throw new IllegalArgumentException("Invalid trip location");
        }
        address = address.trim();
    }

}
