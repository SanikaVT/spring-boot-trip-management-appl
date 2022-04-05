package com.tripmanagement.asdc.dao;

import com.tripmanagement.asdc.model.FuelEconomy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*Class contains methods specific to database operations on fueleconomy table*/
@Repository
public class FuelEconomyDAOImpl implements FuelEconomyDAO {


    @Autowired
    JdbcTemplate jdbcTemplate;

	Logger logger = LoggerFactory.getLogger(FuelEconomyDAOImpl.class);

    //This method is used to insert fuelEconomy object into the database
	@Override
	public boolean saveFuelEconomy(FuelEconomy fuel_economy) {

		if(fuel_economy==null)
		return false;
		try{
			int trip_id = fuel_economy.getTrip_id();
			float kms_travelled = fuel_economy.getKms_travelled();
			float fuel_consumed = fuel_economy.getFuel_consumed();
			String innerSubQuery = trip_id + "," + kms_travelled + "," + fuel_consumed;
			String subQuery1 = "" + null + "," + innerSubQuery + ",";
			float fuel_economy1 = fuel_economy.getFuel_economy();
			String timestamp = fuel_economy.getTimestamp();
			int vehicle_id = fuel_economy.getVehicle_id();
			String subQuery2 = fuel_economy1 + ",'" + timestamp + "'," + vehicle_id + ");";
			String sql = "insert into fuel_economy values(" + subQuery1 + subQuery2;
			jdbcTemplate.update(sql);
				return true;
		}
			catch(Exception e)
			{
				logger.error("Error saving fuel_economy",e);
				return false;
	
			}
	}


}










