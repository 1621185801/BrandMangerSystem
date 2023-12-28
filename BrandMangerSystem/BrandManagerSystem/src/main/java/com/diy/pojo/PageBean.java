package com.diy.pojo;

import java.util.List;

//分页查询的JavaBean

public class PageBean<T> {
    // 总记录数
    private int totalCount;
    // 当前页数据
    private List<T> rowsInPage;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRowsInPage() {
        return rowsInPage;
    }

    public void setRowsInPage(List<T> rowsInPage) {
        this.rowsInPage = rowsInPage;
    }
}
