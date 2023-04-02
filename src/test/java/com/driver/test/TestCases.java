package com.driver.test;

import com.driver.EaseMyTrip;
import com.driver.controllers.AirportController;
import com.driver.model.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest(classes = EaseMyTrip.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {

    @Autowired
    AirportController airportController;

    @BeforeEach
    public void setup(){
        airportController.addAirport(new Airport("IGI",2,City.DELHI));
        airportController.addAirport(new Airport("CA",3,City.CHANDIGARH));
        airportController.addAirport(new Airport("KIA",4,City.BANGLORE));
        airportController.addPassenger(new Passenger(1,"e1","p1",12));
        airportController.addPassenger(new Passenger(2,"e2","p2",15));
        airportController.addFlight(new Flight(1,City.CHANDIGARH,City.BANGLORE,100,new Date(2023,3,27),2.5));
        airportController.addFlight(new Flight(2,City.DELHI,City.BANGLORE,100,new Date(2023,3,27),2.0));
        airportController.bookATicket(1,1);
        airportController.bookATicket(2,2);
    }
    @Test
    @Order(1)
    public void testAddAirport(){
        String result = airportController.addAirport(new Airport("IGI2",2,City.DELHI));
        Assertions.assertEquals("SUCCESS",result);
    }

    @Test
    @Order(2)
    public void testGetLargestAirportName(){
        String airportName = airportController.getLargestAirportName();

        Assertions.assertEquals("KIA",airportName);
    }

    @Test
    @Order(3)
    public void testGetLargestAirportNameInCaseOfSameNumberOfTerminals(){
        airportController.addAirport(new Airport("BIA",4,City.BANGLORE));
        String airportName = airportController.getLargestAirportName();
        Assertions.assertEquals("BIA",airportName);
    }
    @Test
    @Order(4)
    public void testGetShortestDurationOfPossibleBetweenTwoCities(){
        double duration = airportController.getShortestDurationOfPossibleBetweenTwoCities(City.CHANDIGARH,City.BANGLORE);
        Assertions.assertEquals(2.5,duration);

    }
    @Test
    @Order(5)
    public void testGetShortestDurationOfPossibleBetweenTwoCitiesWhenFightDontExist(){
        double duration = airportController.getShortestDurationOfPossibleBetweenTwoCities(City.CHENNAI,City.BANGLORE);
        Assertions.assertEquals(-1,duration);

    }
    @Test
    @Order(6)
    public void testGetNumberOfPeopleOn(){
        int numberOfPeopleOn = airportController.getNumberOfPeopleOn(new Date(2023,3,27),"KIA");
        Assertions.assertEquals(2,numberOfPeopleOn);
    }
    @Test
    @Order(7)
    public void testGetNumberOfPeopleOnWithNoFlight(){
        int numberOfPeopleOn = airportController.getNumberOfPeopleOn(new Date(2023,3,27),"A");
        Assertions.assertEquals(0,numberOfPeopleOn);
    }
    @Test
    @Order(8)
    public void testCalculateFlightFare(){
        int flightFare = airportController.calculateFlightFare(1);
        Assertions.assertEquals(3050,flightFare);
    }
    @Test
    @Order(9)
    public void testCalculateRevenueOfAFlight(){
        int revenue = airportController.calculateRevenueOfAFlight(1);
        Assertions.assertEquals(3050, revenue);
    }

    @Test
    @Order(10)
    public void testCalculateRevenueOfAFlight2(){
        int revenue = airportController.calculateRevenueOfAFlight(1);
        Assertions.assertEquals(3050, revenue);
    }
    @Test
    @Order(11)
    public void testBookAFlight(){
        String result = airportController.bookATicket(1,2);
        Assertions.assertEquals("SUCCESS", result);
    }
    @Test
    @Order(12)
    public void testBookAFlightWhenPassengerAlreadyThere(){
        String result = airportController.bookATicket(1,1);
        Assertions.assertEquals("FAILURE", result);
    }

    @Test
    @Order(13)
    public void testCancelATicket(){
        String result = airportController.cancelATicket(1,1);
        Assertions.assertEquals("SUCCESS", result);

    }
    @Test
    @Order(14)
    public void testCancelATicketWhenFlightDoesntExist(){
        String result = airportController.cancelATicket(5,1);
        Assertions.assertEquals("FAILURE", result);

    }
    @Test
    @Order(15)
    public void testCancelATicketWhenTicketDoesntExist(){
        String result = airportController.cancelATicket(2,1);
        Assertions.assertEquals("FAILURE", result);
    }
    @Test
    @Order(16)
    public void testAddFlight(){
        String result = airportController.addFlight(new Flight(3,City.DELHI,City.BANGLORE,100,new Date(2023,3,27),2.0));
        Assertions.assertEquals("SUCCESS", result);

    }
    @Test
    @Order(17)
    public void testGetAirportNameFromFlightId(){
        String airportName = airportController.getAirportNameFromFlightId(1);
        Assertions.assertEquals("CA", airportName);
    }
    @Test
    @Order(18)
    public void testGetAirportNameFromFlightIdWhenAirportDoesntExist(){
        String airportName = airportController.getAirportNameFromFlightId(4);
        Assertions.assertEquals(null, airportName);
    }

    @Test
    @Order(19)
    public void testAddPassenger(){
        String result = airportController.addPassenger(new Passenger(3,"e3","p3",15));
        Assertions.assertEquals("SUCCESS", result);

    }
    @Test
    @Order(20)
    public void testCountOfBookingsDoneByPassengerAllCombined(){
        airportController.bookATicket(2,1);
        int count = airportController.countOfBookingsDoneByPassengerAllCombined(1);
        Assertions.assertEquals(2, count);
    }

}

