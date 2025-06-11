
package gui;

/**
 *
 * @author Amila_Vishwajith
 */
import models.Booking;
import models.Flight;
import models.User;
import services.BookingService;
import services.UserService;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class BookingForm extends JFrame
{
    public BookingForm(User currentUser, Flight selectedFlight) 
    {
        setTitle("Book Flight - ID: " + selectedFlight.getFlightId());
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Header
        add(Box.createVerticalStrut(10));
        JLabel flightLabel = new JLabel("Booking for Flight ID: " + selectedFlight.getFlightId());
        flightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(flightLabel);
        add(Box.createVerticalStrut(10));

        // Seat class selection
        JPanel seatPanel = new JPanel();
        seatPanel.add(new JLabel("Seat Class:"));
        String[] seatClasses = {"First", "Business", "Economy"};
        JComboBox<String> seatClassCombo = new JComboBox<>(seatClasses);
        seatPanel.add(seatClassCombo);
        add(seatPanel);

        // If Operator: select customer to book for
        JComboBox<User> customerCombo = null;
        if (currentUser.getRole().equals("Operator"))
        {
            JPanel userPanel = new JPanel();
            userPanel.add(new JLabel("Select Customer:"));

            List<User> customers = UserService.getAllCustomers();
            customerCombo = new JComboBox<>(customers.toArray(new User[0]));
            userPanel.add(customerCombo);
            add(userPanel);
        }

        // Confirm booking
        JButton bookButton = new JButton("Confirm Booking");
        bookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(bookButton);

        JLabel messageLabel = new JLabel("");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(messageLabel);

        // Book button action
        JComboBox<User> finalCustomerCombo = customerCombo;
        bookButton.addActionListener(e -> 
        {
            String seatClass = (String) seatClassCombo.getSelectedItem();
            int userId;

            if (currentUser.getRole().equals("Operator")) 
            {
                User selectedCustomer = (User) finalCustomerCombo.getSelectedItem();
                userId = selectedCustomer.getUserId();
            } else {
                userId = currentUser.getUserId();
            }

            Booking booking = new Booking(
                    userId,
                    selectedFlight.getFlightId(),
                    seatClass,
                    Timestamp.valueOf(LocalDateTime.now())
            );

            boolean success = BookingService.register(booking);
            messageLabel.setText(success ? "✅ Booking successful!" : "❌ Booking failed.");
        });

        setVisible(true);
    }
}
