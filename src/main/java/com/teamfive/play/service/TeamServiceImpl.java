/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamfive.play.service;


import com.teamfive.play.converter.TeamConverter;
import com.teamfive.play.domain.Team;
import com.teamfive.play.model.TeamVO;
import com.teamfive.play.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository categoryRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    private TeamConverter categoryConverter;

    @Override
    public TeamVO findById(Long id) {
        Optional<Team> slot = categoryRepository.findById(id);
        if (slot.isPresent()) {
            return categoryConverter.transformToVO(slot.get());
        }
        return null;
    }

    public TeamVO save(TeamVO slot) {
        return categoryConverter.transformToVO(categoryRepository.save(categoryConverter.transformToCategory(slot)));
    }

    public List<TeamVO> findAll(Boolean active) {
        return categoryConverter.transformToVoList(categoryRepository.findByActive(active));
    }

    public List<TeamVO> findAllPagedUser(boolean active, Integer pageSize, Integer pageNo, String sanitizedSortBy) {
        Page<Team> all = null;
        Pageable paging = null;
        if (!StringUtils.hasText(sanitizedSortBy)) {
            paging = PageRequest.of(--pageNo, pageSize, Sort.by(sanitizedSortBy));
            all = categoryRepository.findAll(paging);
        } else {
            paging = PageRequest.of(--pageNo, pageSize);
            all = categoryRepository.findAll(paging);
        }
        return categoryConverter.transformToVoList(all.getContent());
    }

    public List<TeamVO> findAllSearchUser(boolean active, Integer pageSize, Integer pageNo, String sanitizedSortBy) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Team> searchQuery = criteriaBuilder.createQuery(Team.class);
        Root<Team> root = searchQuery.from(Team.class);
        final List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(criteriaBuilder.equal(root.get("active"), active));
        searchQuery.select(root);
        searchQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
        TypedQuery<Team> typedQuery = em.createQuery(searchQuery);
        typedQuery.setFirstResult(--pageNo * pageSize);
        typedQuery.setMaxResults(pageSize);
        return categoryConverter.transformToVoList(typedQuery.getResultList());
    }

    @Override
    public Boolean remove(Long id) {
        categoryRepository.delete(categoryRepository.findById(id).get());
        return true;
    }


}
