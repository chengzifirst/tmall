package com.how2java.tmall.service;

import com.how2java.tmall.dao.PropertyDao;
import com.how2java.tmall.pojo.Property;
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
public class PropertyService {
    @Autowired
    PropertyDao propertyDao;

    public void insert(Property property){
        propertyDao.insert(property);
    }

    public void update(Property property){
        propertyDao.update(property);
    }

    public void delete(int id){
        propertyDao.delete(id);
    }

    public Property selectOne(int id){
        return propertyDao.selectOne(id);
    }

    public Page4Navigator<Property> findByCategory(int cid, int start, int size,int navigatePages){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(start,size,sort);

        List<Property> cs = propertyDao.findByCategory(cid);
        Page<Property> pageFromMybatis = listConvertToPage(cs,pageable);
        return new Page4Navigator<>(pageFromMybatis,navigatePages);
    }

    public List<Property> findByCategory(int cid){
        return propertyDao.findByCategory(cid);
    }


}
