package com.how2java.tmall.service;

import com.how2java.tmall.dao.CategoryDao;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.how2java.tmall.util.ListConvertToPage.listConvertToPage;

/*
* 关键是将categoryDao.findAll()获取到的List转化为Page
* 注意：Page一次只有size条数据，而findAll是获取所有数据*/
@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public void add(Category category){
        categoryDao.add(category);
    }

    public void delete(int id){
        categoryDao.delete(id);
    }

    public Category get(int id){
        return categoryDao.get(id);
    }

    public void update(Category category){
        categoryDao.update(category);
    }



    //查询所有分页，Mybatis框架使用JPA分页
    public Page4Navigator<Category> list(int start,int size,int navigatePages){     //start:当前是第几页，size:一页有多少条数据
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(start,size,sort);
        List<Category> cs = categoryDao.findAll();
        Page pageFromMybatis = listConvertToPage(cs,pageable);
        return new Page4Navigator<>(pageFromMybatis,navigatePages);
    }

    public List<Category> list(){
        return categoryDao.findAll();
    }

    /*删除Product对象的Category，防止无限递归*/
    public void removeCategoryFromProduct(Category category){
        List<Product> products = category.getProducts();
        if(null != products){
            for(Product product : products){
                product.setCategory(null);
            }
        }

        List<List<Product>> productByRow = category.getProductsByRow();
        if(null != productByRow){
            for(List<Product> ps : productByRow){
                for(Product p : ps){
                    p.setCategory(null);
                }
            }
        }
    }

    public void removeCategoryFromProduct(List<Category> categories){
        for(Category c : categories){
            removeCategoryFromProduct(c);
        }
    }

}


