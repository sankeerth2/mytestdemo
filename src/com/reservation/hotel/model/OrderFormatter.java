package com.reservation.hotel.model;

import java.text.ParseException;
import java.util.Map;

import com.reservation.hotel.registration.OrderInput;
import com.reservation.hotel.registration.OrderInput.UserProfile;

public class OrderFormatter {


    private CustomerType currentCustomerType;
    private Map<String, String> listDates;
    public String[] stayDates;

    OrderInput orderInput = new OrderInput();
    OrderInput.UserProfile userProfile = orderInput.new UserProfile();

    public void inputFormatter(String input) {
        String[] inputs = input.split(":");
        int i = 0;
        //		for(int i=0; i<=inputs.length;i++){
        userProfile.setCustomerType(CustomerType.getType(inputs[i++]));
        stayDates = inputs[i].split(",");
        for (String string : stayDates) {
            System.out.println(string);
        }
        DayCalculatorService DCS = new DayCalculatorService();

        try {
            userProfile.setListDates(DCS.dayCalculator(stayDates));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        orderInput.setUserProfile(userProfile);
    }


    public void outputFormatter(HotelType leastPriceHotel) {
        System.out.println(leastPriceHotel);
    }

    public String[] getStayDates() {

        return stayDates;

    }
}
