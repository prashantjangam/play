package com.teamfive.play.domain;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class Base implements Serializable {

    @Column(name = "deleted")
    private Boolean deleted;

    @Basic
    @Column(name = "createdBy")
    private Long createdBy;

    @Basic
    @Column(name = "createdAt")
    private Date createdAt;

    @Basic
    @Column(name = "updatedBy")
    private Long updatedBy;

    @Basic
    @Column(name = "updatedAt")
    private Date updatedAt;
}
