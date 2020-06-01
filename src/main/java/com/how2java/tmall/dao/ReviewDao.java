package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Review;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewDao {
    void insert(Review review);
    void update(Review review);
    void delete(int id);

    Review selectOne(int id);
    List<Review> findByProduct(int pid);
    int countByProduct(int pid);
}
