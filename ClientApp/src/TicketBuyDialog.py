from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import QDialog, QDialogButtonBox, QVBoxLayout, QLabel, QPushButton, QFrame, QLineEdit
from zeep import Client


class TicketBuyDialog(QDialog):
    def __init__(self):
        super().__init__()
        self.lay = QVBoxLayout()
        self.setWindowModality(Qt.ApplicationModal)
        self.ticketLabel = QLabel("...")
        self.flightId = None
        self.lay.addWidget(self.ticketLabel)

        line = QFrame()
        line.setFrameShape(QFrame.HLine)
        line.setFrameShadow(QFrame.Sunken)
        self.lay.addWidget(line)

        self.nameEdit = QLineEdit()
        self.nameEdit.setPlaceholderText("Name")
        self.lay.addWidget(self.nameEdit)

        self.surnameEdit = QLineEdit()
        self.surnameEdit.setPlaceholderText("Surname")
        self.lay.addWidget(self.surnameEdit)

        button = QPushButton("Buy")
        button.clicked.connect(self.buyFlight)
        self.lay.addWidget(button)

        self.setLayout(self.lay)

    def buyFlight(self):
        self.client = Client('http://localhost:8080/ticketsWebService-1.0/FlightsServiceService?wsdl')
        name = self.nameEdit.text()
        surname = self.surnameEdit.text()
        result = self.client.service.bookFlight(self.flightId, name, surname)
        print(result)
        self.close()

    def setFlightStr(self, name):
        self.ticketLabel.setText(name)

    def setFlightId(self, identifier):
        self.flightId = identifier
