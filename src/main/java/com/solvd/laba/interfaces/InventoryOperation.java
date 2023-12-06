package com.solvd.laba.interfaces;

import com.solvd.laba.storage.Part;

@FunctionalInterface
public interface InventoryOperation<T extends Part> {
    void operate(T part, int quantity);
}
