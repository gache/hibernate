package com.formation.hibernate;

import com.formation.hibernate.configs.HibernateUtils;
import com.formation.hibernate.models.Article;
import com.formation.hibernate.models.Artiste;
import com.formation.hibernate.models.Image;
import com.formation.hibernate.services.ArtisteService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collections;


public class Driver {
    public static void main(String[] args) {
        Session session = HibernateUtils.getSession();

        Article article1 = new Article();
        article1.setNom("Nike air max");
        article1.setDescrition("Fais lui porter le Swoosh avec ces baskets Air Max 2090 pour enfants.");
        article1.setPrix(90.0);

        Artiste artiste = new Artiste(null,
                "Erick",
                "Franco",
                "erick@fran.fr",
                "Française",
                new ArrayList<>(Collections.singletonList(article1))
        );
        Image image = new Image(null,
                1920,
                1080,
                "paysage",
                "src/images/1.png",
                article1);

        Transaction ts = session.beginTransaction();
//
//		session.save(article1);
//
//		session.save(image);
//		session.save(artiste);
//
//		ts.commit(); // l'envoie la bdd
//		session.close();

//		ImageService imageService = new ImageService();
//		System.out.println(imageService.findAll());

//		ArticleService articleService = new ArticleService();
//		System.out.println(artisteService.findAll());
//		System.out.println(articleService.findByID(1L));
//		articleService.save(article1);
//
//		article1.setRef(1L);
//		articleService.delete(article1);


        ArtisteService artisteService = new ArtisteService();
        System.out.println(artisteService.findAll());
        System.out.println(artisteService.findById(1L));
        artisteService.save(artiste);




    }
}
