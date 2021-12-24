package com.github.kimchidev.pagination.maker;

public interface Maker {

    Maker setMoveButtonName(String pre, String next);

    Maker exposeDisabled();

    Maker html();

    Maker css() throws Exception;

    String get() throws Exception;

    void download(String downloadPath) throws Exception;

}
