package com.savethepets.dto;

import java.time.LocalDateTime;

public class FilterDTO {
    int species;
    int breed;
    int type;
    Double userLat;
    Double userLot;
    int range;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
