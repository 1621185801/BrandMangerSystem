package com.diy.service.Impl;

import com.diy.mapper.BrandMapper;
import com.diy.pojo.Brand;
import com.diy.pojo.PageBean;
import com.diy.service.BrandService1;
import com.diy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService1 {

    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     */
    @Override
    public List<Brand> queryAll() {

        //2. 获取SqlSession对象
        SqlSession Session = factory.openSession();

        //3. 获取BrandMapper
        BrandMapper mapper = Session.getMapper(BrandMapper.class);

        //4. 调用方法
        List<Brand> brands = mapper.queryAll();
        //5. 释放资源
        Session.close();

        return brands;

    }

    /**
     * 添加品牌
     */
    @Override
    public void add(Brand brand) {
        SqlSession sess = factory.openSession();

        BrandMapper mapper = sess.getMapper(BrandMapper.class);

        mapper.add(brand);

        sess.commit();

        sess.close();
    }

    /**
     * 更新品牌
     */
    @Override
    public void update(Brand brand) {
        SqlSession sess = factory.openSession();

        BrandMapper mapper = sess.getMapper(BrandMapper.class);

        mapper.update(brand);

        sess.commit();

        sess.close();
    }

    /**
     * 删除品牌
     */
    @Override
    public void deleteById(int id) {
        SqlSession sess = factory.openSession();

        BrandMapper mapper = sess.getMapper(BrandMapper.class);

        mapper.deleteById(id);

        sess.commit();

        sess.close();

    }

    /**
     * 批量删除品牌
     */
    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sess = factory.openSession();

        BrandMapper mapper = sess.getMapper(BrandMapper.class);

        mapper.deleteByIds(ids);

        sess.commit();

        sess.close();
    }

    /**
     * 分页查询
     */
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sess = factory.openSession();
        BrandMapper mapper = sess.getMapper(BrandMapper.class);

        // 计算查询条目数
        int size = pageSize;
        // 计算开始索引
        int begin = (currentPage -1) * pageSize;
        // 查询当前页数据
        List<Brand> rowsInPage = mapper.selectByPage(begin, size);
        int count = mapper.selectTotalCount();
        //  封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRowsInPage(rowsInPage);
        pageBean.setTotalCount(count);

        sess.close();

        return pageBean;
    }

    /**
     * 根据条件查询和分页
     */
    @Override
    public PageBean<Brand> selectByPageWithCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession sess = factory.openSession();
        BrandMapper mapper = sess.getMapper(BrandMapper.class);

        // 计算查询条目数
        int size = pageSize;
        // 计算开始索引
        int begin = (currentPage -1) * pageSize;

        // 处理brand条件，模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() >0) {
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() >0) {
            brand.setCompanyName("%"+companyName+"%");
        }

        List<Brand> rowsInPage = mapper.selectByPageWithCondition(begin, size, brand);
        int totalCount = mapper.selectTotalCountWithCondition(brand);

        // 封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRowsInPage(rowsInPage);
        pageBean.setTotalCount(totalCount);

        sess.close();

        return pageBean;
    }


}
