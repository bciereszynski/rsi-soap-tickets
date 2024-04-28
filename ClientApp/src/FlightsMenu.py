from PyQt5.QtWidgets import QListWidget, QVBoxLayout, QWidget, QDialog, QHBoxLayout, QLineEdit, QPushButton

from src.CheckReservationDialog import CheckReservationDialog
from src.TicketInfoDialog import TicketInfoDialog
from src.models.Ticket import Ticket


class FlightsMenu(QWidget):
    def __init__(self, itemsList, itemDialog, parent=None):
        super().__init__(parent)
        self.lay = QVBoxLayout()
        self.setLayout(self.lay)

        self.itemDialog = itemDialog
        self.confirmationDialog = TicketInfoDialog()
        self.itemsList = itemsList
        self.itemsList.listChanged.connect(self.updateItems)

        self.itemsWidget = QListWidget()
        self.updateItems()

        filterLay = QHBoxLayout()
        self.fromFilter = QLineEdit()
        self.fromFilter.setPlaceholderText("From")
        filterLay.addWidget(self.fromFilter)
        self.toFilter = QLineEdit()
        self.toFilter.setPlaceholderText("To")
        filterLay.addWidget(self.toFilter)

        self.fromFilter.textChanged.connect(self.filter)
        self.toFilter.textChanged.connect(self.filter)

        self.lay.addLayout(filterLay)
        self.lay.addWidget(self.itemsWidget)

        checkButton = QPushButton("Check Reservation")
        checkButton.clicked.connect(self.checkReservationAction)
        self.lay.addWidget(checkButton)

        self.itemsWidget.itemDoubleClicked.connect(self.editCommand)

    def filter(self):
        fromPlace = self.fromFilter.text() if self.fromFilter.text() != "" else None
        toPlace = self.toFilter.text() if self.toFilter.text() != "" else None
        self.itemsList.fetch(fromPlace, toPlace)

    def selectedIndex(self):
        if len(self.itemsWidget.selectedIndexes()) == 0:
            return None
        return self.itemsWidget.selectedIndexes()[0]

    def itemSelectionChanged(self):
        return self.itemsWidget.currentItemChanged

    def updateItems(self):
        names = [str(item) for item in self.itemsList.getItems()]
        self.itemsWidget.clear()
        self.itemsWidget.addItems(names)
        self.update()

    def editCommand(self):
        itemToEditRow = self.itemsWidget.selectedIndexes()[0].row()
        self.itemDialog.setWindowTitle("Buy ticket")
        item = self.itemsList.getItem(itemToEditRow)
        self.itemDialog.setFlight(item)
        self.itemDialog.exec_()

        result = self.itemDialog.getResult()
        if result is not None:
            ticket = Ticket(result)
            self.confirmationDialog.setTicket(ticket)
            self.confirmationDialog.exec_()

    def checkReservationAction(self):
        dialog = CheckReservationDialog()
        dialog.exec_()

        result = self.itemDialog.getResult()
        if result is not None:
            ticket = Ticket(result)
            self.confirmationDialog.setTicket(ticket)
            self.confirmationDialog.exec_()
