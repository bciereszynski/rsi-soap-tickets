package rsi.ticketswebservice.entities;

import rsi.ticketswebservice.dto.FlightDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Flight {
    private final UUID id;
    private final City departureCity;
    private final City arrivalCity;
    private final LocalDateTime departureTime;

    private int availableSeats;

    public Flight(City departureCity, City arrivalCity, LocalDateTime departureTime, int availableSeats) {
        this.id = UUID.randomUUID();
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
    }

    public UUID getId() {
        return id;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean tryReserveSeat() {
        if (this.availableSeats == 0) {
            return false;
        }
        this.availableSeats--;
        return true;
    }

    public FlightDto toDto() {
        return new FlightDto(
                this.id.toString(),
                this.departureCity.toDto(),
                this.arrivalCity.toDto(),
                this.departureTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                this.availableSeats
        );
    }
}
