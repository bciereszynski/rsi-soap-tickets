from datetime import datetime


class Flight:
    def __init__(self, flightDto):
        self.departureCity = flightDto["departureCity"]
        self.arrivalCity = flightDto["arrivalCity"]
        self.dateTime = datetime.strptime(flightDto["departureTime"], "%Y-%m-%dT%H:%M:%S.%f")
        self.id = flightDto["id"]

    def __str__(self):
        name = self._formatCityName(self.departureCity)
        name = name + " ==> "
        name = name + self._formatCityName(self.arrivalCity)
        name = name + " " + self._formatDate(self.dateTime)
        return name

    def _formatCityName(self, city):
        name = city['country'] + ' : ' + city['name']
        return name

    def _formatDate(self, datetime):
        return datetime.strftime("%d/%m/%y - %H:%M")
