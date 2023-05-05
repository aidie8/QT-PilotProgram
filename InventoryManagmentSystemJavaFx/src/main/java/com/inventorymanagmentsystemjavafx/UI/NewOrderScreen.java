package com.inventorymanagmentsystemjavafx.UI;

import com.inventorymanagmentsystemjavafx.ApplicationController;
import com.inventorymanagmentsystemjavafx.Backend.InventoryItem;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.Callback;
import java.util.Optional;

public class NewOrderScreen implements Screen {


    GridPane pane;


    ListView<InventoryItem> inventoryListView;

    ListView<OrderListItem> orderListView;

    ObservableList<OrderListItem> itemOrderList;


    ObservableList<InventoryItem> inventoryStockItems;
    public NewOrderScreen()
    {

        pane = new GridPane();
        inventoryListView = new ListView<>();
        inventoryStockItems = FXCollections.observableArrayList();
        inventoryStockItems.addAll(getStockInventory().keySet());
        getStockInventory().addListener((MapChangeListener<InventoryItem,Integer>) change ->
        {
            inventoryStockItems.remove(change.getKey());
            if (change.wasAdded())
            {
                inventoryStockItems.add(change.getKey());
            }
        });
        inventoryListView.setItems(inventoryStockItems);
        orderListView = new ListView<>();
        itemOrderList = FXCollections.observableArrayList(item -> new Observable[]
        {
                item.count
        });
        orderListView.setItems(itemOrderList);
        pane.add(inventoryListView,1,1);
        pane.add(orderListView,3,1);
        ColumnConstraints firstColumn = new ColumnConstraints();
        firstColumn.setPercentWidth(10);
        firstColumn.setHgrow(Priority.ALWAYS);
        pane.getColumnConstraints().add(firstColumn);
        ColumnConstraints listColumn = new ColumnConstraints();
        listColumn.setMinWidth(200);
        listColumn.setMaxWidth(800);
        listColumn.setPercentWidth(40);

        pane.getColumnConstraints().add(listColumn);
        pane.getColumnConstraints().add(new ColumnConstraints(50));
        pane.getColumnConstraints().add(listColumn);
        pane.getColumnConstraints().add(firstColumn);

        RowConstraints listRow = new RowConstraints(400);
        listRow.setMinHeight(100);
        listRow.setMaxHeight(1000);
        listRow.setVgrow(Priority.ALWAYS);
        pane.getRowConstraints().add(new RowConstraints(100));
        pane.getRowConstraints().add(listRow);
        pane.getRowConstraints().add(new RowConstraints(50));

        inventoryListView.setCellFactory(new Callback<>() {


            @Override
            public ListCell<InventoryItem> call(ListView<InventoryItem> param) {
                final ListCell<InventoryItem> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(InventoryItem item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {
                            setText(item.getUIString() + "\nStock Level: " + getStockInventory().get(item));
                        } else {
                            setText(null);
                        }
                    }


                };

                cell.setOnMouseClicked(e -> {
                    if (!(e.getButton() == MouseButton.PRIMARY)) {
                        return;
                    }
                    if (cell.getIndex() < getStockInventory().size()) {
                        InventoryItem item = inventoryStockItems.get(cell.getIndex());
                        Optional<OrderListItem> stream = itemOrderList.stream().filter(orderListItem -> orderListItem.item.getSku() == item.getSku()).findFirst();
                        if (stream.isPresent()) {
                            if (stream.get().count.getValue() + 1 <= getStockInventory().get(item)) {
                                stream.get().addItemQuantityToOrder(1);
                                System.out.println("Updated Value in order");
                            } else {
                                System.out.println("Order Amount is greater than what is in stock");

                            }

                        } else {
                            itemOrderList.add(new OrderListItem(item));


                        }
                    }
                });

                return cell;
            }
        });



        orderListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<OrderListItem> call(ListView<OrderListItem> param) {

                return new ListCell<>() {
                    @Override
                    protected void updateItem(OrderListItem item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {
                            setText(item.getItem().getName()+ " :     " + item.count.getValue());
                        } else {
                            setText(null);
                        }
                    }


                };
            }
        });
    }


    private ObservableMap<InventoryItem,Integer> getStockInventory()
    {
        return ApplicationController.INSTANCE.getManager().GetInventory().getItemStock();
    }




    @Override
    public Parent getPane() {
        return pane;
    }



    private static class OrderListItem implements Observable {
        private final InventoryItem item;
        private final IntegerProperty count = new SimpleIntegerProperty();




        public OrderListItem(InventoryItem item) {
           this.item = item;
           count.set(1);

        }

        public void addItemQuantityToOrder(int count)
        {
            this.count.set(this.count.getValue() + count);

        }

        public void removeItemQuantityFromOrder(int count)
        {
            this.count.set(this.count.get() - count);
            if(this.count.getValue() < 0)this.count.setValue(0);
        }

        public InventoryItem getItem()
        {
            return item;
        }



        @Override
        public void addListener(InvalidationListener listener) {

        }

        @Override
        public void removeListener(InvalidationListener listener) {

        }


    }
}
