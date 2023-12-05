package com.solvd.laba;

import com.solvd.laba.interfaces.InventoryManageable;
import com.solvd.laba.serviceManagement.Part;

import java.util.*;
import java.io.*;
public class Inventory implements InventoryManageable {
    private Map<String, Part> parts; //String --> our unique identifier

    public Inventory() {
        this.parts = new HashMap<>();
    }
    public void addPart(Part part) {
        if (parts.containsKey(part.getName())) {
            System.out.println(part.getName() + " already exists.");
            return;
        }
        parts.put(part.getName(), part);
        System.out.println(part.getName() + " successfully added.");
    }

    public void addPart(String partName, double price, int quantity) {
        if (quantity <= 0 || price <= 0 ) {
            System.out.println("Invalid quantity: Quantity must be a positive integer.");
            return;
        }
        if (parts.containsKey(partName)) {
            System.out.println(partName + " already exists.");
            return;
        }
        Part newPart = new Part(partName, price);
        newPart.setQuantity(quantity);
        parts.put(partName, newPart);
        System.out.println(partName + " successfully added.");
    }

    public Part removePart(String partName) {
        if (!parts.containsKey(partName)) {
            System.out.println(partName + " does not exist.");
            return null;
        }
        Part removedPart = parts.remove(partName);
        System.out.println(partName + " successfully removed.");
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
            System.out.println(partName + " does not exist.");
            return;
        }
        Part part = parts.get(partName);
        part.setQuantity(newCount);
        System.out.println(partName + " updated to " + newCount);
    }

    public void checkAmount(String partName) {
        if (!parts.containsKey(partName)) {
            System.out.println(partName + " does not exist.");
            return;
        }
        Part part = parts.get(partName);
        int stock = part.getQuantity();
        if (stock < 3) {
            System.out.println("Warning: " + partName + " has low stock: " + stock);
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
        int totalQuantity = 0;
        for (Part part : parts.values()) {
            totalQuantity += part.getQuantity();
        }
        return totalQuantity;
    }

    public void updateInventory(String partName, int quantity) {
        if (!parts.containsKey(partName)) {
            return;
        }
        Part part = parts.get(partName);
        part.setQuantity(quantity);
    }

    public void removeFromInventory(String partName) {
        if (!parts.containsKey(partName)) {
            return;
        }
        parts.remove(partName);
    }

    @Override
    public void addQuantity(String partName, int amount){
        if (!parts.containsKey(partName)) {
            return;
        }
        Part part = parts.get(partName);
        part.addQuantity(amount);
    }
    @Override
    public void substractQuantity(String partName, int amount){
        if (!parts.containsKey(partName)) {
            return;
        }
        Part part = parts.get(partName);
        part.substractQuantity(amount);
    }
}
