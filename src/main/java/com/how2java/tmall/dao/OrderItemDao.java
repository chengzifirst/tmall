package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderItemDao {
    void insert(OrderItem orderItem);
    void update(OrderItem orderItem);
    void delete(int id);

    OrderItem selectOne(int id);
    List<OrderItem> findByOrder(int oid);

}
