package com.mvoland.cov19api.common.type;

public enum AgeGroup {
    ALL,
    FROM_0_TO_9,
    FROM_10_TO_19,
    FROM_20_TO_29,
    FROM_30_TO_39,
    FROM_40_TO_49,
    FROM_50_TO_59,
    FROM_60_TO_69,
    FROM_70_TO_79,
    FROM_80_TO_89,
    MORE_THAN_90,
    UNKNOWN;


    public static AgeGroup fromValue(Integer ageValue) {
        switch (ageValue) {
            case 0:
                return AgeGroup.ALL;
            case 9:
                return AgeGroup.FROM_0_TO_9;
            case 19:
                return AgeGroup.FROM_10_TO_19;
            case 29:
                return AgeGroup.FROM_20_TO_29;
            case 39:
                return AgeGroup.FROM_30_TO_39;
            case 49:
                return AgeGroup.FROM_40_TO_49;
            case 59:
                return AgeGroup.FROM_50_TO_59;
            case 69:
                return AgeGroup.FROM_60_TO_69;
            case 79:
                return AgeGroup.FROM_70_TO_79;
            case 89:
                return AgeGroup.FROM_80_TO_89;
            case 90:
                return AgeGroup.MORE_THAN_90;
            default:
                return AgeGroup.UNKNOWN;
        }
    }

    public Integer toValue() {
        switch (this) {
            case ALL:
                return 0;
            case FROM_0_TO_9:
                return 9;
            case FROM_10_TO_19:
                return 19;
            case FROM_20_TO_29:
                return 29;
            case FROM_30_TO_39:
                return 39;
            case FROM_40_TO_49:
                return 49;
            case FROM_50_TO_59:
                return 59;
            case FROM_60_TO_69:
                return 69;
            case FROM_70_TO_79:
                return 79;
            case FROM_80_TO_89:
                return 89;
            case MORE_THAN_90:
                return 90;
            default:
                return -1;
        }

    }
}
