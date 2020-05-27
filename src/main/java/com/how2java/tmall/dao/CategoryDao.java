package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoryDao {

    Category get(int id);
    void add(Category category);
    void delete(int id);
    void update(Category category);

    List<Category> findAll();
}
