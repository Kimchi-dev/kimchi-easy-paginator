package me.kimchi.pagination.constant;

import java.util.Arrays;

public enum PaginatorConstant {

    MYSQL_PAGING(100, "MySql Paging"),
    ORACLE_PAGING(101, "Oracle Paging"),
    POSTGRESQL_PAGING(102, "PostgreSql Paging"),
    ;

    private int code;
    private String description;

    PaginatorConstant(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static PaginatorConstant getConstantByCode(final int target) throws Exception {
        PaginatorConstant constant = Arrays.stream(PaginatorConstant.values()).filter(c -> c.code == target)
                .findFirst().get();

        if(constant == null) {
            throw new NullPointerException("There is no correspond constants that, which registered with this code.");
        }

        return constant;
    }

    public boolean isSameConstant(final PaginatorConstant constant) {

        if(constant == null) {
            throw new NullPointerException("This Parameter constant cannot be null.");
        }

        return (this.code == constant.code);
    }
}
