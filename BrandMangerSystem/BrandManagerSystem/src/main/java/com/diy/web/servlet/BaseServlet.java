package com.diy.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class BaseServlet extends HttpServlet {
    // 根据请求的最后一段路径来进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String URI = req.getRequestURI();// /BrandMangerSystem/brand/queryAll

        //  获取最后一段路径，方法名
        int index = URI.lastIndexOf('/');  //  获取到资源的二级路径  selectAll
        String methodName = URI.substring(index + 1);

        /**
         * 获取方法 Method对象
         */
        Class<? extends BaseServlet> cls = this.getClass();
        try {
            // 获取参数
            Method mth = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 执行方法
            mth.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
