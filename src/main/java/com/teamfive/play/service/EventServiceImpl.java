/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamfive.play.service;


import com.teamfive.play.converter.EventConverter;
import com.teamfive.play.domain.Event;
import com.teamfive.play.model.EventVO;
import com.teamfive.play.repository.EventRepository;
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
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    private EventConverter eventConverter;

    @Override
    public EventVO findById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return eventConverter.transformToVO(event.get());
        }
        return null;
    }

    public EventVO save(EventVO event) {
        return eventConverter.transformToVO(eventRepository.save(eventConverter.transformToEvent(event)));
    }

    public List<EventVO> findAll(Boolean active) {
        return eventConverter.transformToVoList(eventRepository.findByActive(active));
    }

    public List<EventVO> findByAllMatchesOfTeamId(Long teamId) {
        return eventConverter.transformToVoList(eventRepository.findByHomeTeamOrAwayTeam(teamId));
    }


    public List<String> findAllWinners() {
        List<String> names = new ArrayList<String>();

        List<String> list = eventRepository.findAllHomeWinners();
        if (null != list) {
            names.addAll(list);
        }
        list = eventRepository.findAllAwayWinners();
        if (null != list) {
            names.addAll(list);
        }
        return names;
    }

    public List<EventVO> findAllPagedUser(boolean active, Integer pageSize, Integer pageNo, String sanitizedSortBy) {
        Page<Event> all = null;
        Pageable paging = null;
        if (!StringUtils.hasText(sanitizedSortBy)) {
            paging = PageRequest.of(--pageNo, pageSize, Sort.by(sanitizedSortBy));
            all = eventRepository.findAll(paging);
        } else {
            paging = PageRequest.of(--pageNo, pageSize);
            all = eventRepository.findAll(paging);
        }
        return eventConverter.transformToVoList(all.getContent());
    }

    public List<EventVO> findAllSearchUser(boolean active, Integer pageSize, Integer pageNo, String sanitizedSortBy) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Event> searchQuery = criteriaBuilder.createQuery(Event.class);
        Root<Event> root = searchQuery.from(Event.class);
        final List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(criteriaBuilder.equal(root.get("active"), active));
        searchQuery.select(root);
        searchQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
        TypedQuery<Event> typedQuery = em.createQuery(searchQuery);
        typedQuery.setFirstResult(--pageNo * pageSize);
        typedQuery.setMaxResults(pageSize);
        return eventConverter.transformToVoList(typedQuery.getResultList());
    }

    @Override
    public Boolean remove(Long id) {
        eventRepository.delete(eventRepository.findById(id).get());
        return true;
    }


}
