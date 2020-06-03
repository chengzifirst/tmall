package com.how2java.tmall.service;

import com.how2java.tmall.dao.ReviewDao;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Review;
import com.how2java.tmall.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewDao reviewDao;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    public void insert(Review review){
        reviewDao.insert(review);
    }

    public List<Review> findByProduct(Product product){
        List<Review> reviews = reviewDao.findByProduct(product.getId());
        for(Review review : reviews){
            User user = userService.selectOne(review.getUid());
            Product product1 = productService.selectOne(review.getPid());
            review.setUser(user);
            review.setProduct(product1);
        }

        return reviews;
    }

    public int countByProduct(Product product){
        return reviewDao.countByProduct(product.getId());
    }
}
