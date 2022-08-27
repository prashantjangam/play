package com.teamfive.play.converter;

import com.teamfive.play.domain.Team;
import com.teamfive.play.model.TeamVO;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamConverter extends AuditConverter<Team> implements Serializable {

    /**
     * .
     */
    public TeamVO transformToVO(Team domain) {
        if(null == domain){
            return null;
        }
        TeamVO vo = new TeamVO();
        vo.setId(domain.getId());
        vo.setDeleted(domain.getDeleted());
        vo.setActive(domain.getActive());
        vo.setDescription(domain.getDescription());
        vo.setName(domain.getName());
        vo.setBanner(domain.getBanner());
        vo.setLogo(domain.getLogo());
        vo.setCreatedAt(domain.getCreatedAt());
        vo.setCreatedBy(domain.getCreatedBy());
        vo.setUpdatedAt(domain.getUpdatedAt());
        vo.setUpdatedBy(domain.getUpdatedBy());
        return vo;
    }

    /**
     * .
     */
    public Team transformToCategory(TeamVO vo) {
        if(null == vo){
            return null;
        }
        Team domain = new Team();
        domain.setId(vo.getId());
        domain.setDeleted(vo.getDeleted());
        domain.setActive(vo.getActive());
        domain.setDescription(vo.getDescription());
        domain.setName(vo.getName());
        domain.setBanner(vo.getBanner());
        domain.setLogo(vo.getLogo());
        domain.setCreatedAt(vo.getCreatedAt());
        domain.setCreatedBy(vo.getCreatedBy());
        domain.setUpdatedAt(vo.getUpdatedAt());
        domain.setUpdatedBy(vo.getUpdatedBy());
        return domain;
    }

    /**
     * .
     */
    public List<TeamVO> transformToVoList(List<Team> list) {
        return list.stream().map(this::transformToVO).collect(Collectors.toList());
    }
}
