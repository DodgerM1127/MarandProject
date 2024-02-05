package com.example.marandproject.api.client;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FbnApp extends JFrame {
    private JPanel fbn;
    private JList flights;
    private JTextField originAirport;
    private JTextField destinationAirport;
    private JTextField carrier;
    private JButton findFlightButton;

    public FbnApp() {


        String response = getAirports();
        String[] airportList = jsonListStringToStringArray(response).toArray(new String[0]);
        response = getCarriers();
        String[] carrierList = jsonListStringToStringArray(response).toArray(new String[0]);
        response = getFlights();
        String[] flightList = flightListStringToStringArray(response).toArray(new String[0]);

        setContentPane(fbn);
        setTitle("Fly by night App");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        DefaultListModel<flightLine> flightLines = new DefaultListModel<>();
        flights.setModel(flightLines);
        findFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = getFlights();
                String[] flightList = flightListStringToStringArray(response).toArray(new String[0]);
                flightLines.removeAllElements();
                String originA = originAirport.getText();
                String destinationA = destinationAirport.getText();
                String carrierC = carrier.getText();

                for(int i = 0; i< flightList.length; i+=9){
                    flightLines.addElement(new flightLine(
                            flightList[i],
                            flightList[i+1],
                            flightList[i+2],
                            flightList[i+3],
                            flightList[i+4],
                            flightList[i+5],
                            flightList[i+6],
                            flightList[i+7],
                            flightList[i+8]
                    ));
                }
                //textFields shorten flightLines
                if(originA.length() == 3){
                    for (int i =0; i< flightLines.getSize();i++) {
                        if (!flightLines.get(i).getOriginAirport().equals(originA)) {
                            flightLines.remove(i);
                            i--;
                        }
                    }
                }
                if(destinationA.length() == 3){
                    for (int i =0; i< flightLines.getSize();i++) {
                        if (!flightLines.get(i).getDestinationAirport().equals(destinationA))
                        {
                            flightLines.remove(i);
                            i--;
                        }
                    }
                }
                if(carrierC.length() > 3){
                    for (int i =0; i< flightLines.getSize();i++) {
                        if (!flightLines.get(i).getCarrier().equals(carrierC))
                        {
                            flightLines.remove(i);
                            i--;
                        }
                    }
                }
            }
        });
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedItem = flights.getSelectedValue().toString();
                    String flightNumber = selectedItem.replaceAll(" +", " ").split(" ")[1];
                    String serverAnswer = decreaseSeats(flightNumber);
                    JOptionPane.showMessageDialog(
                            null,
                            serverAnswer.equals("Good") ?
                                    "Reserved one seat on a flight: " + flightNumber :
                                    "On flight: "+ flightNumber + " " + serverAnswer
                    );
                    findFlightButton.doClick();
                }
            }
        };
        flights.addMouseListener(mouseListener);
        findFlightButton.doClick();
    }


    public static void main(String[] args) {
        FbnApp myApp = new FbnApp();
    }

    private static class flightLine{
        String flightName;
        String originAirport;
        String destinationAirport;
        String carrier;
        String price;
        String day;
        String time;
        String duration;
        String availableSeats;

        public flightLine(String flightName, String originAirport, String destinationAirport, String carrier, String price, String day, String time, String duration, String availableSeats) {
            this.flightName = flightName;
            this.originAirport = originAirport;
            this.destinationAirport = destinationAirport;
            this.carrier = carrier;
            this.price = price;
            this.day = day;
            this.time = time;
            this.duration = duration;
            this.availableSeats = availableSeats;
        }

        public String getFlightName() {
            return flightName;
        }

        public String getOriginAirport() {
            return originAirport;
        }

        public String getDestinationAirport() {
            return destinationAirport;
        }

        public String getCarrier() {
            return carrier;
        }

        public String getPrice() {
            return price;
        }

        public String getDay() {
            return day;
        }

        public String getTime() {
            return time;
        }

        public String getDuration() {
            return duration;
        }

        public String getAvailableSeats() {
            return availableSeats;
        }

        @Override
        public String toString() {
            return String.format("Flight: %7s  From-To: %4s-%4s  Carrier: %15s  Price: %6s  day and time: %3s %s  flight duration: %s  Seats left available: %s",
                    this.getFlightName(),
                    this.getOriginAirport(),
                    this.getDestinationAirport(),
                    this.getCarrier(),
                    this.getPrice(),
                    this.getDay(),
                    this.getTime(),
                    this.getDuration(),
                    this.getAvailableSeats()
            );
        }
    }
    private static ArrayList<String> jsonListStringToStringArray(String response){
        String[] partialJsons = response.substring(1,response.length()-1).split(",");
        ArrayList<String> jsons= new ArrayList<>();
        for (int i=0; i<partialJsons.length; i++){
            String[] x = new String[0];
            if(i%2==1) {
                x = partialJsons[i].split("\"");
                jsons.add(x[3]);
            }
        }
        return jsons;
    }

    private static ArrayList<String> flightListStringToStringArray(String response){
        String[] partialJsons = response.substring(1,response.length()-1).split(",");
        ArrayList<String> jsons= new ArrayList<>();
        for (int i=0; i<partialJsons.length; i++){
            String[] x = new String[0];
            if((i%2==0)&i%12<7) {
                x = partialJsons[i].split("\"");
                jsons.add(x[3]);
            }else if(i%12==7){
                x = partialJsons[i].split("\"");
                jsons.add(x[2].substring(1));
            }else if(i%12==11){
                x = partialJsons[i].split("\"");
                jsons.add(x[2].substring(1,x[2].length()-1));
            }else if(i%12>7){
                x = partialJsons[i].split("\"");
                jsons.add(x[3]);
            }
        }
        return jsons;
    }

    private String getCarriers() {
        String response;
        try {
            String requestURL = "http://localhost:8080/fbn/carrier";
            URL wikiRequest = new URL(requestURL);
            URLConnection connection = wikiRequest.openConnection();
            connection.setDoOutput(true);
            Scanner scanner = new Scanner(wikiRequest.openStream());
            response = scanner.useDelimiter("\\Z").next();
            scanner.close();
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return response;
    }

    private String getFlights() {
        String response;
        try {
            String requestURL = "http://localhost:8080/fbn/flight";
            URL wikiRequest = new URL(requestURL);
            URLConnection connection = wikiRequest.openConnection();
            connection.setDoOutput(true);
            Scanner scanner = new Scanner(wikiRequest.openStream());
            response = scanner.useDelimiter("\\Z").next();
            scanner.close();
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return response;
    }

    private String getAirports() {
        String response;
        try {
            String requestURL = "http://localhost:8080/fbn/airport";
            URL wikiRequest = new URL(requestURL);
            URLConnection connection = wikiRequest.openConnection();
            connection.setDoOutput(true);
            Scanner scanner = new Scanner(wikiRequest.openStream());
            response = scanner.useDelimiter("\\Z").next();
            scanner.close();
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return response;
    }

    private String decreaseSeats(String flightNumber) {
        String response;
        try {
            String requestURL = "http://localhost:8080/fbn/flight/seats/" + flightNumber;
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            Scanner scanner = new Scanner(connection.getInputStream());
            response = scanner.useDelimiter("\\Z").next();
            scanner.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return response;
    }

}
