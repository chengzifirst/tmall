package com.how2java.tmall.service;

import com.how2java.tmall.dao.OrderDao;
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.how2java.tmall.util.ListConvertToPage.listConvertToPage;

@Service
public class OrderService {
    public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    public Order selectOne(int id){
        return orderDao.selectOne(id);
    }

    public void update(Order order){
        orderDao.update(order);
    }

    public void delete(int id){
        orderDao.delete(id);
    }

    public Page4Navigator<Order> list(int start,int size,int navigatePages){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(start,size,sort);

        List<Order> cs = orderDao.findAll();
        for(Order order : cs){
            User user = userService.selectOne(order.getUid());
            order.setUser(user);
        }

        Page<Order> page = listConvertToPage(cs,pageable);
        return new Page4Navigator<>(page,navigatePages);
    }

    public void removeOrderFromOrderItem(List<Order> orders) {
        for (Order order : orders) {
            removeOrderFromOrderItem(order);
        }
    }

    private void removeOrderFromOrderItem(Order order) {
        List<OrderItem> orderItems= order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(null);
        }
    }

    public void fill(List<Order> orders) {
        for (Order order : orders)
            fill(order);
    }

    public void fill(Order order) {
        List<OrderItem> orderItems = orderItemService.findByOrder(order.getId());
        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi :orderItems) {
            Product product = productService.selectOne(oi.getPid());
            oi.setProduct(product);
            total+=oi.getNumber()*oi.getProduct().getPromotePrice();
            totalNumber+=oi.getNumber();
            productService.setFirstProdutImage(oi.getProduct());
        }
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);
    }
}
