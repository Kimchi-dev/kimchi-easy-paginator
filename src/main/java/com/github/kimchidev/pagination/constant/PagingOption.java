package com.github.kimchidev.pagination.constant;

import java.util.Arrays;

public enum PagingOption {
    FIXED(100, "FIXED PAGING"),
    ELASTIC(200,"ELASTIC PAGING")
    ;

    private int optionCode;
    private String optionName;

    PagingOption(int optionCode, String optionName) {
        this.optionCode = optionCode;
        this.optionName = optionName;
    }

    public static PagingOption getOptionByCode(final int optionCode) throws Exception {
        PagingOption option = Arrays.stream(PagingOption.values()).filter(o -> o.optionCode == optionCode)
                .findFirst().get();

        if(option == null) {
            throw new NullPointerException("There is no correspond constants that, which registered with this code.");
        }

        return option;
    }

    public boolean isSameOption(final PagingOption option) {

        if(option == null) {
            throw new NullPointerException("This Parameter constant cannot be null.");
        }

        return (this.optionCode == option.optionCode);
    }

    public int getOptionCode() {
        return this.optionCode;
    }

    public String getOptionName() {
        return this.optionName;
    }
}
