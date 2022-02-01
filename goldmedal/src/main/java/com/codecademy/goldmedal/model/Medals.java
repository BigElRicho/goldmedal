package com.codecademy.goldmedal.model;

import javax.persistence.*;

@Entity
@Table
public class Medals {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    private Iterable<GoldMedal> medals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medals(Iterable<GoldMedal> medals) {
        this.medals = medals;
    }

    public Iterable<GoldMedal> getMedals() {
        return medals;
    }

    public void setMedals(Iterable<GoldMedal> medals) {
        this.medals = medals;
    }

    public Medals() {
    }
}
