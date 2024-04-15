import copy

from PyQt5.QtCore import pyqtSignal, QObject


class FlightsList(QObject):
    listChanged = pyqtSignal()

    def __init__(self, repository):
        super().__init__()
        self.items = []
        self.repository = repository
        self.fetch()

    def fetch(self):
        self.items = self.repository.List()
        self.listChanged.emit()

    def getItems(self):
        return copy.copy(self.items)

    def getItem(self, index):
        return self.items[index]
