package com.how2java.tmall;

import com.how2java.tmall.dao.CategoryDao;
import com.how2java.tmall.pojo.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = TmallApplication.class)
class TmallApplicationTests {
    @Autowired
    CategoryDao categoryDao;

    @Test
    void contextLoads() {
        List<Category> cs = categoryDao.findAll();
        for (Category c : cs) {
            System.out.println("c.getName():"+ c.getName());
        }
    }

}
