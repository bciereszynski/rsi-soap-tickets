from zeep import Client

from src.models.Flight import Flight


class FlightsRepository:
    def __init__(self):
        self.client = Client('http://localhost:8080/ticketsWebService-1.0/FlightsInfoService?wsdl')

    def List(self):
        result = self.client.service.getFlights()
        return [Flight(flightDto) for flightDto in result]