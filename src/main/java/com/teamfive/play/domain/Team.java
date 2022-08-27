package com.teamfive.play.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name="team")
public class Team extends Base implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private String banner;

    @Column(nullable = true)
    private String logo;

    @Column(name = "active")
    private Boolean active;

    public Team() {
    }
    public Team(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team event = (Team) o;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name) && Objects.equals(description, event.description) && Objects.equals(banner, event.banner) && Objects.equals(logo, event.logo) && Objects.equals(active, event.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, banner, logo, active);
    }
}
