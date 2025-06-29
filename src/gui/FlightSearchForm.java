
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
    private JTable flightTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> originCombo;
    private JComboBox<String> destinationCombo;

    public FlightSearchForm(User user) 
    {
        this.currentUser = user;
        this.flightList = new ArrayList<>();
        
        setTitle("Search Flights");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
         // Top panel for dropdowns and search
        JPanel topPanel = new JPanel(new FlowLayout());
        
        originCombo = new JComboBox<>(new String[]{"1 - Colombo", "2 - London", "3 - Dubai"});
        destinationCombo = new JComboBox<>(new String[]{"1 - Colombo", "2 - London", "3 - Dubai"});
        JButton searchButton = new JButton("🔍 Search Flights");

        topPanel.add(new JLabel("From:"));
        topPanel.add(originCombo);
        topPanel.add(new JLabel("To:"));
        topPanel.add(destinationCombo);
        topPanel.add(searchButton);
        add(topPanel, BorderLayout.NORTH);
        
         // Table and scroll pane
        tableModel = new DefaultTableModel(new String[]
        {
            "Flight ID(s)", "Airplane ID(s)", "Departure Time", "Arrival Time", "Route Type"
        }, 0);
        flightTable = new JTable(tableModel);
        
        //Allow multiple rows selection
        flightTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
         JScrollPane scrollPane = new JScrollPane(flightTable);
        add(scrollPane, BorderLayout.CENTER);
        
        //Book Button
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
        
        // Direct flights
        List<Flight> directFlights = FlightService.getFlightsByRoute(originId, destinationId);
        for (Flight f : directFlights)
        {
            tableModel.addRow(new Object[]
            {
                f.getFlightId(),
                f.getAirplaneId(),
                f.getDepartureTime(),
                f.getArrivalTime(),
                "Direct"
            });
            flightList.add(f);
        }

        // Transit flights
        List<List<Flight>> transitRoutes = FlightService.getTransitFlights(originId, destinationId);
        for (List<Flight> route : transitRoutes) 
        {
            if (route.size() == 2)
            {
                Flight first = route.get(0);
                Flight second = route.get(1);
                String flightIds = first.getFlightId() + " -> " + second.getFlightId();
                String airplaneIds = first.getAirplaneId() + " -> " + second.getAirplaneId();

                tableModel.addRow(new Object[]{
                    flightIds,
                    airplaneIds,
                    first.getDepartureTime(),
                    second.getArrivalTime(),
                    "Transit"
                });

                //store both flights for booking
                flightList.add(first);
                flightList.add(second);
            }
        }

        if (flightList.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "❌ No flights found.");
        }
    }

    private void openBookingForm() 
    {
        int[] selectedRows = flightTable.getSelectedRows(); //Multiple rows
        if (selectedRows.length == 0) 
        {
            JOptionPane.showMessageDialog(this, "Please select at least one flight to book.");
            return;
        }

        List<Flight> selectedFlights = new ArrayList<>();
        for (int row : selectedRows)
        {
            if (row < flightList.size())
            {
                selectedFlights.add(flightList.get(row));
            }
        }

        new BookingForm(currentUser, selectedFlights); //Pass List<Flight>
    }
}