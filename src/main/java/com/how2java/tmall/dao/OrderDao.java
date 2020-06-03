package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderDao {
    void insert(Order order);
    void update(Order order);
    void delete(int id);

    Order selectOne(int id);
    List<Order> findAll();
    List<Order> findByUserAndStatusNot(int uid, String status);
}
