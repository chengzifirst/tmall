package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    void insert(User user);
    void update(User user);
    void delete(int id);

    User selectOne(int id);
    List<User> findAll();

    User findByName(String name);
    User findByNameAndPassword(String name,String password);

}
