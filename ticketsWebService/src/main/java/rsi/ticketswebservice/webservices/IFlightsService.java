package rsi.ticketswebservice.webservices;

import rsi.ticketswebservice.dto.TicketDto;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.IOException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IFlightsService {
    @WebMethod
    TicketDto bookFlight(String flightId, String passengerFirstName, String passengerSurname);

    @WebMethod
    byte[] getBookingConfirmation(String ticketId) throws IOException;
}
