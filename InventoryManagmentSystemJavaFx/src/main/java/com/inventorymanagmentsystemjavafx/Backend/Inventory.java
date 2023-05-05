package com.inventorymanagmentsystemjavafx.Backend;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Inventory {


    ObservableMap<InventoryItem,Integer> itemStock;



    public Inventory()
    {
        itemStock = FXCollections.observableHashMap();

    }

    public ObservableMap<InventoryItem,Integer> getItemStock()
    {
        return itemStock;

    }


    public void addItemToInventory(InventoryItem item,int count)
    {
        itemStock.put(item,count);
    }
    
}
