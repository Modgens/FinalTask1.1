package com.example.tt.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "visitor_groups")
@ToString
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateIn;
    private Date dateOut;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Visitant mainVisitant;

    @ManyToMany
    private List<Visitant> visitants;
}
