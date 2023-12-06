package com.solvd.laba.interfaces;

import com.solvd.laba.storage.Inventory;

public interface InventoryChecker <T> {
    boolean check(Inventory inventory, T part);
}
