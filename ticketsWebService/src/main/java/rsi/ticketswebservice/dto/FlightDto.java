package rsi.ticketswebservice.dto;

import java.io.Serializable;

public class FlightDto implements Serializable {
    private String id;
    private CityDto departureCity;
    private CityDto arrivalCity;
    private String departureTime;

    private int availableSeats;

    public FlightDto() {
    }

    public FlightDto(String id, CityDto departureCity, CityDto arrivalCity, String departureTime, int availableSeats) {
        this.id = id;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
    }

    public String getId() {
        return id;
    }

    public CityDto getDepartureCity() {
        return departureCity;
    }

    public CityDto getArrivalCity() {
        return arrivalCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDepartureCity(CityDto departureCity) {
        this.departureCity = departureCity;
    }

    public void setArrivalCity(CityDto arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
