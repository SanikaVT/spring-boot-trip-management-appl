package com.tripmanagement.asdc.dao;

import com.tripmanagement.asdc.model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingDAOImpl implements BookingDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	Logger logger = LoggerFactory.getLogger(BookingDAOImpl.class);

	@Override
	public boolean saveRide(Booking booking) {
		try {
			String sql = "insert into booking values(" + null + "," + booking.getCustomer_id() + ",'"
					+ booking.getTimestamp() + "'," + booking.getCost() + "," + booking.getSeats_booked() + ","
					+ booking.getTrip_id() + "," + booking.getIsPaid() + ");";
			jdbcTemplate.update(sql);
			logger.debug("Ride saved successfully");
			return true;
		} catch (Exception e) {
			logger.error("Error while adding Rides", e);
			return false;

		}
	}

	@Override
	public List<Booking> getAllRidesForCustomer(int customer_id) {
		List<Booking> rides = new ArrayList<>();
		try {
			String selectBookingQuery = "select * from booking where customer_id=" + customer_id;
			rides = jdbcTemplate.query(selectBookingQuery,
			BeanPropertyRowMapper.newInstance(Booking.class));
			return rides;
		} catch (Exception e) {
			logger.error("Error while getting rides for customer", e);
			return rides;

		}
	}

	@Override
	public boolean updateIsPaid(int customer_id) {
		try{
			String sql = "update booking set isPaid=1 where customer_id="+customer_id;
			jdbcTemplate.update(sql);
			return true;
			}
			catch(Exception e)
			{
				logger.error("Error updating isPaid",e);
				return false;
	
			}
	}

	

}
