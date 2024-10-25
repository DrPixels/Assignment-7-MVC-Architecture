package assignment.pkg7;

import java.util.ArrayList; 
import java.util.Collections; 
import java.util.Comparator;
import java.util.Observable; 

public class RestaurantReviewModel extends Observable {
    // List to hold restaurant reviews
    private ArrayList<RestaurantReview> reviews = new ArrayList<>();

    // Method to add a new review to the list
    public void addReview(RestaurantReview review) {
        reviews.add(review); // Add the review to the list
        setChanged(); // Mark the observable as changed
        notifyObservers(); // Notify observers that the data has changed
    }

    // Method to retrieve the list of reviews
    public ArrayList<RestaurantReview> getReviews() {
        // Sort the list based on rating in descending order
        Collections.sort(reviews, new Comparator<RestaurantReview>() {
            @Override
            public int compare(RestaurantReview r1, RestaurantReview r2) {
                return Integer.compare(r2.getRating(), r1.getRating()); // Compare ratings for descending order
            }
        });
        
        return reviews; // Return the sorted list of reviews
    }

    // Method to remove a review by its unique ID
    public void removeReview(String id) {
        // Iterate through the list of reviews
        for (RestaurantReview review : reviews) {
            // Check if the current review's ID matches the provided ID
            if (review.getId().equals(id)) {
                reviews.remove(review); // Remove the matching review
                break; 
            }
        }
        
        setChanged(); // Mark the observable as changed
        notifyObservers(); // Notify observers that the data has changed
    }
}
