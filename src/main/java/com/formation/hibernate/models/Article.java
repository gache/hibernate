package com.formation.hibernate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // c'est la bdd qui s'occupe l'autoincrementation
    private Long ref;

    @Column(length = 100)
    private String nom;

    @Column(columnDefinition = "text")
    private String descrition;

    private Double prix;

}
