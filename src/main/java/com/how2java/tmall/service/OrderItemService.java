package com.how2java.tmall.service;

import com.how2java.tmall.dao.OrderItemDao;
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemDao orderItemDao;

    @Autowired
    ProductService productService;

    public void insert(OrderItem orderItem){
        orderItemDao.insert(orderItem);
    }

    public void update(OrderItem orderItem){
        orderItemDao.update(orderItem);
    }

    public void delete(int id){
        orderItemDao.delete(id);
    }

    public OrderItem selectOne(int id){
        OrderItem orderItem = orderItemDao.selectOne(id);
        Product product = productService.selectOne(orderItem.getPid());
        orderItem.setProduct(product);
        return orderItem;
    }

    public List<OrderItem> findByOrder(int oid){
        return orderItemDao.findByOrder(oid);
    }

    public List<OrderItem> findByProduct(int pid){
        return orderItemDao.findByProduct(pid);
    }

    public List<OrderItem> findByUser(User user){
        return orderItemDao.findByUserAndOrderIsNull(user.getId());
    }

    public int getSaleCount(Product product) {
        List<OrderItem> ois =findByProduct(product.getId());
        int result =0;
        for (OrderItem oi : ois) {
            if(null!=oi.getOrder())
                if(null!= oi.getOrder() && null!=oi.getOrder().getPayDate())
                    result+=oi.getNumber();
        }
        return result;
    }


}
