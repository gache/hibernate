package com.formation.hibernate.services;

import com.formation.hibernate.configs.HibernateUtils;
import com.formation.hibernate.models.Image;
import com.formation.hibernate.repositories.ImageRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.RollbackException;
import java.util.Collection;
import java.util.Optional;

public class ImageService implements ImageRepository {
    private final Session session = HibernateUtils.getSession();

    @Override
    public Optional<Collection<Image>> findAll() {
        Collection<Image> images = null;
        org.hibernate.query.Query<Image> q = session.createQuery("from Image", Image.class);
        images = q.list();
        return Optional.ofNullable(images);

    }

    @Override
    public Optional<Image> findById(Long id) {
        Image image = session.find(Image.class, id);
        session.close();
        return Optional.ofNullable(image);

    }

    @Override
    public void save(Image model) {
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
    public void delete(Image model) {
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
