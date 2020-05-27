package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductDao {
    void insert(Product product);
    void update(Product product);
    void delete(int id);

    Product selectOne(int id);
    List<Product> findByCategory(int cid);
}
