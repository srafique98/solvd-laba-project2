package com.solvd.laba.interfaces;

public interface InventoryManageable {
    public void addQuantity(String partName, int amount);
    public void substractQuantity(String partName, int amount);
}
