package com.how2java.tmall.service;

import com.how2java.tmall.dao.ReviewDao;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewDao reviewDao;

    @Autowired
    ProductService productService;

    public void insert(Review review){
        reviewDao.insert(review);
    }

    public List<Review> findByProduct(Product product){
        return reviewDao.findByProduct(product.getId());
    }

    public int countByProduct(Product product){
        return reviewDao.countByProduct(product.getId());
    }
}
