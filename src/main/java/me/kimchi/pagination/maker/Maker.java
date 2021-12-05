package me.kimchi.pagination.maker;

public interface Maker {

    Maker setMoveButtonName(String pre, String next);

    Maker generate() throws Exception;

    void download(String downloadPath) throws Exception;

    Maker html();

    Maker withCss() throws Exception;

    String get() throws Exception;
}
