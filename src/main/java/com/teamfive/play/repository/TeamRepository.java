package com.teamfive.play.repository;


import com.teamfive.play.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    public  List<Team> findByDeleted(Boolean deleted);
    public  List<Team> findByActive(Boolean active);


}
