package com.how2java.tmall.controller;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories/{cid}/products")
    public Page4Navigator<Product> list(@PathVariable("cid") int cid, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start < 0 ? 0 : start;
        Page4Navigator<Product> page = productService.findByCategory(cid, start, size, 5);

        for(Product p : page.getContent()){
            System.out.println(p.getName());
        }
        List<Product> cs = page.getContent();
        productService.setFirstProdutImages(cs);

        return page;
    }

    @GetMapping("/products/{id}")
    public Product get(@PathVariable("id") int id) throws Exception {
        Product bean = productService.selectOne(id);

        /*设置product.category传给前端*/
        Category c = categoryService.get(bean.getCid());
        bean.setCategory(c);

        return bean;
    }

    @PostMapping("/products")
    public Object add(@RequestBody Product bean) throws Exception {
        productService.insert(bean);
        return bean;
    }

    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        productService.delete(id);
        return null;
    }

    @PutMapping("/products")
    public Object update(@RequestBody Product bean) throws Exception {
        productService.update(bean);
        return bean;
    }
}
