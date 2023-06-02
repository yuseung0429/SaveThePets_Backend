package com.savethepets.id;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class BookmarkId implements Serializable {
    String userId;
    Long postId;
}