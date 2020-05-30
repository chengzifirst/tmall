package com.how2java.tmall.service;

import com.how2java.tmall.dao.OrderItemDao;
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemDao orderItemDao;

    public List<OrderItem> findByOrder(int oid){
        return orderItemDao.findByOrder(oid);
    }
}
