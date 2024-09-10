package com.example.dbproject;
public class Film {
    private int filmId;
    private String title;
    private String description;
    private int releaseYear;
    private int languageId;
    private int rentalDuration;
    private double rentalRate;
    private int length;
    private double replacementCost;
    private String rating;
    private String specialFeatures;
    private String lastUpdate;

    public Film(int filmId, String title, String description, int releaseYear, int languageId, int rentalDuration,
                double rentalRate, int length, double replacementCost, String rating, String specialFeatures, String lastUpdate) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
        this.lastUpdate = lastUpdate;
    }

    // Utility method to truncate text to a specified length
    private String truncate(String text, int length) {
        if (text.length() > length) {
            return text.substring(0, length - 3) + "...";
        } else {
            return text;
        }
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-50s %-10d %-10d %-10d %-10.2f %-10d %-15.2f %-5s %-30s %-20s",
                filmId,
                truncate(title, 20),       // Truncate title to 20 characters
                truncate(description, 50), // Truncate description to 50 characters
                releaseYear,
                languageId,
                rentalDuration,
                rentalRate,
                length,
                replacementCost,
                rating,
                specialFeatures,
                lastUpdate);
    }
}

