from src.models.Flight import Flight
from src.models.Passenger import Passenger


class Ticket:
    def __init__(self, ticketDto):
        self.id = ticketDto["id"]
        self.passenger = Passenger(ticketDto["passengerFirstName"], ticketDto["passengerSurname"])
        self.flight = Flight(ticketDto["flight"])
