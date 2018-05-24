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
 * Servlet implementation class AddPilot
 */
@WebServlet("/AddPilot")
public class AddPilot extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PilotService pilotService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPilot() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Pilot pilot = new Pilot();

		pilot.setFirstName("Veli");
		pilot.setLastName("Parker");
		pilot.setPilotLicense(1244);
		pilot.setPilotRank(PilotRank.Captian);

		pilotService.addPilot(pilot);
		System.out.println(pilot);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
