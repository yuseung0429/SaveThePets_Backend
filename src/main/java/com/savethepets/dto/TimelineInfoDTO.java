package com.savethepets.dto;

import com.savethepets.entity.Post;
import com.savethepets.id.PostPictureId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TimelineInfoDTO {
    Long sightingPostId;
    LocalDateTime time;
    Double postLat;
    Double postLot;
    int species;
    int breed;
    byte[] picture;

    public TimelineInfoDTO(Post sightingpost,byte[] picture ) {
        this.sightingPostId = sightingpost.getPostId();
        this.time = sightingpost.getTime();
        this.postLat = sightingpost.getLat();
        this.postLot = sightingpost.getLot();
        this.species = sightingpost.getSpecies();
        this.breed = sightingpost.getBreed();
        this.picture = picture;
    }
}
