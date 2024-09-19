package com.example.dbproject;

public class Ingredient {
    private int ingredientId;
    private String name;
    private double price;
    private boolean vegan;
    private boolean vegetarian;

    public Ingredient(int ingredientId, String name, double price, boolean vegan, boolean vegetarian) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.price = price;
        this.vegan = vegan;
        this.vegetarian = vegetarian;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-7.2f %-5b %-5b", ingredientId, name, price, vegan, vegetarian);
    }
}
