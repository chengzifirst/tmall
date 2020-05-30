package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.PropertyValue;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PropertyValueDao {
    void insert(PropertyValue propertyValue);
    void update(PropertyValue propertyValue);
    void delete(int id);

    PropertyValue getByPropertyAndProduct(int ptid,int pid);
    List<PropertyValue> findByProduct(int pid);

}
