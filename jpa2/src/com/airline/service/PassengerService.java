package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.airline.models.Flight;
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

	public void addFlightTicketToPassenger(String flightId, String passengerId) {
		
		//Get the passenger by id (new way)
				CriteriaBuilder builder = em.getCriteriaBuilder();
				
				CriteriaQuery<Passenger> cqPassenger = builder.createQuery(Passenger.class);
				
				Root<Passenger> pRoot = cqPassenger.from(Passenger.class);
				
				cqPassenger.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), passengerId));
				
				TypedQuery<Passenger> pQuery = em.createQuery(cqPassenger);
				
				Passenger passenger = pQuery.getSingleResult();
				
				//Get the flight by id
				builder = em.getCriteriaBuilder();
				
				CriteriaQuery<Flight> cqFlight = builder.createQuery(Flight.class);
				
				Root<Flight> fRoot = cqFlight.from(Flight.class);
				
				cqFlight.select(fRoot).where(builder.equal(fRoot.get("id").as(Integer.class), flightId));
				
				TypedQuery<Flight> fQuery = em.createQuery(cqFlight);
				
				Flight flight = fQuery.getSingleResult();
				
				//Associate the flight with the passenger
				
				
				//first we add flight to the passenger (many to many relationship)
				List<Flight> fList = passenger.getFlights();
				
				fList.add(flight);
				
				passenger.setFlights(fList);
				
				//now we add this flight to the list of the flights the passenger has (many to many relationship)
				flight.getPassengers().add(passenger);
		
	}

}
