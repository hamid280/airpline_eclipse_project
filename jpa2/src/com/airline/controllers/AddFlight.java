package com.airline.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.FlightDestinations;
import com.airline.service.FlightService;

/**
 * Servlet implementation class AddFlight
 */
@WebServlet("/AddFlight")
public class AddFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	@EJB
	FlightService fs;

	public AddFlight() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Flight f = new Flight();
		
		String from_destination = request.getParameter("from_destination");
		f.setFlightOrigin(FlightDestinations.valueOf(from_destination));
		
		String to_destination = request.getParameter("to_destination");
		f.setFlightDestination(FlightDestinations.valueOf(to_destination));

		String price = request.getParameter("price");
		f.setPrice(Integer.parseInt(price));;

		Calendar cal = Calendar.getInstance();
		
		Integer year = Integer.parseInt(request.getParameter("year"));
		cal.set(Calendar.YEAR, year);
		
		Integer month = Integer.parseInt(request.getParameter("month"));
		cal.set(Calendar.MONTH, month);
		
		Integer day = Integer.parseInt(request.getParameter("day"));
		cal.set(Calendar.DAY_OF_MONTH, day);
		
		Integer hour = Integer.parseInt(request.getParameter("hour"));
		cal.set(Calendar.HOUR_OF_DAY, hour);
		
		Integer minute = Integer.parseInt(request.getParameter("minute"));
		cal.set(Calendar.MINUTE, minute);
		
		Date flightTime = cal.getTime();
		f.setFlightTime(flightTime);
		

		Airplane a = new Airplane();

		String airplane_model = request.getParameter("airplane_model");
		a.setModelName(airplane_model);
		
		String airplane_make = request.getParameter("airplane_make");
		a.setPlaneMake(airplane_make);
		
		Integer airplane_seating= Integer.parseInt(request.getParameter("airplane_seating"));
		a.setSeatingCapacity(airplane_seating);

		f.setAirplaneDetail(a);

		System.out.println(f);
		System.out.println(a);

		fs.addFlight(f, a);
		
		response.sendRedirect("Flights");

	}

}
