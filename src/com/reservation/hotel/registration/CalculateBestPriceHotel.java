package com.reservation.hotel.registration;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.reservation.hotel.model.CustomerType;
import com.reservation.hotel.model.DayType;
import com.reservation.hotel.model.HotelRates;
import com.reservation.hotel.model.HotelType;
import com.reservation.hotel.model.OrderFormatter;

public class CalculateBestPriceHotel implements ICalculateBestPriceHotel{
	
	private CustomerType currentCustomerType;
	Map<String, String> listDate = new HashMap<>();
	String[] stayDates;
	OrderInput orderInput;
	
	Map<HotelType, Map<CustomerType, Map<DayType, HotelRates>>> prices;
	Map<HotelType, Integer> totalIndividualPrice = new HashMap<>();
	private int currentHotelRating;
	private int competitorHotelRating;

	public void bestPrice(OrderFormatter orderFormatter, OrderInput orderInput){
		this.orderInput= orderInput;
		OrderInput.UserProfile userProfile = orderInput.getUserProfile();
		currentCustomerType = userProfile.getCustomerType();
		listDate = userProfile.getListDates();
		stayDates = orderFormatter.getStayDates();
		prices = orderInput.getCurrentPrices();
		for (HotelType hotelType : HotelType.values()) {
			totalIndividualPrice.put(hotelType, individualHotelPrice(hotelType, orderFormatter));
		}
		
		
		orderFormatter.outputFormatter(calMinPriceHotel(orderFormatter));
	}
	
	public HotelType calMinPriceHotel(OrderFormatter orderFormatter) {
		Map<HotelType, Integer> min = new HashMap<>();
		Entry<HotelType, Integer> minEntry = (Entry<HotelType, Integer>) min.entrySet();
		HotelType minEntryHotel = minEntry.getKey();
		currentHotelRating = minEntryHotel.getRating();
		
		for(Entry<HotelType, Integer> entry :totalIndividualPrice.entrySet())
			if (min == null || ((Entry<HotelType, Integer>) min).getValue() > entry.getValue()) {
//		        
				min = (Map<HotelType, Integer>) entry;
			}
//		   else{
//				if(((Entry<HotelType, Integer>) min).getValue() == entry.getValue()){	
//	    		HotelType entryHotel = entry.getKey().
//
//	        	competitorHotelRating= entryHotel.getRating();
//			if(currentHotelRating < competitorHotelRating)
//			min = (Map<HotelType, Integer>) entry;
//	        }
//	        
//				}
		    return minEntryHotel;
		
	}
	
	public int individualHotelPrice(HotelType hotelType, OrderFormatter orderFormatter) {
		int totalHotelPrice=0;
		stayDates = orderFormatter.getStayDates();
		for(String date: stayDates){
			if(listDate.containsKey(date)){
				String day = listDate.get(date);
				if(day=="Monday"||day=="Tuesday"||day=="Wednesday"||day=="Thrusday"||day=="Friday")
				{
					DayType currentDayType = DayType.WEEKDAY;
					totalHotelPrice+=dayCostCalculation(hotelType, currentDayType);
				}
				else {
					DayType currentDayType = DayType.WEEKEND;
					totalHotelPrice+=dayCostCalculation(hotelType, currentDayType);
				}
				}
				
			}
		return totalHotelPrice;
		}

	public int dayCostCalculation(HotelType hotelType, DayType currentDayType) {
		Map<CustomerType, Map<DayType, HotelRates>> hotel = prices.get(hotelType);
		Map<DayType, HotelRates> customerType = hotel.get(currentCustomerType);
		HotelRates hotelRates = customerType.get(currentDayType);
		return hotelRates.Price;
	}	
	}

