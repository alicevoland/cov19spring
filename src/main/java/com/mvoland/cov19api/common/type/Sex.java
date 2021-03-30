package com.mvoland.cov19api.common.type;


public enum Sex {
    ALL,
    FEMALE,
    MALE,
    UNKNOWN;

    public static Sex fromValue(Integer ageValue) {
        switch (ageValue) {
            case 0:
                return Sex.ALL;
            case 1:
                return Sex.MALE;
            case 2:
                return Sex.FEMALE;
            default:
                return Sex.UNKNOWN;
        }
    }

    public Integer toValue() {
        switch (this) {
            case ALL:
                return 0;
            case FEMALE:
                return 1;
            case MALE:
                return 2;
            default:
                return -1;
        }

    }

}
