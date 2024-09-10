package com.example.dbproject;


public class Actor {
    private int actorId;
    private String firstName;
    private String lastName;
    private String lastUpdate;

    public Actor(int actorId, String firstName, String lastName, String lastUpdate) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-15s %-15s %-20s", actorId, firstName, lastName, lastUpdate);
    }
}

