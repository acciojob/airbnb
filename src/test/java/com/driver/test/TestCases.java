package com.driver.test;

import com.driver.AirBnb;
import com.driver.controllers.HotelManagementController;
import com.driver.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest(classes = AirBnb.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {
    HotelManagementController hotelManagementController = new HotelManagementController();

    @Test
    public void testAddHotel(){
        String result = hotelManagementController.addHotel(getHotel("Tushar",new ArrayList<>()));
        Assertions.assertEquals("SUCCESS",result);
    }
    @Test
    public void testAddHotelWhenNameNull(){
        String result = hotelManagementController.addHotel(getHotel(null,new ArrayList<>()));
        Assertions.assertEquals("FAILURE",result);
    }

    @Test
    public void testAddHotelWhenHotelAlreadyExists(){
        hotelManagementController.addHotel(getHotel("Tushar",new ArrayList<>()));
        String result = hotelManagementController.addHotel(getHotel("Tushar",new ArrayList<>()));
        Assertions.assertEquals("FAILURE",result);
    }

    @Test
    public void testAddUser(){
        int aadharNumber = hotelManagementController.addUser(getUser(1,"Tushar", 22));
        Assertions.assertEquals(1,aadharNumber);
    }

    @Test
    public void testGetHotelWithMaxFacilities(){
        List<Facility> facilities = new ArrayList<>();
        facilities.add(Facility.BAR);
        hotelManagementController.addHotel(getHotel("Tushar",facilities));
        List<Facility> facilities1 = new ArrayList<>();
        facilities1.add(Facility.GYM);
        facilities1.add(Facility.BAR);
        hotelManagementController.addHotel(getHotel("Tushar1",facilities1));
        String hotelName = hotelManagementController.getHotelWithMostFacilities();
        Assertions.assertEquals("Tushar1",hotelName);
    }
    @Test
    public void testGetHotelWithMaxFacilitiesWhereNoFacilities(){
        hotelManagementController.addHotel(getHotel("Tushar",new ArrayList<>(){}));
        String hotelName = hotelManagementController.getHotelWithMostFacilities();
        Assertions.assertEquals("",hotelName);
    }

    @Test
    public void testGetARoom(){
        hotelManagementController.addHotel(getHotel("T",new ArrayList<>()));
        int result = hotelManagementController.bookARoom(getBooking(1,2,"Tushar","T"));
        Assertions.assertEquals(2000,result);
    }

    @Test
    public void testGetARoomWhenRoomsNotAvailable(){
        hotelManagementController.addHotel(getHotel("T",new ArrayList<>()));
        int result = hotelManagementController.bookARoom(getBooking(1,6,"Tushar","T"));
        Assertions.assertEquals(-1, result);
    }

    @Test
    public void testGetBookings(){
        hotelManagementController.addHotel(getHotel("T",new ArrayList<>()));
        hotelManagementController.bookARoom(getBooking(1,1,"Tushar","T"));
        hotelManagementController.bookARoom(getBooking(1,1,"Tushar","T"));
        int result = hotelManagementController.getBookings(1);
        Assertions.assertEquals(2, result);
    }
    @Test
    public void testUpdateFacilities(){
        hotelManagementController.addHotel(getHotel("T",new ArrayList<>()));
        List<Facility> facilities = new ArrayList<>();
        facilities.add(Facility.GYM);
        facilities.add(Facility.BAR);
        Hotel hotel = hotelManagementController.updateFacilities(facilities,"T");
        Assertions.assertEquals(2, hotel.getFacilities().size());
    }

    public Hotel getHotel(String name,List<Facility> facilities){
        return new Hotel(name,5,facilities,1000);
    }
    public User getUser(int aadharNumber, String name, int age) {
        return new User(aadharNumber, name, age);
    }

    public Booking getBooking(int bookingAadharCard,int noOfRooms, String name ,String hotelName){
        return new Booking(bookingAadharCard,noOfRooms,name,hotelName);
    }
}


