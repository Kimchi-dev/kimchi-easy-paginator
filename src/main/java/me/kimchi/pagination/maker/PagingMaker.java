package me.kimchi.pagination.maker;

import me.kimchi.pagination.object.PaginatedObject;

public class PagingMaker implements Maker{

    private Maker maker;

    public PagingMaker(PaginatedObject target, String endPoint) {
        this.maker = new PaginationHTMLMaker(target, endPoint);
    }

    public PagingMaker(PaginatedObject target, String endPoint, boolean exposeDisabledMoveBlock) {
        this.maker = new PaginationHTMLMaker(target, endPoint ,exposeDisabledMoveBlock);
    }

    @Override
    public Maker setMoveButtonName(String pre, String next) {
        this.maker.setMoveButtonName(pre, next);
        return this;
    }

    @Override
    public Maker generate() throws Exception {
        this.maker.generate();
        return this;
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
    public Maker withCss() throws Exception {
        this.maker.withCss();
        return this;
    }

    @Override
    public String get() throws Exception {
        return this.maker.get();
    }
}
