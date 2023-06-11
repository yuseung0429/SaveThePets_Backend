package com.savethepets.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DistancePostDTO {
    Double userLat;
    Double userLot;
    Double rightUpLat;
    Double rightUpLot;
    Double leftDownlat;
    Double leftDownlot;
}
