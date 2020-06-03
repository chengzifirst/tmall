package com.how2java.tmall.service;

import com.how2java.tmall.dao.ProductDao;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    ReviewService reviewService;

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
        List<ProductImage> singleImages = productImageService.listSingleProductImages(product);
        if(!singleImages.isEmpty())
            product.setFirstProductImage(singleImages.get(0));
        else
            product.setFirstProductImage(new ProductImage()); //这样做是考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。

    }
    public void setFirstProdutImages(List<Product> products) {
        for (Product product : products)
            setFirstProdutImage(product);
    }

    /*为分类填充产品集合*/
    public void fill(Category category){
        List<Product> products = productDao.findByCategory(category.getId());
        setFirstProdutImages(products);
        category.setProducts(products);
    }

    /*为多个分类填充产品集合*/
    public void fill(List<Category> categories){
        for(Category category : categories){
            fill(category);
        }
    }

    /*为多个分类填充推荐产品集合*/
    public void fillByRow(List<Category> categories){
        int productNumberEachRow = 8;
        for(Category category : categories){
            List<Product> products = productDao.findByCategory(category.getId());
            List<List<Product>> productsByRow = new ArrayList<>();
            for(int  i = 0;i < products.size();i+=productNumberEachRow){
                int size = i + productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productEachRow = products.subList(i,size);
                productsByRow.add(productEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }

    public void setSaleAndReviewNumber(Product product) {
        int saleCount = orderItemService.getSaleCount(product);
        product.setSaleCount(saleCount);

        int reviewCount = reviewService.countByProduct(product);
        product.setReviewCount(reviewCount);

    }

    public void setSaleAndReviewNumber(List<Product> products) {
        for (Product product : products)
            setSaleAndReviewNumber(product);
    }

    public List<Product> search(String keyword, int start, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(start,size,sort);

        List<Product> products = productDao.findByNameLike(keyword);
        Page<Product> page = listConvertToPage(products,pageable);
        return page.getContent();
    }

    public void setFirstProdutImagesOnOrderItems(List<OrderItem> ois) {
        for (OrderItem orderItem : ois) {
            setFirstProdutImage(orderItem.getProduct());
        }
    }


}

