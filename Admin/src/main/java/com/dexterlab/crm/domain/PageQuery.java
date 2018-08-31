package com.dexterlab.crm.domain;

import com.baomidou.mybatisplus.plugins.Page;

public class PageQuery {
    private int pageSize;
    private int pages;

    public Page buildPage(){
        return new Page(pages,pageSize);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int currentPage) {
        this.pages = currentPage;
    }
}
