package com.how2java.tmall.service;

import com.how2java.tmall.dao.ProductDao;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;
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

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductImageService productImageService;

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
    public void setFirstProdutImage(Product product) {
        List<ProductImage> singleImages = productImageService.listSingleProductImages(product.getId());
        if(!singleImages.isEmpty())
            product.setFirstProductImage(singleImages.get(0));
        else
            product.setFirstProductImage(new ProductImage()); //这样做是考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。

    }
    public void setFirstProdutImages(List<Product> products) {
        for (Product product : products)
            setFirstProdutImage(product);
    }


}

