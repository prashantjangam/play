package com.teamfive.play.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="event")
public class Event extends Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "start_time",nullable = true)
    private Date startTime;

    @Column(name = "location")
    private String location;

    @Column(name = "home_team_name")
    private String homeTeamName;

    @ManyToOne
    @JoinColumn(name = "home_team_id",referencedColumnName = "id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id",referencedColumnName = "id")
    private Team awayTeam;

    @Column(name = "away_team_name")
    private String awayTeamName;

    @Column(name = "home_team_score")
    private Long homeTeamScore;

    @Column(name = "away_team_score")
    private Long awayTeamScore;

    @Column(name = "status")
    private String status;



}
