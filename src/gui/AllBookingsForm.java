
package gui;

/**
 *
 * @author Amila_Vishwajith
 */

import models.Booking;
import services.BookingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AllBookingsForm extends JFrame 
{

    private JTable bookingTable;
    private DefaultTableModel tableModel;

    public AllBookingsForm() {
        setTitle("All Bookings (Admin View)");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table columns
        String[] columns = {"Booking ID", "User ID", "Flight ID", "Seat Class", "Booking Date"};
        tableModel = new DefaultTableModel(columns, 0);
        bookingTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookingTable);
        add(scrollPane, BorderLayout.CENTER);

        loadBookings();
        setVisible(true);
    }

    private void loadBookings() 
    {
        tableModel.setRowCount(0); // clear table
        List<Booking> bookings = BookingService.getAllBookings();

        for (Booking b : bookings) 
        {
            tableModel.addRow(new Object[]
            {
                    b.getBookingId(),
                    b.getUserId(),
                    b.getFlightId(),
                    b.getSeatClass(),
                    b.getBookingDate()
            });
        }
    }
}


