package pagination.object;

public class PaginatedObject {

    private int startIndex;
    private int endIndex;
    private boolean ableToPreStep;
    private boolean ableToNextStep;
    private boolean hasNextPage;
    private int startPage;
    private int endPage;
    private int currentPage;
    private int contentsPerPage;

    void setResultData(int startIndex, int endIndex, boolean ableToPreStep, boolean ableToNextStep, boolean hasNextPage, int startPage, int endPage, int currentPage,int contentsPerPage) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.ableToPreStep = ableToPreStep;
        this.ableToNextStep = ableToNextStep;
        this.hasNextPage = hasNextPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.currentPage = currentPage;
        this.contentsPerPage = contentsPerPage;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public boolean isAbleToPreStep() {
        return this.ableToPreStep;
    }

    public boolean isAbleToNextStep() {
        return this.ableToNextStep;
    }

    public boolean hasNextPage() {
        return this.hasNextPage;
    }

    public int getStartPage() {
        return this.startPage;
    }

    public int getEndPage() {
        return this.endPage;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getContentsPerPage() {
        return this.contentsPerPage;
    }
}
