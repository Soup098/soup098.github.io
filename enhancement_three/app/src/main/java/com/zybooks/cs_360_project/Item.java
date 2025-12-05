package com.zybooks.cs_360_project;

public class Item {
    private String name;
    private int quantity;

    private int id;

    public Item(int id, String name, int quantity){
        this.name = name;
        this.quantity = quantity;
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getId() {return id;}

    public void setId(int Id) {this.id = id;}

}
