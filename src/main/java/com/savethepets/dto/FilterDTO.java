package com.savethepets.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class FilterDTO {
    int species;
    int breed;
    int[] type;
    Double userLat;
    Double userLot;
    int range;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
