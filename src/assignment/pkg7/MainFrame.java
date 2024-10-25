package assignment.pkg7;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainFrame extends JPanel implements Observer {
    
    // Model to hold the restaurant reviews
    RestaurantReviewModel model;

    // UI Components
    JButton addButton;
    JPanel reviewsPanel; // Panel to display the reviews
    
    JPanel noReviewsPanel = new JPanel(); // Panel to show when there are no reviews
    JLabel noReviewsLabel = new JLabel("No reviews available.", SwingConstants.CENTER); // Label for no reviews message

    public MainFrame(RestaurantReviewModel model) {
        this.model = model; // Initialize the model
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Set layout for the main panel
        
        // Create a panel for the headers of the review columns
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(1, 5)); // Use a grid layout with one row and five columns
        labelPanel.setMaximumSize(new Dimension(760, 30)); // Set the maximum size of the panel

        // Create header labels for the review fields with borders and padding
        JLabel restaurantLabel = new JLabel("Restaurant", SwingConstants.CENTER);
        restaurantLabel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10) 
        ));

        JLabel reviewerLabel = new JLabel("Reviewer", SwingConstants.CENTER);
        reviewerLabel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel reviewLabel = new JLabel("Review", SwingConstants.CENTER);
        reviewLabel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel ratingLabel = new JLabel("Rating", SwingConstants.CENTER);
        ratingLabel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10) 
        ));

        JLabel actionLabel = new JLabel("Action", SwingConstants.CENTER);
        actionLabel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10) 
        ));

        // Add header labels to the label panel
        labelPanel.add(restaurantLabel);
        labelPanel.add(reviewerLabel);
        labelPanel.add(reviewLabel);
        labelPanel.add(ratingLabel);
        labelPanel.add(actionLabel);

        // Create a panel to hold the reviews
        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS)); // Use vertical box layout

        // Create a scroll pane to contain the reviews panel
        JScrollPane reviewScrollPane = new JScrollPane(reviewsPanel);
        reviewScrollPane.setBorder(null); 
        reviewScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Create the Add button
        addButton = new JButton("Add");
        addButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); 
        addButton.setAlignmentX(CENTER_ALIGNMENT); 

        // Configure the no reviews panel
        noReviewsLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 0), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10) 
        ));
        noReviewsPanel.add(noReviewsLabel); // Add label to no reviews panel

        // Add components to the main panel
        add(labelPanel); // Add header panel
        add(noReviewsPanel); // Add no reviews panel
        add(reviewScrollPane); // Add reviews scroll pane
        add(addButton); // Add Add button
    }

    // Getter for the Add button
    public JButton getAddButton() {
        return addButton;
    }

    // Method called when the model updates (as part of the Observer pattern)
    @Override
    public void update(Observable o, Object arg) {
        refreshReviews(); // Refresh the reviews displayed
        revalidate(); // Revalidate layout after updates
        repaint(); // Repaint the component to reflect changes
    }

    // Method to refresh the displayed reviews
    public void refreshReviews() {
        reviewsPanel.removeAll(); // Clear the existing reviews from the panel

        // Check if there are any reviews in the model
        if (model.getReviews().isEmpty()) {
            noReviewsPanel.setVisible(true); // Show no reviews panel
        } else {
            noReviewsPanel.setVisible(false); // Hide no reviews panel
            
            // Iterate through each review and create a display panel
            for (RestaurantReview review : model.getReviews()) {
                
                JPanel reviewPanel = new JPanel();
                reviewPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK), 
                        BorderFactory.createEmptyBorder(5, 0, 5, 0) 
                ));

                reviewPanel.setMaximumSize(new Dimension(760, 50)); 
                reviewPanel.setLayout(new GridLayout(1, 5)); 

                // Create labels for each review field with appropriate padding
                JLabel newRestaurant = new JLabel(review.getRestaurant());
                newRestaurant.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

                JLabel newReviewer = new JLabel(review.getReviewer());
                newReviewer.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

                JLabel newReview = new JLabel(review.getReview());
                newReview.setMaximumSize(new Dimension(28, 50));
                newReview.setPreferredSize(new Dimension(28, 50));
                newReview.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

                JLabel newRating = new JLabel(String.valueOf(review.getRating()), SwingConstants.RIGHT);
                newRating.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 15));

                // Create a button to remove the review
                JButton removeButton = new JButton("Remove");
                removeButton.addActionListener(e -> {
                    int response = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this review?", // Confirmation message
                    "Confirm Action", // Title of the dialog
                    JOptionPane.YES_NO_OPTION); // Options for the dialog

                    // If confirmed, remove the review from the model
                    if (response == JOptionPane.YES_OPTION) {
                        model.removeReview(review.getId());
                    }
                });

                // Add the labels and button to the review panel
                reviewPanel.add(newRestaurant);
                reviewPanel.add(newReviewer);
                reviewPanel.add(newReview);
                reviewPanel.add(newRating);
                reviewPanel.add(removeButton);

                reviewsPanel.add(reviewPanel); // Add the review panel to the reviews panel
            }
        }
    }
}
