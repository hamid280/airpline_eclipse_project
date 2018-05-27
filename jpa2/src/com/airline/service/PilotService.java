package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.Flight;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class PilotService
 */
@Stateless
@LocalBean
public class PilotService {
	
	@PersistenceContext(unitName = "airline")
	EntityManager em;

    /**
     * Default constructor. 
     */
    public PilotService() {
        // TODO Auto-generated constructor stub
    }
    
    public void addNewPilotToFlight(Pilot pilot, String flightId) {
    	
    	//first we need to add pilot to database then use it to add it to the flight
    	em.persist(pilot);

		TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById", Flight.class);

		fQuery.setParameter("id", Integer.parseInt(flightId));

		Flight f = fQuery.getSingleResult();


		List<Pilot> pList = f.getPilots();

		pList.add(pilot);

		f.setPilots(pList);

		pilot.setFlightForPilot(f);

	}
    
	public void addPilot(Pilot p) {
    	
    	em.persist(p);
    	
    }
	
	

}
