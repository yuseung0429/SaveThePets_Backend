package com.savethepets.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FilterDTO {
    int species;
    int breed;
    int[] type;
    Double userLat;
    Double userLot;
    int range;
    String startDate;
    String endDate;
    
    public FilterDTO()
    {
    	this.species = -1;
    	this.breed = -1;
    }
}
