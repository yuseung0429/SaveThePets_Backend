package com.savethepets.service;

import com.savethepets.entity.Timeline;

public interface TimelineService {
    boolean createTimeline(Timeline timeline);
    boolean removeTimeline(Long missingpostId, Long sightingpostId);

}
