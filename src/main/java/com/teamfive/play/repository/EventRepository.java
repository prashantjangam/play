package com.teamfive.play.repository;


import com.teamfive.play.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    public  List<Event> findByDeleted(Boolean deleted);
    public  List<Event> findByActive(Boolean active);

    @Query("SELECT e FROM Event e where e.homeTeam.id = ?1 or e.awayTeam.id = ?1 ")
    public  List<Event> findByHomeTeamOrAwayTeam(Long id);

    @Query("SELECT e.homeTeam.name FROM Event e where e.homeTeamScore > e.awayTeamScore ")
    public  List<String> findAllHomeWinners();

    @Query("SELECT e.awayTeam.name FROM Event e where e.homeTeamScore < e.awayTeamScore ")
    public  List<String> findAllAwayWinners();


}
