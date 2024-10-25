package assignment.pkg7;

import java.util.UUID; // Class to generate unique IDs

public class RestaurantReview {

    // Attributes for the restaurant review
    private String id; // Unique identifier for the review
    private String restaurant; // Name of the restaurant
    private String reviewer; // Name of the reviewer
    private int rating; // Rating given by the reviewer
    private String review; // Text of the review

    // Constructor to initialize a new restaurant review
    public RestaurantReview(String restaurant, String reviewer, int rating, String review) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID for the review
        this.restaurant = restaurant; // Set the restaurant name
        this.reviewer = reviewer; // Set the reviewer's name
        this.rating = rating; // Set the rating
        this.review = review; // Set the review text
    }

    // Getter method to retrieve the unique ID of the review
    public String getId() {
        return id;
    }

    // Getter method to retrieve the restaurant name
    public String getRestaurant() {
        return restaurant;
    }

    // Getter method to retrieve the reviewer's name
    public String getReviewer() {
        return reviewer;
    }

    // Getter method to retrieve the rating
    public int getRating() {
        return rating;
    }

    // Getter method to retrieve the review text
    public String getReview() {
        return review;
    }
}
