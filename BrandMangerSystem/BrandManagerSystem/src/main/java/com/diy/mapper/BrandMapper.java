package com.diy.mapper;

import com.diy.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {

    /**
     * 查询所有
     */
    @Select("select * from tb_brand")
    @ResultMap("BrandResultMap")
    List<Brand> queryAll();


    /**
     * 添加数据
     */
    @Insert("insert into tb_brand values (null, #{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /**
     * 更新数据
     */
    @Update("update tb_brand set brand_name = #{brandName}, company_name = #{companyName},ordered = #{ordered},description = #{description} where id = #{id}")
    void update(Brand brand);

    /**
     * 删除数据
     */
    @Delete("delete from tb_brand where id = #{id}")
    void deleteById(int id);

    /**
     * 批量删除数据
     */
    void deleteByIds(@Param("ids")int[] ids);

    /**
     * 分页查询
     */
    @Select("select * from tb_brand limit #{begin}, #{size}")
    @ResultMap("BrandResultMap")
    List<Brand> selectByPage(@Param("begin")int begin, @Param("size") int size);

    /**
     * 查询总记录数
     */
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    /**
     * 根据条件查询总记录数
     */
    List<Brand> selectByPageWithCondition(@Param("begin")int begin, @Param("size") int size, @Param("brand") Brand brand);
    int selectTotalCountWithCondition(Brand brand);

}
