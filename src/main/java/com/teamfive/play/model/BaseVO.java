package com.teamfive.play.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@JsonIgnoreProperties
@MappedSuperclass
public abstract class BaseVO {

    @JsonProperty("deleted")
    public Boolean deleted;
    @JsonProperty("created_by")
    private Long createdBy;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("updated_by")
    private Long updatedBy;
    @JsonProperty("updated_at")
    private Date updatedAt;
}
