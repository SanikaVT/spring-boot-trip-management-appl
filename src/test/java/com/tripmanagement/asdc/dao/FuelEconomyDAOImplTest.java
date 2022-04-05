package com.tripmanagement.asdc.dao;

import com.tripmanagement.asdc.model.FuelEconomy;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
class FuelEconomyDAOImplTest {

    @Autowired
    FuelEconomyDAO fuelEconomyDAO;

    @Test
    void testsaveFuelEconomyCorrect() {
        FuelEconomy fuelEconomy = new FuelEconomy();
        fuelEconomy.setTrip_history_id(1);
        fuelEconomy.setTrip_id(0);
        fuelEconomy.setKms_travelled(9);
        fuelEconomy.setFuel_economy(99);
        fuelEconomy.setTimestamp("2022-03-29");
        fuelEconomy.setVehicle_id(1);
        assertTrue(fuelEconomyDAO.saveFuelEconomy(fuelEconomy));
    }

    @Test
    void testsaveFuelEconomyEmpty() {
//        FuelEconomy fuelEconomy = new FuelEconomy();
//        fuelEconomy.setTimestamp("");
        assertFalse(fuelEconomyDAO.saveFuelEconomy(null));
    }
}