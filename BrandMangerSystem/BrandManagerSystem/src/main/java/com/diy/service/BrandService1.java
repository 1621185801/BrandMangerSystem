package com.diy.service;

import com.diy.pojo.Brand;
import com.diy.pojo.PageBean;

import java.util.List;



public interface BrandService1 {

    /**
     * 查询所有
     */
    List<Brand> queryAll();

    /**
     * 添加品牌
     */
    void add(Brand brand);

    /**
     * 更新数据
     */
    void update(Brand brand);

    /**
     * 单个删除
     */
    void deleteById(int id);

    /**
     * 批量删除
     */
    void deleteByIds(int[] ids);

    /**
     * 分页查询
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);


    /**
     * 分页条件查询
     */
    PageBean<Brand> selectByPageWithCondition(int currentPage, int pageSize, Brand brand);


}
