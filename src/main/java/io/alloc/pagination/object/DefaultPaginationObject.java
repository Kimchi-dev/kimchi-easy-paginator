package kr.devis.util.offsetpaginator.pagination.object;

public class DefaultPaginationObject extends AbstractPaginationObject{

    private int startNum;
    private int endNum;

    public void setResult(int startNum, int endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
    }

    @Override
    public final int getStartIndex() {
        return this.startNum;
    }

    @Override
    public final int getEndIndex() {
        return this.endNum;
    }

    @Override
    public PaginatedResult convertToPaginatedObject() {

        PaginatedResult object = new PaginatedResult();

        object.setResultData(getStartIndex(),getEndIndex(), isAbleToPreviousStep(), isAbleToNextStep(), hasNextPage(), getStartPage(), getEndPage(), getCurrentPage(), getContentsPerPage());
        return object;
    }
}
