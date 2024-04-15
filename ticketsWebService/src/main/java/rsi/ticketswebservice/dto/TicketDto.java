package rsi.ticketswebservice.dto;

import java.io.Serializable;

public class TicketDto implements Serializable {
    private String id;
    private FlightDto flight;
    private String passengerFirstName;
    private String passengerSurname;

    public TicketDto() {
    }

    public TicketDto(String id, FlightDto flight, String passengerFirstName, String passengerSurname) {
        this.id = id;
        this.flight = flight;
        this.passengerFirstName = passengerFirstName;
        this.passengerSurname = passengerSurname;
    }

    public String getId() {
        return id;
    }

    public String getPassengerSurname() {
        return passengerSurname;
    }

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public FlightDto getFlight() {
        return flight;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFlight(FlightDto flight) {
        this.flight = flight;
    }

    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public void setPassengerSurname(String passengerSurname) {
        this.passengerSurname = passengerSurname;
    }
}
