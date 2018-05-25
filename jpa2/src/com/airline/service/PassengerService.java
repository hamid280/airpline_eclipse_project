package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.Passenger;

/**
 * Session Bean implementation class PassengerService
 */
@Stateless
@LocalBean
public class PassengerService {

	/**
	 * Default constructor.
	 */
	public PassengerService() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "airline")
	private EntityManager em;

	public void addPassenger(Passenger p) {

		em.persist(p);
		
	}
	
	public List<Passenger> getPassengers(){
		TypedQuery<Passenger> query = em.createQuery("SELECT p FROM Passenger p", Passenger.class);
		List<Passenger> results = query.getResultList();
		return results;
	}

}
