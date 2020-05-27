package com.how2java.tmall.pojo;

import java.util.Date;

public class Product {
    private Integer id;
    private String name;
    private String subtitle;
    private Object originalprice;
    private Object promoteprice;
    private Integer stock;
    private Integer cid;
    private Date createdate;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    
    public Object getOriginalprice() {
        return originalprice;
    }

    public void setOriginalprice(Object originalprice) {
        this.originalprice = originalprice;
    }
    
    public Object getPromoteprice() {
        return promoteprice;
    }

    public void setPromoteprice(Object promoteprice) {
        this.promoteprice = promoteprice;
    }
    
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
    
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

}