package com.solvd.laba.storage;

import com.solvd.laba.enums.PartCondition;
import com.solvd.laba.interfaces.InventoryChecker;
import com.solvd.laba.interfaces.InventoryManageable;
import com.solvd.laba.interfaces.InventoryOperation;
import com.solvd.laba.interfaces.PartConditionFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class Inventory implements InventoryManageable {
    private static final Logger LOGGER = LogManager.getLogger(Inventory.class);
    private Map<String, Part> parts; //String --> our unique identifier

    public Inventory() {
        this.parts = new HashMap<>();
    }

    public void addPart(String partName, double price, int quantity) {
        if (quantity <= 0 || price <= 0 ) {
            LOGGER.error("Invalid quantity: Quantity must be a positive integer.");
            return;
        }
        if (parts.containsKey(partName)) {
            LOGGER.info(partName + " already exists.");
            return;
        }
        Part newPart = new Part(partName, price);
        newPart.setQuantity(quantity);
        parts.put(partName, newPart);
        LOGGER.info(partName + " successfully added.");
    }

    public Part removePart(String partName) {
        if (!parts.containsKey(partName)) {
            LOGGER.info(partName + " does not exist.");
            return null;
        }
        Part removedPart = parts.remove(partName);
        LOGGER.info(partName + " successfully removed.");
        return removedPart;
    }
    public Part getPart(String partName) {
        if (!parts.containsKey(partName)) {
            return null;
        }
        return parts.get(partName);
    }
    public Map<String, Part> getAllParts() {
        return parts;
    }
    public void updateInventoryCount(String partName, int newCount) {
        if (!parts.containsKey(partName)) {
            LOGGER.warn(partName + " does not exist.");
            return;
        }
        Part part = parts.get(partName);
        part.setQuantity(newCount);
        LOGGER.info(partName + " updated to " + newCount);
    }

    public void removeDamagedParts() {
        List<Part> damagedParts = parts.values().stream()
                .filter(damagedPartsFilter::test)
                .collect(Collectors.toList());

        damagedParts.forEach(part -> {
            subtractQuantityOperation.operate(part, part.getQuantity());
            LOGGER.info("Removed damaged part: " + part.getName());
        });

        LOGGER.info("Removed all damaged parts from inventory.");
    }

    public void checkAmount(String partName) {
        if (!parts.containsKey(partName)) {
            LOGGER.warn(partName + " does not exist.");
            return;
        }
        Part part = parts.get(partName);
        int stock = part.getQuantity();
        if (stock < 3) {
            LOGGER.warn("Warning: " + partName + " has low stock: " + stock);
        }
    }

    public int getPartQuantity(String partName) {
        if (!parts.containsKey(partName)) {
            return 0;
        }
        Part part = parts.get(partName);
        return part.getQuantity();
    }

    public int getTotalCount() {
        return parts.values().stream()
                .mapToInt(Part::getQuantity)
                .sum();
    }

    @Override
    public void addQuantity(String partName, int amount) {
        if (!parts.containsKey(partName)) {
            return;
        }
        Part part = parts.get(partName);
        addQuantityOperation.operate(part, amount);
    }
    @Override
    public void substractQuantity(String partName, int amount){
        if (!parts.containsKey(partName)) {
            return;
        }
        Part part = parts.get(partName);
        part.subtractQuantity(amount);
    }

    public void printLowStockPartsWithInventoryCheck(int threshold) {
        getLowStockParts(threshold)
                .stream()
                .forEach(part -> {
                    if (hasPart.check(this, part.getName())) {
                        LOGGER.info("Low stock part: " + part.getName() + " (" + part.getQuantity() + " remaining)");
                    } else {
                        LOGGER.warn("Unexpected error: Part not found in inventory: " + part.getName());
                    }
                });
    }

    public List<Part> getLowStockParts(int threshold) {
        return parts.values().stream()
                .filter(part -> part.getQuantity() < threshold)
                .collect(Collectors.toList());
    }

    public InventoryChecker<String> hasPart = (inventory, partName) -> inventory.parts.containsKey(partName);
    PartConditionFilter<Part> damagedPartsFilter = part -> part.getCondition() == PartCondition.DAMAGED;
    InventoryOperation<Part> addQuantityOperation = (part, quantity) -> part.addQuantity(quantity);
    InventoryOperation<Part> subtractQuantityOperation = (part, quantity) -> part.subtractQuantity(quantity);


}

