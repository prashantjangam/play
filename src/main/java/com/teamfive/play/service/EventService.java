/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamfive.play.service;


import com.teamfive.play.model.EventVO;

import java.util.List;

public interface EventService {

    public EventVO findById(Long id);

    public EventVO save(EventVO event);

    public List<EventVO> findAll(Boolean active);

    public List<String> findAllWinners();

    public List<EventVO> findByAllMatchesOfTeamId(Long id);

    public List<EventVO> findAllPagedUser(boolean active, Integer pageSize, Integer pageNo, String sanitizedSortBy);

    public List<EventVO> findAllSearchUser(boolean active, Integer pageSize, Integer pageNo, String sanitizedSortBy);

    public Boolean remove(Long id);

}
