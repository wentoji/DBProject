package com.example.dbproject;

public class PizzaPrice {
    private int pizzaId;
    private String name;
    private String ingredients;
    private double price;

    public PizzaPrice(int pizzaId, String name, String ingredients, double price) {
        this.pizzaId = pizzaId;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-20s %-7.2f", pizzaId, name, ingredients, price);
    }
}
