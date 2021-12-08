package com.github.kimchidev.pagination.object;

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
    public PaginatedObject convertToPaginatedObject() {

        PaginatedObject object = new PaginatedObject();

        object.setResultData(getStartIndex(),getEndIndex(), isAbleToPreviousStep(), isAbleToNextStep(), hasNextPage(), getStartPage(), getEndPage(), getCurrentPage(), getContentsPerPage());
        return object;
    }
}
