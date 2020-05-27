package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Property;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PropertyDao {
    void insert(Property property);
    void update(Property property);
    void delete(int id);

    Property selectOne(int id);
    List<Property> findAll();

    List<Property> findByCategory(int cid);

}
