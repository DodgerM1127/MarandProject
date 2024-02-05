package com.example.marandproject.api.dataConversionTool;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;

public class DataConversionTool {
    public static void main(String[] args) {
        List<List<String>> flights = new ArrayList<>();
        Set<String> carriers = new HashSet<>();
        Set<String> airports = new HashSet<>();
        List<String> temp;

        //reads file and stores data
        try (Scanner scanner = new Scanner(new File("src/main/java/com/example/marandproject/api/dataConversionTool/DataSet.txt"))) {

            while (scanner.hasNextLine()) {
                temp = getRecordFromLine(scanner.nextLine());
                addAirports(airports, temp.get(1));
                addAirports(airports, temp.get(2));
                addCarriers(carriers, temp.get(3));
                flights.add(temp);
            }

        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //create jsons
        ArrayList<HashMap> jsonArrayForAirports = createJsonArrayForAirports(airports);
        ArrayList<HashMap> jsonArrayForCarriers = createJsonArrayForCarriers(carriers);
        ArrayList<HashMap> jsonArrayForFlights = createJsonArrayForFlights(flights);

        //jsons to string
        String jsonAirportsString = separatorReplacer(jsonArrayForAirports.toString());
        String jsonCarrierString = separatorReplacer(jsonArrayForCarriers.toString());
        String jsonFlightsString = separatorReplacer(jsonArrayForFlights.toString());


        try {
            // post airports
            URL url = new URL("http://localhost:8080/fbn/airports");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(jsonAirportsString);
            out.flush();
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            out.close();


            //post carriers
            url = new URL("http://localhost:8080/fbn/carriers");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(jsonCarrierString);
            out.flush();
            responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            out.close();

            //post flights
            url = new URL("http://localhost:8080/fbn/flightsByName");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(jsonFlightsString);
            out.flush();
            responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            out.close();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * replaces "=" with ":"
     * */
    private static String separatorReplacer(String text){
        return text.replaceAll("=",":");
    }
    /**
     * creates json for flights
     * */
    private static ArrayList<HashMap> createJsonArrayForFlights(List<List<String>> flights) {
        ArrayList<HashMap> jsonArrayForFlights = new ArrayList<>();
        for (List<String> flight : flights){
            HashMap<String, String> newFlight = new HashMap<>();
            newFlight.put("\"flightNumber\"","\""+flight.get(0)+"\"");
            newFlight.put("\"originAirport\"","\""+flight.get(1)+"\"");
            newFlight.put("\"destinationAirport\"","\""+flight.get(2)+"\"");
            newFlight.put("\"carrier\"","\""+flight.get(3)+"\"");
            newFlight.put("\"price\"",flight.get(4));
            newFlight.put("\"day\"","\""+flight.get(5)+"\"");
            newFlight.put("\"time\"","\""+FormatTime(flight.get(6))+"\"");
            newFlight.put("\"duration\"","\""+FormatTime(flight.get(7))+"\"");
            newFlight.put("\"availableSeats\"",flight.get(8));
            jsonArrayForFlights.add(newFlight);
        }
        return jsonArrayForFlights;
    }

    /**
     * corrects time format from: XXhXXm or XX:XX to: XX-XX-XX
     * */
    private static String FormatTime(String hm){
        int minutes;
        int hours;
        if(hm.charAt(hm.length()-1)=='m'){
            String [] split = hm.split("h");
            if(split.length == 1)
                minutes = Integer.parseInt(split[0].substring(0,2));
            else{
                minutes = Integer.parseInt(split[1].substring(0,2));
                hours = Integer.parseInt(split[0])*60;
                minutes += hours;
            }
        }else {
            String [] split = hm.split(":");
            minutes = Integer.parseInt(split[1].substring(0,2));
            hours = Integer.parseInt(split[0])*60;
            minutes += hours;
        }
        return String.format("%02d-%02d-00", minutes / 60, minutes % 60);
    }

    /**
     * creates json for airports
     * */
    private static ArrayList<HashMap> createJsonArrayForAirports(Set<String> airports) {
        ArrayList<HashMap> jsonArrayForAirports = new ArrayList<>();
        for (String airport : airports){
            HashMap<String, String> airportName = new HashMap<>();
            airportName.put("\"name\"","\""+airport+"\"");
            jsonArrayForAirports.add(airportName);
        }
        return jsonArrayForAirports;
    }

    /**
     * creates json for carriers
     * */
    private static ArrayList<HashMap> createJsonArrayForCarriers(Set<String> carriers) {
        ArrayList<HashMap> jsonArrayForCarriers = new ArrayList<>();
        for (String carrier : carriers){
            HashMap<String, String> carrierName = new HashMap<>();
            carrierName.put("\"name\"","\""+carrier+"\"");
            jsonArrayForCarriers.add(carrierName);
        }
        return jsonArrayForCarriers;
    }

    /**
     * separates string into list of strings with delimiter ^
     * */
    private static List<String> getRecordFromLine(String s) {
        List<String> list1 = List.of(s.split("\\^"));
        return list1;
    }

    /**
     * checks if airport is in the set and adds airport
     * */
    private static void addAirports(Set<String> airports, String airport){
        if(!airports.contains(airport))
            airports.add(airport);
    }

    /**
     * checks if carrier is in the set and adds carrier
     * */
    private static void addCarriers(Set<String> carriers, String carrier){
        if(!carriers.contains(carrier))
            carriers.add(carrier);
    }
}
