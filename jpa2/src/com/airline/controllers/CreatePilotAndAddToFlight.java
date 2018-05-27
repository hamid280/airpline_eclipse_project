package com.airline.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Pilot;
import com.airline.models.PilotRank;
import com.airline.service.PilotService;

/**
 * Servlet implementation class CreatePilotAndAddToFlight
 */
@WebServlet("/CreatePilotAndAddToFlight")
public class CreatePilotAndAddToFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PilotService pilotService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreatePilotAndAddToFlight() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		Pilot pilot = new Pilot();

		String firstName = request.getParameter("first_name");
		pilot.setFirstName(firstName);

		String LastName = request.getParameter("last_name");
		pilot.setLastName(LastName);

		Integer license = Integer.parseInt(request.getParameter("license"));
		pilot.setPilotLicense(license);

		String rank = request.getParameter("pilot_rank");
		pilot.setPilotRank(PilotRank.valueOf(rank));

		String flightId = request.getParameter("fid");

		pilotService.addNewPilotToFlight(pilot, flightId);

		response.sendRedirect("Flights");

	}

}
