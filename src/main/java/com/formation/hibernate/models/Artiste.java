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
public class Artiste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identifiant;

    @Column(nullable = false, length = 200)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String email;

    @Column(length = 100)
    private String nationalite;

    @ManyToMany
    //  la table de jointure
    //  @JoinTable(name = "jointure_artiste_article", joinColumns = @JoinColumn(name = "artiste_id", nullable = true), inverseJoinColumns = @JoinColumn(name = "article_id", nullable = true))

    @ToString.Exclude private List<Article> articles = new ArrayList<>();


}
