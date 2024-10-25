package assignment.pkg7;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controller extends JFrame {

    private MainFrame mainFrame;          // Main frame to display reviews
    private ReviewEntryFrame reviewEntryFrame; // Frame for entering new reviews
    private RestaurantReviewModel model;  // Model to manage restaurant reviews
    private CardLayout cardLayout;        // Layout manager for switching between frames

    public Controller() {
        // Set up the initial frame properties
        setSize(800, 400);
        setTitle("Restaurant Reviewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Initialize the CardLayout
        cardLayout = new CardLayout();
        setLayout(cardLayout); // Set the layout of the Controller frame

        // Initialize the model and frames
        model = new RestaurantReviewModel();
        mainFrame = new MainFrame(model);
        reviewEntryFrame = new ReviewEntryFrame();
        
        // Add the main frame as an observer of the model
        model.addObserver(mainFrame);

        // Add both frames to the CardLayout
        add(mainFrame, "MainFrame");             // Key: "MainFrame"
        add(reviewEntryFrame, "ReviewEntryFrame"); // Key: "ReviewEntryFrame"

        // Action listener for the "Add" button in the main frame
        mainFrame.getAddButton().addActionListener(e -> {
            // Switch to the Review Entry frame when the Add button is clicked
            cardLayout.show(getContentPane(), "ReviewEntryFrame");
            setSize(600, 200); // Adjust size for the entry frame
            setTitle("Review Entry"); // Update the title
        });

        // Action listener for the "Save" button in the review entry frame
        reviewEntryFrame.getSaveButton().addActionListener(e -> {
            // Validate the fields before saving the review
            if (reviewEntryFrame.isFieldsValid()) {
                // Add the new review to the model
                model.addReview(reviewEntryFrame.getNewReview());
                reviewEntryFrame.clearFields();    // Clear the input fields after saving

                // Show a success message dialog
                JOptionPane.showMessageDialog(this, 
                    "Review added successfully.",     
                    "New Review Added",              
                    JOptionPane.INFORMATION_MESSAGE); 
               
                // Switch back to the main frame after saving
                cardLayout.show(getContentPane(), "MainFrame");
                setSize(800, 400);  // Reset size for the main frame
                setTitle("Restaurant Reviewer"); // Update the title
            } else {
                // Show a warning message if required fields are missing
                JOptionPane.showMessageDialog(this, 
                    "Please answer all required fields.",
                    "Missing Field",                       
                    JOptionPane.WARNING_MESSAGE);       
            }
        });

        // Action listener for the "Cancel" button in the review entry frame
        reviewEntryFrame.getCancelButton().addActionListener(e -> {
            reviewEntryFrame.clearFields();  // Clear fields on cancel
            // Switch back to the main frame without saving
            cardLayout.show(getContentPane(), "MainFrame");
            setSize(800, 400);  // Reset size for the main frame
            setTitle("Restaurant Reviewer");  // Update the title
        });
    }
}