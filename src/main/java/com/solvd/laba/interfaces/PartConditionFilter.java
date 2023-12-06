package com.solvd.laba.interfaces;

import com.solvd.laba.storage.Part;

@FunctionalInterface
public interface PartConditionFilter<T extends Part> {
    boolean test(T part);
}
