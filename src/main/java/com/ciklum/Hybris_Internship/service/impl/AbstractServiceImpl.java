package com.ciklum.Hybris_Internship.service.impl;

import com.ciklum.Hybris_Internship.model.AbstractEntity;
import com.ciklum.Hybris_Internship.service.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class AbstractServiceImpl<E extends AbstractEntity, R extends JpaRepository<E,Long>> implements AbstractService<E> {

    private final R repository;

    public AbstractServiceImpl(R repository){
        this.repository = repository;
    }

    @Override
    public E create(E e) {
        if (e == null)
            throw new NullPointerException("Can't be null");
        return repository.saveAndFlush(e);
    }

    @Override
    public E readById(long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with " + id + "not found"));
    }

    @Override
    public E update(E e) {
        if (e != null) {
            readById(e.getId());
            return repository.saveAndFlush(e);
        }
        throw new NullPointerException("Product can't be null");
    }

    @Override
    public void delete(long id) {
        repository.delete(readById(id));
    }

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }
}
