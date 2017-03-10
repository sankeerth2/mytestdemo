package com.reservation.hotel.registration;

import com.reservation.hotel.model.DayType;
import com.reservation.hotel.model.HotelType;
import com.reservation.hotel.model.OrderFormatter;

public interface ICalculateBestPriceHotel {
	
	void bestPrice(OrderFormatter orderFormatter, OrderInput orderInput);
	
	HotelType calMinPriceHotel(OrderFormatter orderFormatter);
	
	int individualHotelPrice(HotelType hotelType, OrderFormatter orderFormatter);
	
	int dayCostCalculation(HotelType hotelType, DayType currentDayType);
	
}
