package com.savethepets.service;

import com.savethepets.entity.Timeline;
import com.savethepets.id.TimelineId;
import com.savethepets.repository.TimelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class TimelineServiceImpl implements TimelineService{

    private final TimelineRepository timelineRepository;

    @Override
    public boolean createTimeline(Timeline timeline) {
        // DB에 TimelineId에 해당하는 record가 없는 경우
        if(timelineRepository.findOne(timeline.getTimelineId())==null)
        {
            timelineRepository.save(timeline);
            return true;
        }
        // DB에 TimelineId에 해당하는 record가 있는 경우
        else
            return false;
    }

    @Override
    public boolean removeTimeline(TimelineId timelineId) {
        Timeline timeline;
        // DB에 TimelineId에 해당하는 record가 있는 경우
        if((timeline = timelineRepository.findOne(timelineId))!=null)
        {
            timelineRepository.remove(timeline);
            return true;
        }
        // DB에 TimelineId에 해당하는 record가 없는 경우
        else
            return false;
    }

}
