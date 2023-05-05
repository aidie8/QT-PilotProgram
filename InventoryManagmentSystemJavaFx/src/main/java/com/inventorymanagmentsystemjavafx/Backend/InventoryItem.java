package com.inventorymanagmentsystemjavafx.Backend;

public class InventoryItem {

    private final int sku;
    private final String name;
    private final String description;
    private final int costPerUnit;

    public InventoryItem(int sku, String name, String description, int costPerUnit)
    {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.costPerUnit = costPerUnit;
    }

    public int getCostPerUnit() {
        return costPerUnit;
    }

    public String getDescription() {
        return description;
    }



    public String getName() {
        return name;
    }



    public int getSku() {
        return sku;
    }


    public String getUIString()
    {
        return "Name: %s    Description : %s    SKU: %d    Price : %d".formatted(name,description,sku,costPerUnit);
    }
}
