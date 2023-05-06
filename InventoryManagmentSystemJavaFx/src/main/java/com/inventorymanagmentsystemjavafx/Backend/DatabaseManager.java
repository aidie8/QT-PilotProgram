package com.inventorymanagmentsystemjavafx.Backend;

import javafx.scene.chart.PieChart;

import java.sql.*;

public class DatabaseManager {
    Connection conn = null;

    Inventory inv;


    public Inventory GetInventory()
    {
        return inv;
    }


    public DatabaseManager()
    {
        inv = new Inventory();
    }
    public boolean Connect(String username,String password)
    {
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/mydb?" +
                            "user=%s&password=%s".formatted(username,password));
            if (!conn.isClosed()) return true;
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public void loadInventory()
    {
        String statement = "Select inventory.sku, inventory.count, items.price, items.`Item name`,items.description from inventory,items where inventory.SKU = items.SKU";
        try (Statement stmt = conn.createStatement())
        {
            ResultSet rs = stmt.executeQuery(statement);
            while(rs.next())
            {
                int sku = rs.getInt("sku");
                String name = rs.getString("Item Name");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                int count = rs.getInt("count");
                InventoryItem item = new InventoryItem(sku,name,description,price);
                inv.addItemToInventory(item,count);
                System.out.println("Added item : " + item.getName() + "With count of " + count);

            }


        }catch (SQLException e)
        {
            System.out.println(e.getMessage());

        }
    }


    public Connection getConnection()
    {
        return conn;

    }


    public boolean sendInsertQuery(String statement)
    {
        try (Statement stmt = conn.createStatement())
        {
            int rowsAffected = stmt.executeUpdate(statement);
            return rowsAffected > 0;

    }catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
