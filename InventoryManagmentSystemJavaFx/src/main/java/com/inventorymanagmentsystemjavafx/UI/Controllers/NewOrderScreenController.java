package com.inventorymanagmentsystemjavafx.UI.Controllers;

import com.inventorymanagmentsystemjavafx.ApplicationController;
import com.inventorymanagmentsystemjavafx.UI.NewOrderScreen;
import javafx.collections.ObservableList;

import java.sql.*;

public class NewOrderScreenController {



    private NewOrderScreen screen;

    public NewOrderScreenController(NewOrderScreen screen)
    {
        this.screen = screen;

    }




    public boolean ConfirmOrder()
    {




        ResultSet set;
        String commitOrderString = "Insert INTO Orders (OrderID,ItemSKU,count) Value (?,?,?)";
        Connection conn = ApplicationController.INSTANCE.getManager().getConnection();

        try (Statement stmt = conn.createStatement())
        {

            set =  stmt.executeQuery("select MAX(OrderID) from orders");




        int orderId = 0;
        try {

            while (set.next()){
                orderId = set.getInt(1) + 1;
                System.out.println(orderId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try(
            PreparedStatement statement =  ApplicationController.INSTANCE.getManager().getConnection().prepareStatement(commitOrderString))  {


            ObservableList<NewOrderScreen.OrderListItem> orderItems = screen.getItemOrderList();

            ApplicationController.INSTANCE.getManager().getConnection().setAutoCommit(false);
            for (NewOrderScreen.OrderListItem item: orderItems)
            {
                System.out.println("New Statement");
                statement.setInt(1,orderId);
                statement.setInt(2,item.getItem().getSku());
                statement.setInt(3,item.getCount());
                int rowsAffected = statement.executeUpdate();

                ApplicationController.INSTANCE.getManager().getConnection().commit();

            }

            return true;
        } catch (SQLException e) {
            try {
                System.err.println(e.getMessage());
                System.err.println("Transaction is being rolled back");
                ApplicationController.INSTANCE.getManager().getConnection().rollback();
            } catch (SQLException excep) {
                System.err.println(e.getMessage());
            }
            return false;
        }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}


