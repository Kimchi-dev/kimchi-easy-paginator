package kr.devis.util.offsetpaginator.constant;

public enum CalculateConstant {
    CURRENT_PAGE(100, "CURRENT_PAGE"),
    CONTENTS_PER_PAGE(101, "CONTENTS_PER_PAGE"),
    NUM_SIZE_PER_PAGE(102, "NUM_SIZE_PER_PAGE"),
    TOTAL_CONTENTS_COUNT(103, "TOTAL_CONTENTS_COUNT"),
    TOTAL_PAGE_COUNT(104, "TOTAL_PAGE_COUNT"),
    CURRENT_STEP(105, "CURRENT_STEP"),
    TOTAL_STEP_CNT(106, "TOTAL_STEP_CNT"),
    START_PAGE(107, "START_PAGE"),
    END_PAGE(108, "END_PAGE"),
    START_INDEX(109, "START_INDEX"),
    END_INDEX(110, "END_INDEX"),
    ;

    private int valueCode;
    private String valueName;

    CalculateConstant(int valueCode, String valueName) {
        this.valueCode = valueCode;
        this.valueName = valueName;
    }

    public String getValueName() {
        return this.valueName;
    }
}
