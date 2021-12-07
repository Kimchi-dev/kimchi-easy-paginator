package me.kimchi.pagination.constant;

public enum PagingExceptConstant {
    OPTION_CANNOT_BE_NULL(101, "Paging option cannot be null. You have to choice paging mode. ex) paginator.elastic() or paginator.fixed()"),
    PROCEED_OPTION_AND_BUILD(102, "Paging option cannot be null. You can this paginator with call like that -> paginator.elastic().build().paginate()"),
    DO_INIT_FOR_CALCULATOR(103,"Please do init() method first, for initialize of calculator."),
    NOT_SUPPORTED_CONSTANT(104, "This Paginator constant is not supported.");
    private int eCode;
    private String eMessage;

    PagingExceptConstant(int eCode, String eMessage) {
        this.eCode = eCode;
        this.eMessage = eMessage;
    }

    public String getEMessage() {
        return this.eMessage;
    }

    public int getECode() {
        return this.eCode;
    }


}
