import copy

from PyQt5.QtCore import pyqtSignal, QObject


class FlightsList(QObject):
    listChanged = pyqtSignal()

    def __init__(self, repository):
        super().__init__()
        self.items = []
        self.repository = repository
        self.fetch()

    def fetch(self, formPlace=None, toPlace=None):
        self.items = self.repository.List(formPlace, toPlace)

        self.listChanged.emit()

    def getItems(self):
        return copy.copy(self.items)

    def getItem(self, index):
        return self.items[index]
