package com.teamfive.play.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties
public class EventVO extends BaseVO implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
    
    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("start_time")
    private Date startTime;

    @JsonProperty("location")
    private String location;

    @JsonProperty("home_team_name")
    private String homeTeamName;

    @JsonProperty("away_team_name")
    private String awayTeamName;

    @JsonProperty("home_team_id")
    private Long homeTeamId;

    @JsonProperty("away_team_id")
    private Long awayTeamId;

    @JsonProperty("home_team_score")
    private Long homeTeamScore;

    @JsonProperty("away_team_score")
    private Long awayTeamScore;

    @JsonProperty("status")
    private String status;

}
