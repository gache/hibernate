package com.formation.hibernate.services;

import com.formation.hibernate.configs.HibernateUtils;
import com.formation.hibernate.models.Article;
import com.formation.hibernate.repositories.ArticleRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.RollbackException;
import java.util.Collection;
import java.util.Optional;

public class ArticleService implements ArticleRepository {
    private final Session session = HibernateUtils.getSession();


    @Override
    public Optional<Collection<Article>> findAll() {
        Collection<Article> articles = null;
        org.hibernate.query.Query<Article> q = null;
        // on gère try catch
        try {
            q = session.createQuery("from Article ", Article.class);
            //		assert q.list().isEmpty();
            articles = q.list();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(articles);
    }

    @Override
    public Optional<Article> findById(Long id) {
        Article article = null;
        org.hibernate.query.Query<Article> q = null;
        // on gère try catch
        try {
            article = session.find(Article.class, id);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(article);

    }

    @Override
    public void save(Article model) {
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(model);

        try {
            tx.commit();
        } catch (IllegalStateException | RollbackException e) {
            tx.rollback();
            e.printStackTrace();
        }

        session.close();
    }

    @Override
    public void delete(Article model) {
        Transaction tx = session.beginTransaction();

        Optional<Article> a = this.findById(model.getRef());
        if (a.isPresent()){
            model = a.get();
        }
        // version longue
        // Consumer<Image> c = image -> session.delete(image);
        // model.getImages().forEach(c);

        // version courte
        model.getImages().forEach(image -> session.delete(image));

        session.delete(model);

        try {
            tx.commit();
        } catch (IllegalStateException | RollbackException e) {
            tx.rollback();
            e.printStackTrace();
        }

        session.close();

    }
}
