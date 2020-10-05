package com.formation.hibernate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private Integer largeur;

    private Integer longeur;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false)
    private String lien;

    // liens avec du java avec les association
    @ManyToOne
    @JoinColumn(nullable = false) // il faut minimun une image
    private Article article;

}
