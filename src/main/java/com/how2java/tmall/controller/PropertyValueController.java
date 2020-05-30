package com.how2java.tmall.controller;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.pojo.PropertyValue;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.service.PropertyService;
import com.how2java.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;

    @Autowired
    ProductService productService;

    @Autowired
    PropertyService propertyService;

    @GetMapping("/products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("pid") int pid) throws Exception {
        propertyValueService.init(pid);
        List<PropertyValue> propertyValues = propertyValueService.findByProduct(pid);

        //为每个产品属性值设置product属性和property属性
        for(PropertyValue p : propertyValues){
            Product product = productService.selectOne(p.getPid());
            Property property = propertyService.selectOne(p.getPtid());
            p.setProduct(product);
            p.setProperty(property);

        }
        return propertyValues;
    }

    @PutMapping("/propertyValues")
    public Object update(@RequestBody PropertyValue bean) throws Exception {
        propertyValueService.update(bean);
        return bean;
    }
}
