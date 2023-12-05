package com.solvd.laba.enums;

public enum Status {
    SCHEDULED(0),
    IN_PROGRESS(1),
    COMPLETED(2),
    CANCELLED(3);

    private final int statusId;

    Status(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getStatusDescription() {
        switch (this) {
            case SCHEDULED:
                return "Service is SCHEDULED";
            case IN_PROGRESS:
                return "Service is in progress";
            case COMPLETED:
                return "Service has been completed";
            case CANCELLED:
                return "Service has been cancelled";
            default:
                return "Unknown service status";
        }
    }
}
