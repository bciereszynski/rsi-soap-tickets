from zeep import Client

class FlightsRepository:
    def __init__(self):
        self.client = Client('http://localhost:8080/ticketsWebService-1.0/FlightsInfoService?wsdl')

    def List(self):
        result = self.client.service.getFlights()
        return result