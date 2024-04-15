package rsi.ticketswebservice.services;

import rsi.ticketswebservice.entities.City;
import rsi.ticketswebservice.entities.Flight;
import rsi.ticketswebservice.entities.Ticket;

import java.util.List;

public interface IRepository {
    List<City> getCities();

    List<Flight> getFlights();

    List<Ticket> getTickets();

    void addTicket(Ticket ticket);
}
