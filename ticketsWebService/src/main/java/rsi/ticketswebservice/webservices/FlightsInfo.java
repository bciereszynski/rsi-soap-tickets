package rsi.ticketswebservice.webservices;

import rsi.ticketswebservice.dto.CityDto;
import rsi.ticketswebservice.dto.FlightDto;
import rsi.ticketswebservice.dto.TicketDto;
import rsi.ticketswebservice.entities.City;
import rsi.ticketswebservice.entities.Flight;
import rsi.ticketswebservice.entities.Ticket;
import rsi.ticketswebservice.services.Repository;

import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

@WebService
public class FlightsInfo implements IFlightsInfo {

    private final Repository repository = Repository.getInstance();

    @Override
    public List<FlightDto> getFlights() {
        return repository.getFlights().stream().map(Flight::toDto).collect(Collectors.toList());
    }

    @Override
    public List<FlightDto> getFlightsFrom(String query) {
        return repository.getFlights()
                .stream()
                .filter(flight -> flight.getDepartureCity().getName().startsWith(query) ||
                        flight.getDepartureCity().getCountry().startsWith(query))
                .map(Flight::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightDto> getFlightsTo(String query) {
        return repository.getFlights()
                .stream()
                .filter(flight -> flight.getArrivalCity().getName().startsWith(query) ||
                        flight.getArrivalCity().getCountry().startsWith(query))
                .map(Flight::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightDto> getFlightsBetween(String queryFrom, String queryTo) {
        return repository.getFlights()
                .stream()
                .filter(flight ->
                        (flight.getDepartureCity().getName().startsWith(queryFrom) ||
                                flight.getDepartureCity().getCountry().startsWith(queryFrom)) &&
                                (flight.getArrivalCity().getName().startsWith(queryTo) ||
                                        flight.getArrivalCity().getCountry().startsWith(queryTo)))
                .map(Flight::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CityDto> getCities() {
        return repository.getCities().stream().map(City::toDto).collect(Collectors.toList());
    }

    @Override
    public TicketDto checkReservation(String ticketId) {
        return repository.getTickets().stream()
                .filter(ticket -> ticket.getId().toString().startsWith(ticketId))
                .findFirst()
                .map(Ticket::toDto)
                .orElse(null);
    }
}
