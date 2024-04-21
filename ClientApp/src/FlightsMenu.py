from PyQt5.QtWidgets import QListWidget, QVBoxLayout, QWidget, QDialog
from datetime import datetime

class FlightsMenu(QWidget):
    def __init__(self, itemsList, itemDialog, parent=None):
        super().__init__(parent)
        self.lay = QVBoxLayout()
        self.setLayout(self.lay)

        self.itemDialog = itemDialog
        self.itemsList = itemsList
        self.itemsList.listChanged.connect(self.updateItems)

        self.itemsWidget = QListWidget()
        self.updateItems()

        self.lay.addWidget(self.itemsWidget)

        self.itemsWidget.itemDoubleClicked.connect(self.editCommand)

    def selectedIndex(self):
        if len(self.itemsWidget.selectedIndexes()) == 0:
            return None
        return self.itemsWidget.selectedIndexes()[0]

    def itemSelectionChanged(self):
        return self.itemsWidget.currentItemChanged

    def updateItems(self):
        names = [self._formatFlightName(item) for item in self.itemsList.getItems()]
        self.itemsWidget.clear()
        self.itemsWidget.addItems(names)
        self.update()

    def editCommand(self):
        itemToEditRow = self.itemsWidget.selectedIndexes()[0].row()
        self.itemDialog.setWindowTitle("Buy ticket")
        item = self.itemsList.getItem(itemToEditRow)
        self.itemDialog.setFlightStr(self._formatFlightName(item))
        self.itemDialog.setFlightId(item["id"])
        self.itemDialog.exec_()

        if self.itemDialog.result() == QDialog.Rejected:
            return

        item = self.itemDialog.getItem()

    def _formatFlightName(self, item):
        name = self._formatCityName(item["departureCity"])
        name = name + " ==> "
        name = name + self._formatCityName(item["arrivalCity"])
        name = name + " " + self._formatDate(item)
        return name

    def _formatCityName(self, city):
        name = city['country'] + ' : ' + city['name']
        return name

    def _formatDate(self, item):
        date = datetime.strptime(item["departureTime"], "%Y-%m-%dT%H:%M:%S.%f")
        return date.strftime("%d/%m/%y - %H:%M")
