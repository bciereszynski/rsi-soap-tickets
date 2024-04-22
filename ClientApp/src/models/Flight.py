from datetime import datetime


class Flight:
    def __init__(self, flightDto):
        self.departureCity = flightDto["departureCity"]
        self.arrivalCity = flightDto["arrivalCity"]
        self.dateTime = datetime.strptime(flightDto["departureTime"], "%Y-%m-%dT%H:%M:%S.%f")
        self.id = flightDto["id"]

    def __str__(self):
        departure_city = self._formatCityName(self.departureCity).ljust(22)
        arrival_city = self._formatCityName(self.arrivalCity).ljust(22)
        date_time = self._formatDate(self.dateTime).ljust(20)
        return f"🛫 {departure_city} ➡️ {arrival_city} ⌚ {date_time}"

    def _formatCityName(self, city):
        name = city['country'] + ' : ' + city['name']
        return name

    def _formatDate(self, datetime):
        return datetime.strftime("%d/%m/%y - %H:%M")
