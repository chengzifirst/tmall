package com.how2java.tmall.service;

import com.how2java.tmall.dao.ProductImageDao;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {
    public static final String type_single = "single";
    public static final String type_detail = "detail";

    @Autowired
    ProductImageDao productImageDao;

    public void insert(ProductImage productImage){
        productImageDao.insert(productImage);
    }

    public void delete(int id){
        productImageDao.delete(id);
    }

    public ProductImage selectOne(int id){
        return productImageDao.selectOne(id);
    }

    public List<ProductImage> listSingleProductImages(Product product){
        return productImageDao.findByProductAndType(product.getId(),type_single);
    }

    public List<ProductImage> listDetailProductImages(Product product){
        return productImageDao.findByProductAndType(product.getId(),type_detail);
    }


}
