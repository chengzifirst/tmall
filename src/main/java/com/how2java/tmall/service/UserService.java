package com.how2java.tmall.service;

import com.how2java.tmall.dao.UserDao;
import com.how2java.tmall.pojo.User;
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
public class UserService {
    @Autowired
    UserDao userDao;

    public User selectOne(int id){
        return userDao.selectOne(id);
    }

    public Page4Navigator<User> list(int start,int size,int navigatePages){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(start,size,sort);

        List<User> cs = userDao.findAll();
        Page<User> page = listConvertToPage(cs,pageable);
        return new Page4Navigator<>(page,navigatePages);
    }
}
