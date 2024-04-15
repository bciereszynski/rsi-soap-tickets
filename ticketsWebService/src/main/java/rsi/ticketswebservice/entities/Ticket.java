package rsi.ticketswebservice.entities;

import rsi.ticketswebservice.dto.TicketDto;

import java.util.UUID;

public class Ticket {
    private final UUID id;
    private final Flight flight;
    private final String passengerFirstName;
    private final String passengerSurname;

    public Ticket(Flight flight, String passengerFirstName, String passengerSurname) {
        this.id = java.util.UUID.randomUUID();
        this.flight = flight;
        this.passengerFirstName = passengerFirstName;
        this.passengerSurname = passengerSurname;
    }

    public UUID getId() {
        return id;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public String getPassengerSurname() {
        return passengerSurname;
    }

    public TicketDto toDto() {
        return new TicketDto(this.id.toString(), this.flight.toDto(), this.passengerFirstName, this.passengerSurname);
    }
}
