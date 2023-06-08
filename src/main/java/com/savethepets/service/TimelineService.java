package com.savethepets.service;

import com.savethepets.entity.Timeline;
import com.savethepets.id.TimelineId;

public interface TimelineService {
    boolean createTimeline(Timeline timeline);
    boolean removeTimeline(TimelineId timelineId);

}
