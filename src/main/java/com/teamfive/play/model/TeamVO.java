package com.teamfive.play.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties
public class TeamVO extends BaseVO implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("banner")
    private String banner;

    @JsonProperty("logo")
    private String logo;

    @JsonProperty("active")
    private Boolean active;

}
