
package gui;

/**
 *
 * @author Amila_Vishwajith
 */
import models.Booking;
import models.Flight;
import models.User;
import services.BookingService;
import services.FlightService;
import services.UserService;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class BookingForm extends JFrame
{
    private final User currentUser;
    private final List<Flight> selectedFlights; // Can be 1 (direct) or 2 (transit)
    private JComboBox<User> customerCombo;
    private JComboBox<String> seatClassCombo;

    public BookingForm(User currentUser, List<Flight> selectedFlights)
    {
        this.currentUser = currentUser;
        this.selectedFlights = selectedFlights; 
    
        setTitle(selectedFlights.size() == 1 ? "Book Direct Flight" : "Book Transit Flights");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Confirm Booking (" + (selectedFlights.size() == 1 ? "Direct" : "Transit") + ")");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(titleLabel);
        
        // Show flight IDs
        for (Flight flight : selectedFlights) 
        {
            JLabel flightLabel = new JLabel("Flight ID: " + flight.getFlightId() +
                    " | From: " + flight.getDepartureAirportId() + " ➔ To: " + flight.getArrivalAirportId());
            flightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(flightLabel);
        }

        // Seat class selection
        JPanel seatPanel = new JPanel();
        seatPanel.add(new JLabel("Seat Class:"));
        seatClassCombo = new JComboBox<>(new String[]{"First", "Business", "Economy"});
        seatPanel.add(seatClassCombo);
        add(seatPanel);

        // If Operator: select customer for booking
        
        if (currentUser.getRole().equals("Operator"))
        {
            JPanel userPanel = new JPanel();
            userPanel.add(new JLabel("Select Customer:"));
            List<User> customers = UserService.getAllCustomers();
            customerCombo = new JComboBox<>(customers.toArray(new User[0]));
            userPanel.add(customerCombo);
            add(userPanel);
        }

        // booking button
        JButton bookBtn = new JButton("Confirm Booking");
        JLabel resultLabel = new JLabel();
        bookBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(bookBtn);
        add(Box.createVerticalStrut(5));
        add(resultLabel);

        // Book button action
       bookBtn.addActionListener(e -> 
       {
            int userId = currentUser.getUserId();
            if (currentUser.getRole().equals("Operator")) 
            {
                User selected = (User) customerCombo.getSelectedItem();
                if (selected == null) 
                {
                    JOptionPane.showMessageDialog(this, "Please select a customer.");
                    return;
                }
                userId = selected.getUserId();
            }

            String seatClass = (String) seatClassCombo.getSelectedItem();
            Timestamp bookingDate = Timestamp.valueOf(LocalDateTime.now());

            boolean allSuccess = true;

            for (Flight flight : selectedFlights) 
            {
                Booking booking = new Booking(userId, flight.getFlightId(), seatClass, bookingDate);
                boolean success = BookingService.register(booking);
                if (!success) allSuccess = false;
            }

            resultLabel.setText(allSuccess ? "✅ Booking successful!" : "❌ Booking failed for one or more flights.");
        });

        setVisible(true);
    }
}
