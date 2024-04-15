package rsi.ticketswebservice.webservices;

import rsi.ticketswebservice.entities.Flight;
import rsi.ticketswebservice.entities.Ticket;
import rsi.ticketswebservice.services.PdfService;
import rsi.ticketswebservice.services.Repository;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.soap.SOAPBinding;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@MTOM
@WebService
@BindingType(value = SOAPBinding.SOAP11HTTP_MTOM_BINDING)
public class FlightsService implements IFlightsService{
    private static final Logger LOGGER = Logger.getLogger(FlightsService.class.getName());
    Repository repository = Repository.getInstance();

    @Override
    public boolean bookFlight(String flightId, String passengerFirstName, String passengerSurname) {
        try {
            UUID id = UUID.fromString(flightId);
            Flight flight = repository.getFlights().stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
            if (flight == null) {
                return false;
            }
            if(flight.tryReserveSeat()){
                repository.addTicket(new Ticket(flight, passengerFirstName, passengerSurname));
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            LOGGER.warning("Invalid UUID format");
            return false;
        }
    }

    @Override
    public byte[] getBookingConfirmation(String ticketId) throws IOException {
        UUID id;
        try {
            id = UUID.fromString(ticketId);
        } catch (IllegalArgumentException e) {
            LOGGER.warning("Invalid UUID format");
            return null;
        }
        Ticket ticket = repository.getTickets().stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
        if (ticket == null) {
            return null;
        }
        return PdfService.GenerateBookingConfirmationPdf(ticket.toDto());
    }
}
