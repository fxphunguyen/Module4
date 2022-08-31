package com.codegym.service;

import com.codegym.model.Blog;

import java.util.List;

public interface IGeneralService<B> {
    List<Blog> findAll();

    Blog findById(long id);

    void save(Blog blog);

    void remove(Long id);
}
