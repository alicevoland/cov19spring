package com.mvoland.cov19api.data.entity;

import javax.persistence.Embeddable;

@Embeddable
public enum AgeGroup {
    ALL(0),
    FROM_0_TO_9(9),
    FROM_10_TO_19(19),
    FROM_20_TO_29(29),
    FROM_30_TO_39(39),
    FROM_40_TO_49(49),
    FROM_50_TO_59(59),
    FROM_60_TO_69(69),
    FROM_70_TO_79(79),
    FROM_80_TO_89(89),
    MORE_THAN_90(90);

    private Integer ageValue;

    private AgeGroup(Integer ageValue) {
        this.ageValue = ageValue;
    }

    public Integer getAgeValue() {
        return this.ageValue;
    }

    public void setAgeValue(Integer ageValue) {
        this.ageValue = ageValue;
    }

    @Override
    public String toString() {
        return "AgeGroup{" +
                "ageValue=" + ageValue +
                '}';
    }

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
                return AgeGroup.ALL;
        }
    }
}
