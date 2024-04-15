package rsi.ticketswebservice.entities;

import rsi.ticketswebservice.dto.CityDto;

import java.util.UUID;

public class City {
    private final UUID id;
    private final String name;
    private final String country;

    public City(String name, String country) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.country = country;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public CityDto toDto() {
        return new CityDto(id.toString(), name, country);
    }
}
