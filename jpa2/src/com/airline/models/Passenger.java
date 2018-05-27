package com.airline.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

/**
 * Entity implementation class for Entity: Passenger
 *
 */
@Entity
public class Passenger implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	public Passenger() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String firstName;

	private String lastName;

	@Temporal(TemporalType.DATE)
	private Date dob;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private FlightClass flightClass;
	
	@ManyToMany(mappedBy="passengers")   // passengers is the name of the list of the passengers from the flight class.
	private List<Flight> flights;  //the flight tickets the passenger has

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public FlightClass getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(FlightClass flightClass) {
		this.flightClass = flightClass;
	}
	
	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dob=" + dob + ", gender="
				+ gender + ", flightClass=" + flightClass + "]";
	}

}
