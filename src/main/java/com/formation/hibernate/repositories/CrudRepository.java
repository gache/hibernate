package com.formation.hibernate.repositories;

import java.util.Collection;
import java.util.Optional;

public interface CrudRepository <M, I>  {
    Optional<Collection<M>> findAll();
    Optional<M> findById(I id);
    void  save(M model);
    void  delete(M model);

    // CRUD pour dire = Create, Read, Update, Delete
}
