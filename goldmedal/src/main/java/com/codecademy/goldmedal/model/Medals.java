package com.codecademy.goldmedal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table
public class Medals {
    private Iterable<GoldMedal> medals;

    public Medals(Iterable<GoldMedal> medals) {
        this.medals = medals;
    }

    public Iterable<GoldMedal> getMedals() {
        return medals;
    }

    public void setMedals(Iterable<GoldMedal> medals) {
        this.medals = medals;
    }
}
