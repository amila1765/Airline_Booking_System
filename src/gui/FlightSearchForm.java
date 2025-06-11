
package gui;

/**
 *
 * @author Amila_Vishwajith
 */
import models.Flight;
import services.FlightService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FlightSearchForm extends JFrame
{
    private JComboBox<String> originCombo;
    private JComboBox<String> destinationCombo;
    private JTable flightTable;
    private DefaultTableModel tableModel;

    public FlightSearchForm() 
    {
        setTitle("Search Flights");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        originCombo = new JComboBox<>(new String[]{"1 - Colombo", "2 - London", "3 - Dubai"});
        destinationCombo = new JComboBox<>(new String[]{"1 - Colombo", "2 - London", "3 - Dubai"});

        JButton searchButton = new JButton("Search Flights");

        tableModel = new DefaultTableModel(new String[]{"Flight ID", "Airplane ID", "Dep Time", "Arr Time"}, 0);
        flightTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(flightTable);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("From:"));
        topPanel.add(originCombo);
        topPanel.add(new JLabel("To:"));
        topPanel.add(destinationCombo);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        searchButton.addActionListener(e -> searchFlights());

        setVisible(true);
    }

    private void searchFlights() 
    {
        tableModel.setRowCount(0); // Clear previous results

        int originId = originCombo.getSelectedIndex() + 1;
        int destId = destinationCombo.getSelectedIndex() + 1;

        if (originId == destId) 
        {
            JOptionPane.showMessageDialog(this, "Origin and destination cannot be the same.");
            return;
        }

        List<Flight> flights = FlightService.getFlightsByRoute(originId, destId);

        if (flights.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "No flights found for the selected route.");
        } 
        else 
        {
            for (Flight flight : flights) 
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
}
