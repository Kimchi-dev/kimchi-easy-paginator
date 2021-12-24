package com.github.kimchidev.pagination.maker;

import com.github.kimchidev.pagination.object.PaginatedObject;

public class PageMaker implements Maker{

    private Maker maker;

    public PageMaker(PaginatedObject target, String endPoint) {
        this.maker = new PaginationHTMLMaker(target, endPoint);
    }

    @Override
    public Maker setMoveButtonName(String pre, String next) {
        this.maker.setMoveButtonName(pre, next);
        return this;
    }

    @Override
    public Maker exposeDisabled() {
        return this.maker.exposeDisabled();
    }

    @Override
    public void download(String downloadPath) throws Exception {
        maker.download(downloadPath);
    }

    @Override
    public Maker html() {
        this.maker.html();
        return this;
    }

    @Override
    public Maker css() throws Exception {
        this.maker.css();
        return this;
    }

    @Override
    public String get() throws Exception {
        return this.maker.get();
    }
}
