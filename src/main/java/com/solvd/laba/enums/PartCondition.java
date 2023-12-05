package com.solvd.laba.enums;

public enum PartCondition {
    NEW(0, "New part"),
    USED(1, "Previsiouly used in a vehicle"),
    DAMAGED(2, "Part in poor condition. Should repair!");

    private final int conditionId;
    private final String description;

    PartCondition(int conditionId, String description) {
        this.conditionId = conditionId;
        this.description = description;
    }

    public int getConditionId() {
        return conditionId;
    }

    public String getDescription() {
        return description;
    }

    public static PartCondition getVehicleConditionByConditionId(int conditionId) {
        for (PartCondition condition : PartCondition.values()) {
            if (condition.getConditionId() == conditionId) {
                return condition;
            }
        }
        return null;
    }

    public boolean isCompatibleWithVehicle(VehicleCondition vehicleCondition) {
        switch (this) {
            case NEW:
            case USED:
                return true;
            case DAMAGED:
                return vehicleCondition == VehicleCondition.DAMAGED;
            default:
                return false;
        }
    }
}
