from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import QDialog, QVBoxLayout, QLabel, QFrame, QLineEdit, QPushButton, QFileDialog
from zeep import Client

from src.Config import Config


class TicketInfoDialog(QDialog):
    def __init__(self):
        super().__init__()
        self.lay = QVBoxLayout()
        self.setWindowTitle("Ticket Info")
        self.setWindowModality(Qt.ApplicationModal)
        self.ticket = None

        self.flightLabel = QLabel("Flight:")
        self.lay.addWidget(self.flightLabel)
        self.flightNameLabel = QLabel("..")
        self.lay.addWidget(self.flightNameLabel)

        line = QFrame()
        line.setFrameShape(QFrame.HLine)
        line.setFrameShadow(QFrame.Sunken)
        self.lay.addWidget(line)

        self.passengerLabel = QLabel("Passenger:")
        self.lay.addWidget(self.passengerLabel)
        self.passengerNameLabel = QLabel("...")
        self.lay.addWidget(self.passengerNameLabel)

        self.ticketLabel = QLabel("Ticket identifier:")
        self.lay.addWidget(self.ticketLabel)
        self.ticketIdLabel = QLineEdit("...")
        self.ticketIdLabel.setReadOnly(True)
        self.lay.addWidget(self.ticketIdLabel)

        downloadPdfButton = QPushButton("Download PDF")
        downloadPdfButton.clicked.connect(self.downloadPdf)
        self.lay.addWidget(downloadPdfButton)

        self.setLayout(self.lay)

    def setTicket(self, ticket):
        self.ticket = ticket
        self.flightNameLabel.setText(str(ticket.flight))
        self.passengerNameLabel.setText(str(ticket.passenger))
        self.ticketIdLabel.setText(str(ticket.id))

    def downloadPdf(self):
        fileName = QFileDialog.getSaveFileName(self, 'Save PDF', 'untitled.pdf', 'PDF files (*.pdf)')[0]
        if fileName == '':
            return
        client = Client(Config.server + '/ticketsWebService-1.0/FlightsServiceService?wsdl')
        result = client.service.getBookingConfirmation(self.ticket.id)

        with open(fileName, 'wb') as f:
            f.write(result)