/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamfive.play.service;

import com.teamfive.play.model.TeamVO;

import java.util.List;

public interface TeamService {

    public TeamVO findById(Long id);

    public TeamVO save(TeamVO category);

    public List<TeamVO> findAll(Boolean active);

    public List<TeamVO> findAllPagedUser(boolean active, Integer pageSize, Integer pageNo, String sanitizedSortBy);

    public List<TeamVO> findAllSearchUser(boolean active, Integer pageSize, Integer pageNo, String sanitizedSortBy);

    public Boolean remove(Long id);

}
