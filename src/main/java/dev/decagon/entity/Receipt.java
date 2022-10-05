package dev.decagon.entity;

import java.util.UUID;

public class Receipt {
    private UUID Id;
    private String customerName;
    private String itemName;
    private int numberOfItem;
    private double unitCost;
    private double totalCost;
    private String message;


    public Receipt() {
    }

    public Receipt(String customerName, String itemName, int numberOfItem, double unitCost,double totalCost, String message) {
        this.customerName = customerName;
        this.itemName = itemName;
        this.numberOfItem = numberOfItem;
        this.totalCost = totalCost;
        this.message = message;
        this.unitCost=unitCost;
        this.Id= UUID.randomUUID();
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getNumberOfItem() {
        return numberOfItem;
    }

    public void setNumberOfItem(int numberOfItem) {
        this.numberOfItem = numberOfItem;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Receipt{\n" +
                "customerName: " + customerName +"\n" +
                "itemName: " + itemName  +"\n"+
                "numberOfItem: " + numberOfItem +"\n" +
                "totalCost: " + totalCost +"\n"+
                message +"\n" +
                '}';
    }
}
