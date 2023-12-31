package com.solvd.laba.enums;

import java.util.Arrays;

public enum VehicleCondition {
    EXCELLENT(0, "Excellent condition"),
    GOOD(1, "Vehicle is in good condition with some scratches"),
    FAIR(2, "Vehicle is in fair condition with some damage"),
    DAMAGED(3, "Vehicle is in damaged condition");

    private final int conditionId;
    private final String description;

    VehicleCondition(int conditionId, String description) {
        this.conditionId = conditionId;
        this.description = description;
    }

    public int getConditionId() {
        return conditionId;
    }

    public String getDescription() {
        return description;
    }

    public static VehicleCondition getVehicleConditionByConditionId(int conditionId) {
        return Arrays.stream(VehicleCondition.values())
                .filter(condition -> condition.getConditionId() == conditionId)
                .findFirst()
                .orElse(null);
    }

    public boolean isRepairRequired() {
        if (this == VehicleCondition.DAMAGED || this == VehicleCondition.FAIR){
            return true;
        }
        return false;
    }
}
