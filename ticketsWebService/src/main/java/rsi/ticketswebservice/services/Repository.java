package rsi.ticketswebservice.services;

import rsi.ticketswebservice.entities.City;
import rsi.ticketswebservice.entities.Flight;
import rsi.ticketswebservice.entities.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Repository implements IRepository {
    private static Repository instance;

    private Repository() {
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    private final List<City> cities = Arrays.asList(
            new City("New York", "USA"),
            new City("London", "UK"),
            new City("Paris", "France"),
            new City("Berlin", "Germany"),
            new City("Tokyo", "Japan"),
            new City("Sydney", "Australia")
    );

    private final List<Flight> flights = Arrays.asList(
            new Flight(cities.get(0), cities.get(1), randomFutureTime(), 50),
            new Flight(cities.get(0), cities.get(4), randomFutureTime(), 30),
            new Flight(cities.get(0), cities.get(5), randomFutureTime(), 50),
            new Flight(cities.get(1), cities.get(2), randomFutureTime(), 30),
            new Flight(cities.get(1), cities.get(4), randomFutureTime(), 20),
            new Flight(cities.get(1), cities.get(4), randomFutureTime(), 15),
            new Flight(cities.get(2), cities.get(3), randomFutureTime(), 3),
            new Flight(cities.get(2), cities.get(3), randomFutureTime(), 100),
            new Flight(cities.get(2), cities.get(3), randomFutureTime(), 60),
            new Flight(cities.get(2), cities.get(4), randomFutureTime(), 70),
            new Flight(cities.get(3), cities.get(4), randomFutureTime(), 40),
            new Flight(cities.get(4), cities.get(1), randomFutureTime(), 30),
            new Flight(cities.get(4), cities.get(1), randomFutureTime(), 20),
            new Flight(cities.get(4), cities.get(2), randomFutureTime(), 60),
            new Flight(cities.get(4), cities.get(5), randomFutureTime(), 32),
            new Flight(cities.get(5), cities.get(0), randomFutureTime(), 37),
            new Flight(cities.get(5), cities.get(1), randomFutureTime(), 49),
            new Flight(cities.get(5), cities.get(2), randomFutureTime(), 53),
            new Flight(cities.get(5), cities.get(3), randomFutureTime(), 12)
    );

    private final List<Ticket> tickets = new ArrayList<>(Arrays.asList(
            new Ticket(flights.get(0), "John", "Doe"),
            new Ticket(flights.get(0), "Jane", "Doe"),
            new Ticket(flights.get(0), "Alice", "Smith"),
            new Ticket(flights.get(3), "Bob", "Smith"),
            new Ticket(flights.get(4), "Charlie", "Brown"),
            new Ticket(flights.get(5), "David", "Brown"),
            new Ticket(flights.get(6), "Eve", "White"),
            new Ticket(flights.get(7), "Frank", "White")
    ));

    @Override
    public List<City> getCities() {
        return cities;
    }

    @Override
    public List<Flight> getFlights() {
        return flights;
    }

    @Override
    public List<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    private static LocalDateTime randomFutureTime() {
        Random random = new Random();
        return LocalDateTime.now()
                .plusDays(random.nextInt(365))
                .plusHours(random.nextInt(24))
                .plusMinutes(random.nextInt(60));
    }
}
