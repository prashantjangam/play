package com.teamfive.play.converter;

import com.teamfive.play.domain.Event;
import com.teamfive.play.domain.Team;
import com.teamfive.play.model.EventVO;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventConverter extends AuditConverter<Event> implements Serializable {

    /**
     * .
     */
    public EventVO transformToVO(Event domain) {
        if (null == domain) {
            return null;
        }
        EventVO vo = new EventVO();
        vo.setId(domain.getId());
        vo.setDeleted(domain.getDeleted());
        vo.setActive(domain.getActive());
        vo.setName(domain.getHomeTeam().getName() + "&" + domain.getAwayTeam().getName());
        vo.setAwayTeamName(domain.getAwayTeam().getName());
        vo.setAwayTeamScore(domain.getAwayTeamScore());
        vo.setHomeTeamName(domain.getHomeTeam().getName());
        vo.setHomeTeamScore(domain.getHomeTeamScore());
        vo.setHomeTeamId(domain.getHomeTeam().getId());
        vo.setAwayTeamId(domain.getAwayTeam().getId());
        vo.setLocation(domain.getLocation());
        vo.setStartTime(domain.getStartTime());
        vo.setStatus(domain.getStatus());
        vo.setCreatedAt(domain.getCreatedAt());
        vo.setCreatedBy(domain.getCreatedBy());
        vo.setUpdatedAt(domain.getUpdatedAt());
        vo.setUpdatedBy(domain.getUpdatedBy());
        return vo;
    }

    /**
     * .
     */
    public Event transformToEvent(EventVO vo) {
        if (null == vo) {
            return null;
        }
        Event domain = new Event();
        domain.setId(vo.getId());
        domain.setDeleted(vo.getDeleted());
        domain.setActive(vo.getActive());
        domain.setName(vo.getName());
        domain.setAwayTeamName(vo.getAwayTeamName());
        domain.setAwayTeamScore(vo.getAwayTeamScore());
        domain.setHomeTeamName(vo.getHomeTeamName());
        domain.setHomeTeamScore(vo.getHomeTeamScore());
        domain.setAwayTeam(new Team(vo.getAwayTeamId()));
        domain.setHomeTeam(new Team(vo.getHomeTeamId()));
        domain.setLocation(vo.getLocation());
        domain.setStartTime(vo.getStartTime());
        domain.setStatus(vo.getStatus());
        domain.setCreatedAt(vo.getCreatedAt());
        domain.setCreatedBy(vo.getCreatedBy());
        domain.setUpdatedAt(vo.getUpdatedAt());
        domain.setUpdatedBy(vo.getUpdatedBy());
        return domain;
    }

    /**
     * .
     */
    public List<EventVO> transformToVoList(List<Event> list) {
        return list.stream().map(this::transformToVO).collect(Collectors.toList());
    }
}
