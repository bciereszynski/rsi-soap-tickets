package rsi.ticketswebservice.webservices;

import rsi.ticketswebservice.dto.CityDto;
import rsi.ticketswebservice.dto.FlightDto;
import rsi.ticketswebservice.dto.TicketDto;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IFlightsInfo {
    @WebMethod
    List<FlightDto> getFlights();

    @WebMethod
    List<FlightDto> getFlightsFrom(String query);

    @WebMethod
    List<FlightDto> getFlightsTo(String query);

    @WebMethod
    List<FlightDto> getFlightsBetween(String queryFrom, String queryTo);

    @WebMethod
    List<CityDto> getCities();

    @WebMethod
    List<TicketDto> getTickets();

    @WebMethod
    TicketDto getBookingConfirmation(String ticketId);
}
