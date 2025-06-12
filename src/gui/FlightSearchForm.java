
package gui;

/**
 *
 * @author Amila_Vishwajith
 */
import models.Flight;
import models.User;
import services.FlightService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FlightSearchForm extends JFrame
{
    private User currentUser;
    private List<Flight> flightList; // to store search results
    private JComboBox<String> originCombo;
    private JComboBox<String> destinationCombo;
    private JTable flightTable;
    private DefaultTableModel tableModel;

    public FlightSearchForm(User user) 
    {
        this.currentUser = user;
        this.flightList = new ArrayList<>();
        setTitle("Search Flights");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
         // Top panel for dropdowns
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        originCombo = new JComboBox<>(new String[]{"1 - Colombo", "2 - London", "3 - Dubai"});
        destinationCombo = new JComboBox<>(new String[]{"1 - Colombo", "2 - London", "3 - Dubai"});
        JButton searchButton = new JButton("Search Flights");

        tableModel = new DefaultTableModel(new String[]{"Flight ID", "Airplane ID", "Dep Time", "Arr Time"}, 0);
        flightTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(flightTable);

        topPanel.add(new JLabel("From:"));
        topPanel.add(originCombo);
        topPanel.add(new JLabel("To:"));
        topPanel.add(destinationCombo);
        topPanel.add(searchButton);
        add(topPanel, BorderLayout.NORTH);
        
         // Table for showing flights
        String[] columnNames = {"Flight ID", "Airplane ID", "Departure Time", "Arrival Time"};
        tableModel = new DefaultTableModel(columnNames, 0);
        flightTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(flightTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // Bottom panel for Book button
        JPanel bottomPanel = new JPanel();
        JButton bookButton = new JButton("Book Selected Flight");
        bottomPanel.add(bookButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Button actions
        searchButton.addActionListener(e -> searchFlights());
        bookButton.addActionListener(e -> openBookingForm());
      
        setVisible(true);
    }

    private void searchFlights() 
    {
        tableModel.setRowCount(0); // Clear previous results
        flightList.clear();
        
        int originId = originCombo.getSelectedIndex() + 1;
        int destinationId = destinationCombo.getSelectedIndex() + 1;

        if (originId == destinationId) 
        {
            JOptionPane.showMessageDialog(this, "Origin and destination cannot be the same.");
            return;
        }

        flightList = FlightService.getFlightsByRoute(originId, destinationId);

        if (flights.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "No flights found for the selected route.");
        } 
        else 
        {
            for (Flight flight : flightList) 
            {
                tableModel.addRow(new Object[]
                {
                        flight.getFlightId(),
                        flight.getAirplaneId(),
                        flight.getDepartureTime(),
                        flight.getArrivalTime()
                });
            }
        }
    }
    
    private void openBookingForm() 
    {
        int selectedRow = flightTable.getSelectedRow();
        if (selectedRow >= 0) {
            Flight selectedFlight = flightList.get(selectedRow);
            new BookingForm(currentUser, selectedFlight);
        }
        else 
        {
            JOptionPane.showMessageDialog(this, "Please select a flight to book.");
        }
    }
}
