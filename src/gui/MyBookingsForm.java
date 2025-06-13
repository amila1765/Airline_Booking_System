
package gui;

/**
 *
 * @author Amila_Vishwajith
 */

import models.Booking;
import models.User;
import services.BookingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MyBookingsForm extends JFrame 
{

    public MyBookingsForm(User user) 
    {
        setTitle("My Bookings");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columns = {"Booking ID", "Flight ID", "Seat Class", "Booking Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        List<Booking> bookings = BookingService.getBookingsByUser(user.getUserId());

        for (Booking b : bookings) 
        {
            model.addRow(new Object[]
            {
                    b.getBookingId(),
                    b.getFlightId(),
                    b.getSeatClass(),
                    b.getBookingDate().toString()
            });
        }

        setVisible(true);
    }
}


