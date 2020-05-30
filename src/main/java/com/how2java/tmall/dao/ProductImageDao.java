package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.ProductImage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductImageDao {
    //只有图片，不需要更新功能
    void insert(ProductImage productImage);
    void delete(int id);

    ProductImage selectOne(int id);
    List<ProductImage> findByProductAndType(int pid,String type);
}

