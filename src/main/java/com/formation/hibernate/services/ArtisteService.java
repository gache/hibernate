package com.formation.hibernate.services;

import com.formation.hibernate.configs.HibernateUtils;
import com.formation.hibernate.models.Artiste;

import com.formation.hibernate.repositories.ArtisteRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.RollbackException;
import java.util.Collection;
import java.util.Optional;

public class ArtisteService implements ArtisteRepository {
    private final Session session = HibernateUtils.getSession();

    @Override
    public Optional<Collection<Artiste>> findAll() {
        Collection<Artiste> artistes = null;
        org.hibernate.query.Query<Artiste> q = null;
        // on gère try catch
        try {
            q = session.createQuery("from Artiste", Artiste.class);
            // assert q.list().isEmpty();
            artistes = q.list();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(artistes);

    }

    @Override
    public Optional<Artiste> findById(Long id) {
        Artiste artiste = null;
        // on gère try catch
        try {
            artiste = session.find(Artiste.class, id);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(artiste);

    }

    @Override
    public void save(Artiste model) {
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(model);

        try {
            tx.commit();
        } catch (IllegalStateException | RollbackException e) {
            tx.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Artiste model) {
        Optional<Artiste> a = this.findById(model.getIdentifiant());
        if (a.isPresent()){
            model = a.get();
        }

        // methode pour effacer un artiste
        ArticleService articleService = new ArticleService();
        model.getArticles().forEach(article -> {
            if (article.getArtistes().size() == 1) {
                articleService.delete(article);
            }
        });
        Transaction tx = session.beginTransaction();
        session.delete(model);

        try {
            tx.commit();
        } catch (IllegalStateException | RollbackException e) {
            tx.rollback();
            e.printStackTrace();
        }

    }


}

