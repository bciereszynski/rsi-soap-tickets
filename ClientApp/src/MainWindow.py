from PyQt5.QtWidgets import QMainWindow, QHBoxLayout, QWidget

from src.FlightList import FlightsList
from src.FlightsMenu import FlightsMenu
from src.FlightsRepository import FlightsRepository
from src.TicketBuyDialog import TicketBuyDialog


class MainWindow(QMainWindow):

    def __init__(self):
        super().__init__()
        self.setWindowTitle('Tickets App')
        self.window_width, self.window_height = 600, 300
        self.setMinimumSize(self.window_width, self.window_height)

        lay = QHBoxLayout()
        widget = QWidget()
        widget.setLayout(lay)
        self.setCentralWidget(widget)

        repo = FlightsRepository()
        list = FlightsList(repo)
        dialog = TicketBuyDialog()
        lay.addWidget(FlightsMenu(list, dialog))
