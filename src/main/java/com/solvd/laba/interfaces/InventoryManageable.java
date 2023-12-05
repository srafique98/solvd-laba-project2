package com.solvd.laba.interfaces;

import com.solvd.laba.serviceManagement.Part;

public interface InventoryManageable {
    public void addQuantity(String partName, int amount);
    public void substractQuantity(String partName, int amount);
}
