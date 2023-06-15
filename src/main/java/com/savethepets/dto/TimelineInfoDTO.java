package com.savethepets.dto;

import com.savethepets.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TimelineInfoDTO {
    Long sightingPostId;
    LocalDateTime time;
    Double postLat;
    Double postLot;
    String address;
    int species;
    int breed;
    String picture;

    public TimelineInfoDTO(Post sightingpost,String picture) {
        this.sightingPostId = sightingpost.getPostId();
        this.time = sightingpost.getTime();
        this.postLat = sightingpost.getLat();
        this.postLot = sightingpost.getLot();
        this.address = sightingpost.getAddress();
        this.species = sightingpost.getSpecies();
        this.breed = sightingpost.getBreed();
        this.picture = picture;
    }
}
