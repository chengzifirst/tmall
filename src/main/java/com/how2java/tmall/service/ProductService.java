package com.how2java.tmall.service;

import com.how2java.tmall.dao.ProductDao;
import com.how2java.tmall.pojo.Product;
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
public class ProductService {
    @Autowired
    ProductDao productDao;
    CategoryService categoryService;

    public void insert(Product product){
        productDao.insert(product);
    }

    public void update(Product product){
        productDao.update(product);
    }

    public void delete(int id){
        productDao.delete(id);
    }

    public Product selectOne(int id){
        return productDao.selectOne(id);
    }

  public Page4Navigator<Product> findByCategory(int cid,int start, int size,int navigatePages){
      Sort sort = Sort.by(Sort.Direction.DESC,"id");
      Pageable pageable = PageRequest.of(start,size,sort);

      List<Product> cs = productDao.findByCategory(cid);
      Page<Product> page = listConvertToPage(cs,pageable);
      return new Page4Navigator<>(page,navigatePages);
  }


}

