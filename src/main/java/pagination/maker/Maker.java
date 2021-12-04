package pagination.maker;

public interface Maker {

    Maker setMoveButtonName(String pre, String next);

    Maker generate();

    void download(String downloadPath) throws Exception;

    Maker html();

    Maker withCss();

    String get() throws Exception;
}
