package com.diy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.diy.pojo.Brand;
import com.diy.pojo.PageBean;
import com.diy.service.BrandService1;
import com.diy.service.Impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{

    private BrandService1 BS = new BrandServiceImpl();

    /**
     * 查询所有
     */
    public void queryAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 处理乱码问题
        resp.setContentType("text/json;charset=utf-8");

        // 调用service查询
        List<Brand> brands = BS.queryAll();

        // 转为JSON
        String jbs = JSON.toJSONString(brands);

        // 写数据
        resp.getWriter().write(jbs);
    }

    /**
     * 添加品牌
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取输入流，接收品牌数据json字符串
        BufferedReader brd = req.getReader();
        String params = brd.readLine();

        // 将json字符串转为Brand对象
        Brand brand = JSON.parseObject(params,Brand.class);

        // 调用service添加
        BS.add(brand);

        // 响应成功的标识
        resp.getWriter().write("success");

    }

    /**
     * 更新品牌
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理乱码问题
        req.setCharacterEncoding("utf-8");
        // 获取输入流，接收品牌数据json字符串
        BufferedReader reader = req.getReader();
        String params = reader.readLine();//no matter how long, it is one line
        // 将json字符串转为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        // 调用方法
        BS.update(brand);

        // 写数据
        resp.getWriter().write("success");

    }

    /**
     * 删除单条数据
     */
    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        // 转换为整型
        int id = Integer.parseInt(sid);

        BS.deleteById(id);

        resp.getWriter().write("success");
    }

    /**
     * 批量删除数据
     */
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取输入流，接收品牌数据json字符串
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        // 将json字符串转为int[]数组
        int[] ids = JSON.parseObject(params, int[].class);

        BS.deleteByIds(ids);

        resp.getWriter().write("success");

    }

    /**
     * 分页查询
     */
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 调用service查询
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);


        PageBean<Brand> pageBean = BS.selectByPage(currentPage, pageSize);

        // 转为JSON
        String jpb = JSON.toJSONString(pageBean);

        // 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jpb);
    }

    /**
     *  分页条件查询
     */
    public void selectByPageWithCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        // 转为 Brand
        Brand brand = JSON.parseObject(params, Brand.class);

        // 调用service查询
        PageBean<Brand> pageBean = BS.selectByPageWithCondition(currentPage, pageSize,brand);

        // 转为JSON
        String jpb = JSON.toJSONString(pageBean);

        // 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jpb);
    }
}
