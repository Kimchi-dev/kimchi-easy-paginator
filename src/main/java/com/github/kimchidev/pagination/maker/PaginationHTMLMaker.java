package com.github.kimchidev.pagination.maker;

import com.github.kimchidev.pagination.object.PaginatedObject;
import com.github.kimchidev.pagination.util.PagingUtil;

import java.net.URL;
import java.util.Random;

public class PaginationHTMLMaker implements Maker {

    private PaginatedObject target;
    private boolean exposeDisabledMoveBlock;
    private String pre;
    private String next;
    private String endPoint;
    private String helperPage;
    private String html;
    private String css;

    private static final String TAB = "\t";
    private static final String NEW = "\n";

    public PaginationHTMLMaker(PaginatedObject target, String endPoint) {
        this.target = target;
        this.exposeDisabledMoveBlock = false;
        this.endPoint = endPoint;
        this.pre = "Pre";
        this.next = "Next";
    }

    public PaginationHTMLMaker(PaginatedObject target, String endPoint, boolean exposeDisabledMoveBlock) {
        this.target = target;
        this.exposeDisabledMoveBlock = exposeDisabledMoveBlock;
        this.endPoint = endPoint;
        this.pre = "Pre";
        this.next = "Next";
    }

    @Override
    public Maker setMoveButtonName(String pre, String next) {
        this.pre = pre;
        this.next = next;
        return this;
    }

    @Override
    public Maker generate() throws Exception {
        String helperPage = getSampleHTML();
        if (this.html == null) {
            this.html = "";
        }
        if(this.css == null) {
            this.css = "";
        }

        helperPage = helperPage
                .replace("<KimchiSampleBoard/>", getSampleBoardList(this.target.getContentsPerPage(), this.target.getStartIndex()))
                .replace("<KimchiPagination/>", this.html)
                .replace("<KimchiSampleCSS/>", this.css)
        ;
        this.helperPage = helperPage;
        return this;
    }

    @Override
    public void download(String downloadPath) throws Exception {
        if(this.helperPage == null) {
            throw new NullPointerException("There is no generate() method. If You want to get a sample page, call .generate().download({some-path})");
        }
        PagingUtil.writeFile(this.helperPage, downloadPath, "html");
    }

    private String getSampleHTML() throws Exception {
        ClassLoader classLoader = this.getClass().getClassLoader();

        String html = PagingUtil.readFile(classLoader, "maker/sample-template.html");

        return html;
    }

    @Override
    public Maker withCss() throws Exception {
        StringBuilder builder = new StringBuilder();
        ClassLoader classLoader = this.getClass().getClassLoader();
        String css = PagingUtil.readFile(classLoader, "maker/paginate.css");
        builder
                .append("<style>").append(NEW)
                .append(css).append(NEW)
                .append("</style>").append(NEW)
        ;
        this.css = builder.toString();
        return this;
    }

    private String getSampleBoardList(int contentsSizePerPage, int startIndex) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();

        for(int i = startIndex;i < (contentsSizePerPage + startIndex);i++) {
            builder.append("<tr>").append(NEW)
                    .append("<td>"+i+"</td>").append(NEW)
                    .append("<td>Kimchi-dev</td>").append(NEW)
                    .append("<td>Hello world with "+i+"</td>").append(NEW)
                    .append("<td>"+random.nextInt(4000)+"</td>").append(NEW)
                    .append("<td>1992.01.17 12:34</td>").append(NEW)
                    .append("</tr>").append(NEW);
        }
        return builder.toString();
    }

    @Override
    public Maker html() {

        StringBuilder builder = new StringBuilder();

        builder
                .append("<div class=\"pagination-container\">").append(NEW)
                .append(TAB).append("<ul class=\"pagination\">").append(NEW);
        if(this.target.isAbleToPreStep()) {
            builder
                    .append(TAB).append(TAB).append("<li class=\"page-item\">").append(NEW)
                    .append(TAB).append(TAB).append(TAB).append("<a class=\"pre-button\" href=\""+endPoint+"?currentPage="+this.target.getCurrentPage()+"&pre=true\">"+pre+"</a>").append(NEW)
                    .append(TAB).append(TAB).append("</li>").append(NEW);
        } else if(this.exposeDisabledMoveBlock && ! this.target.isAbleToPreStep()) {
            builder
                    .append(TAB).append(TAB).append("<li class=\"page-item disabled\">").append(NEW)
                    .append(TAB).append(TAB).append(TAB).append("<span class=\"pre-button\">"+pre+"</span>").append(NEW)
                    .append(TAB).append(TAB).append("</li>").append(NEW);
        }
        for (int i = this.target.getStartPage();i <= this.target.getEndPage();i++) {

            if(i != this.target.getCurrentPage()) {
                builder
                        .append(TAB).append(TAB).append("<li class=\"page-item\" aria-current=\"page\">").append(NEW)
                        .append(TAB).append(TAB).append(TAB).append("<a class=\"page-link\" href=\""+endPoint+"?currentPage="+i+"\">"+i+"</a>").append(NEW)
                        .append(TAB).append(TAB).append("</li>").append(NEW);
            } else {
                builder
                        .append(TAB).append(TAB).append("<li class=\"page-item active\"><span class=\"page-link\">"+i+"</span></li>\n");
            }
        }
        if(this.target.isAbleToNextStep()) {
            builder
                    .append(TAB).append(TAB).append("<li class=\"page-item\">").append(NEW)
                    .append(TAB).append(TAB).append(TAB).append("<a class=\"next-button\" href=\""+endPoint+"?currentPage="+this.target.getCurrentPage()+"&next=true\">"+next+"</a>").append(NEW)
                    .append(TAB).append(TAB).append("</li>").append(NEW);

        } else if(this.exposeDisabledMoveBlock && ! this.target.isAbleToNextStep()) {
            builder
                    .append(TAB).append(TAB).append("<li class=\"page-item disabled\">").append(NEW)
                    .append(TAB).append(TAB).append(TAB).append("<span class=\"next-button\">"+next+"</span>").append(NEW)
                    .append(TAB).append(TAB).append("</li>").append(NEW);
        }
        builder
                .append(TAB).append("</ul>").append(NEW)
                .append("</div>").append(NEW)
        ;

        this.html = builder.toString();
        return this;
    }

    @Override
    public String get() throws Exception {
        if(this.html == null) {
            throw new NullPointerException("essential field cannot be null. please call as maker.html().get();");
        }
        StringBuilder builder = new StringBuilder();
        builder.append(this.html).append(NEW);
        if(this.css != null) {
            builder.append(this.css);
        }
        return builder.toString();
    }

}
