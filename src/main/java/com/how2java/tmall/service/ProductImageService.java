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

    public List<ProductImage> listSingleProductImages(int pid){
        return productImageDao.findByProductAndType(pid,type_single);
    }

    public List<ProductImage> listDetailProductImages(int pid){
        return productImageDao.findByProductAndType(pid,type_detail);
    }

   /* public void setFirstProdutImage(Product product) {
        List<ProductImage> singleImages = listSingleProductImages(product.getId());
        if(!singleImages.isEmpty())
            product.setFirstProductImage(singleImages.get(0));
        else
            product.setFirstProductImage(new ProductImage()); //这样做是考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。

    }
    public void setFirstProdutImages(List<Product> products) {
        for (Product product : products)
            setFirstProdutImage(product);
    }*/



}
