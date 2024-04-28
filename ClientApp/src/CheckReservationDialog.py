from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import QDialog, QVBoxLayout, QPushButton, QLineEdit

from src.WSClientBuilder import WSClientBuilder


class CheckReservationDialog(QDialog):
    def __init__(self):
        super().__init__()
        self.lay = QVBoxLayout()
        self.setWindowModality(Qt.ApplicationModal)
        self.setWindowTitle("Check Reservation")
        self.resize(500, 100)

        self.ticketIDEdit = QLineEdit()
        self.ticketIDEdit.setPlaceholderText("Reservation ID")
        self.lay.addWidget(self.ticketIDEdit)

        self.result = None

        button = QPushButton("Check")
        button.clicked.connect(self.checkReservation)
        self.lay.addWidget(button)

        self.setLayout(self.lay)

    def checkReservation(self):
        client = WSClientBuilder.getClientSSL('/ticketsWebService-1.0/FlightsServiceService?wsdl')
        self.result = client.service.getBookingConfirmation(self.ticketIDEdit.text())

        self.close()

    def getResult(self):
        return self.result
