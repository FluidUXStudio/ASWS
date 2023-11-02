package com.ahlesunnat.asws.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @ManyToOne(fetch = FetchType.EAGER)
    private Zone zone;

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Volunteer(Long id, Center center, Zone zone) {
        this.id = id;
        this.center = center;
        this.zone = zone;
    }

    // Add getters and setters for the attributes and relationships

    public Zone getZone() {
        return zone;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters and setters
    // Getters and setters
}