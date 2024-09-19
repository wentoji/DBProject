package com.example.dbproject;

public class PizzaIngredient {
    private int pizzaId;
    private int ingredientId;

    public PizzaIngredient(int pizzaId, int ingredientId) {
        this.pizzaId = pizzaId;
        this.ingredientId = ingredientId;
    }

    @Override
    public String toString() {
        return String.format("Pizza ID: %d, Ingredient ID: %d", pizzaId, ingredientId);
    }
}
