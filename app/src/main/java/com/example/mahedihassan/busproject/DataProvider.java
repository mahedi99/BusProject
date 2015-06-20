package com.example.mahedihassan.busproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hemel007 on 5/14/2015.
 */
public class DataProvider {

    public static HashMap<String, List<String>> getInfo()
    {
        HashMap<String, List<String>> citiesList = new HashMap<String, List<String>>();

        List<String> Dhaka_City = new ArrayList<String>();
        List<String> Chittagong_City = new ArrayList<String>();
        List<String> Rajshahi_City = new ArrayList<String>();
        List<String> Khulna_City = new ArrayList<String>();
        List<String> Sylhet_City = new ArrayList<String>();
        List<String> Barisal_City = new ArrayList<String>();
        List<String> Rangpur_City = new ArrayList<String>();

        Dhaka_City.add("Saudia(Chittagong)");
        Dhaka_City.add("ScaniaMen(Chittagong)");
        Dhaka_City.add("NationalTravels(Rajshahi)");
        Dhaka_City.add("GreenLine(Rajshahi)");
        Dhaka_City.add("GreenLine(Khulna)");
        Dhaka_City.add("Volvo(Sylhet)");
        Dhaka_City.add("Shakura(Barisal)");
        Dhaka_City.add("TRTravelss(Rangpur)");

        Chittagong_City.add("ScaniaMen(Dhaka)");

        Rajshahi_City.add("GreenLine(Dhaka)");

        Khulna_City.add("GreenLine(Dhaka)");

        Sylhet_City.add("Volvo(Dhaka)");

        Barisal_City.add("Shakura(Dhaka)");

        Rangpur_City.add("TRTravelss(Dhaka)");



        citiesList.put("Dhaka City",Dhaka_City);
        citiesList.put("Chittagong City", Chittagong_City);
        citiesList.put("Rajshahi City", Rajshahi_City);
        citiesList.put("Khulna City", Khulna_City);
        citiesList.put("Sylhet City", Khulna_City);
        citiesList.put("Barisal City", Barisal_City);
        citiesList.put("Rangpur City",Rajshahi_City);


        return citiesList;

    }

}
