package com.formation.hibernate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    // liens en java les association et la connexion avec hibernate

    @OneToMany(mappedBy = "article") // je fais une prioritaire mappedBy est le soumis
    @ToString.Exclude private List<Image> images = new ArrayList<>();

    @ManyToMany(mappedBy = "articles")
    @ToString.Exclude private List<Artiste> artistes = new ArrayList<>();


}
