package assignment.pkg7;

import java.awt.Dimension; 
import java.awt.FlowLayout; 
import java.awt.GridLayout; 
import java.util.Enumeration; 
import javax.swing.*; 

public class ReviewEntryFrame extends JPanel {
    
    // UI components for the review entry frame

    private final JTextField restaurantTF; // TextField for restaurant name
    private final JTextField reviewerTF; // TextField for reviewer name
    private final JTextField reviewTF; // TextField for review text
    private final JRadioButton rating1; // RadioButton for rating 1
    private final JRadioButton rating2; // RadioButton for rating 2
    private final JRadioButton rating3; // RadioButton for rating 3
    private final JRadioButton rating4; // RadioButton for rating 4
    private final JRadioButton rating5; // RadioButton for rating 5
    private final ButtonGroup group; // Group to manage the selection of radio buttons
    private final JButton saveButton; // Button to save the review
    private final JButton cancelButton; // Button to cancel the review entry
    
    public ReviewEntryFrame() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Set layout to vertical box layout
        
        // Panel for restaurant input
        JPanel restaurantPanel = new JPanel();
        restaurantPanel.setMaximumSize(new Dimension(800, 40));
        restaurantPanel.setPreferredSize(new Dimension(800, 40));
        restaurantPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        
        JLabel restaurantLabel = new JLabel("Restaurant:"); 
        restaurantLabel.setPreferredSize(new Dimension(70, 30)); 
        restaurantTF = new JTextField(); 
        restaurantTF.setPreferredSize(new Dimension(500, 30)); 
        
        restaurantPanel.add(restaurantLabel); // Add label to panel
        restaurantPanel.add(restaurantTF); // Add text field to panel
        
        // Panel for reviewer input
        JPanel reviewerPanel = new JPanel();
        reviewerPanel.setMaximumSize(new Dimension(800, 40));
        reviewerPanel.setPreferredSize(new Dimension(800, 40));
        reviewerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JLabel reviewerLabel = new JLabel("Reviewer:"); 
        reviewerLabel.setPreferredSize(new Dimension(70, 30)); 
        reviewerTF = new JTextField(); 
        reviewerTF.setPreferredSize(new Dimension(500, 30)); 
        
        reviewerPanel.add(reviewerLabel); // Add label to panel
        reviewerPanel.add(reviewerTF); // Add text field to panel
        
        // Panel for review input
        JPanel reviewPanel = new JPanel();
        reviewPanel.setMaximumSize(new Dimension(800, 40));
        reviewPanel.setPreferredSize(new Dimension(800, 40));
        reviewPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JLabel reviewLabel = new JLabel("Review:"); 
        reviewLabel.setPreferredSize(new Dimension(70, 30)); 
        reviewTF = new JTextField(); 
        reviewTF.setPreferredSize(new Dimension(500, 30)); 
        
        reviewPanel.add(reviewLabel); // Add label to panel
        reviewPanel.add(reviewTF); // Add text field to panel
        
        // Panel for rating selection
        JPanel ratingPanel = new JPanel();
        ratingPanel.setMaximumSize(new Dimension(800, 40));
        ratingPanel.setPreferredSize(new Dimension(800, 40));
        ratingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JLabel ratingLabel = new JLabel("Rating:"); 
        ratingLabel.setPreferredSize(new Dimension(70, 30)); 
        
        ratingPanel.add(ratingLabel); // Add label to rating panel
        
        // Panel for radio buttons (ratings)
        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setMaximumSize(new Dimension(800, 50));
        radioButtonPanel.setPreferredSize(new Dimension(500, 30));
        radioButtonPanel.setLayout(new GridLayout(1, 5)); 
        group = new ButtonGroup(); 
        
        // Create radio buttons for ratings
        rating1 = new JRadioButton("1");
        rating2 = new JRadioButton("2");
        rating3 = new JRadioButton("3");
        rating4 = new JRadioButton("4");
        rating5 = new JRadioButton("5");
        
        // Add radio buttons to the button group
        group.add(rating1);
        group.add(rating2);
        group.add(rating3);
        group.add(rating4);
        group.add(rating5);
        
        // Add radio buttons to the panel
        radioButtonPanel.add(rating1);
        radioButtonPanel.add(rating2);
        radioButtonPanel.add(rating3);
        radioButtonPanel.add(rating4);
        radioButtonPanel.add(rating5);
        
        ratingPanel.add(radioButtonPanel); // Add radio button panel to rating panel
        
        // Panel for action buttons (Save and Cancel)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setMaximumSize(new Dimension(800, 30));
        buttonPanel.setPreferredSize(new Dimension(800, 30));
        buttonPanel.setLayout(new GridLayout(1, 2)); 
        
        saveButton = new JButton("Save"); // Create Save button
        cancelButton = new JButton("Cancel"); // Create Cancel button
        
        buttonPanel.add(saveButton); // Add Save button to panel
        buttonPanel.add(cancelButton); // Add Cancel button to panel

        // Add all panels to the main panel
        add(restaurantPanel);
        add(reviewerPanel);
        add(reviewPanel);
        add(ratingPanel);
        add(buttonPanel);
    }
    
    // Getter for Save button
    public JButton getSaveButton() {
        return saveButton;
    }
    
    // Getter for Cancel button
    public JButton getCancelButton() {
        return cancelButton;
    }
    
    // Method to create a new RestaurantReview object from user inputs
    public RestaurantReview getNewReview() {
        String restaurant = restaurantTF.getText(); // Get restaurant name
        String reviewer = reviewerTF.getText(); // Get reviewer name
        String review = reviewTF.getText(); // Get review text
        int rating = 0; // Initialize rating
        
        // Iterate through radio buttons to find the selected rating
        Enumeration<AbstractButton> buttons = group.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) { // Check if the button is selected
                rating = Integer.parseInt(button.getText()); // Parse rating value
                break; // Exit the loop if selected
            }
        }

        // Return a new RestaurantReview object
        return new RestaurantReview(restaurant, reviewer, rating, review);
    }
    
    // Method to check if all required fields are filled
    public boolean isFieldsValid() {
        // Check if any fields are empty or if no rating is selected
        return !(restaurantTF.getText().isEmpty() || reviewerTF.getText().isEmpty() || 
                 reviewTF.getText().isEmpty() || group.getSelection() == null);
    }
    
    // Method to clear all input fields
    public void clearFields() {
        restaurantTF.setText(""); // Clear restaurant field
        reviewerTF.setText(""); // Clear reviewer field
        reviewTF.setText(""); // Clear review field
        group.clearSelection(); // Deselect all radio buttons
    }
}
