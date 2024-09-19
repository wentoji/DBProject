package com.example.dbproject;

public class Pizza {
    private int pizzaId;
    private String name;
    private boolean vegan;
    private boolean vegetarian;
    private double totalPrice; // Assuming you want to include totalPrice directly

    public Pizza(int pizzaId, String name, boolean vegan, boolean vegetarian, double totalPrice) {
        this.pizzaId = pizzaId;
        this.name = name;
        this.vegan = vegan;
        this.vegetarian = vegetarian;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-5b %-5b %-10.2f", pizzaId, name, vegan, vegetarian, totalPrice);
    }
}
