package com.how2java.tmall.util;

import com.how2java.tmall.pojo.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ListConvertToPage {
    public static Page listConvertToPage(List<?> list, Pageable pageable){
        //List需要手动分割
        //当前页第一条数据在List中的位置
        int start = (int)pageable.getOffset();
        // 当前页最后一条数据在List中的位置
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
        return new PageImpl(list.subList(start, end), pageable, list.size());
    }
}
