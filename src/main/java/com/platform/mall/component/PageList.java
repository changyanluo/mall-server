package com.platform.mall.component;

import java.util.List;
import com.github.pagehelper.PageInfo;

//分页数据封装
public class PageList<T> {
    private int pageIndex; //页号，从1开始
    private int pageSize;
    private int totalPage;
    private long total;
    private List<T> list;

    public static <T> PageList<T> getPageList(List<T> list){
        PageInfo<T> pageInfo = new PageInfo<>(list);
        PageList<T> result = new PageList<>();
        result.setPageIndex(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setTotalPage(pageInfo.getPages());
        result.setList(pageInfo.getList());
        return result;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
