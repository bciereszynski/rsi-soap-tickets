from src.WSClientBuilder import WSClientBuilder
from src.models.Flight import Flight


class FlightsRepository:
    def __init__(self):
        self.client = WSClientBuilder.getClient('/ticketsWebService-1.0/FlightsInfoService?wsdl')

    def List(self, fromPlace=None, toPlace=None):
        if fromPlace is not None and toPlace is not None:
            result = self.client.service.getFlightsBetween(fromPlace, toPlace)
        elif fromPlace is not None:
            result = self.client.service.getFlightsFrom(fromPlace)
        elif toPlace is not None:
            result = self.client.service.getFlightsTo(toPlace)
        else:
            result = self.client.service.getFlights()
        return self._parseResult(result)

    def _parseResult(self, result):
        return [Flight(flightDto) for flightDto in result]
