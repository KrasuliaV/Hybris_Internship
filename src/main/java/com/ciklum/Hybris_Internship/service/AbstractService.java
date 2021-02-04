package com.ciklum.Hybris_Internship.service;

import com.ciklum.Hybris_Internship.model.Product;

import java.util.List;

public interface AbstractService<E> {

    public E create(E e);

    public E readById(long id);

    public E update(E e);

    public void delete(long id);

    public List<E> getAll();
}
