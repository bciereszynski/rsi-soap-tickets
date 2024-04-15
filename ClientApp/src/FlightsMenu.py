from PyQt5.QtWidgets import QListWidget, QVBoxLayout, QWidget, QDialog


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
        names = [str(item) for item in self.itemsList.getItems()]
        self.itemsWidget.clear()
        self.itemsWidget.addItems(names)
        self.update()

    def editCommand(self):
        itemToEditRow = self.itemsWidget.selectedIndexes()[0].row()
        self.itemDialog.setWindowTitle("Buy ticket")
        self.itemDialog.setValues(self.itemsList.getItem(itemToEditRow))
        self.itemDialog.exec_()

        if self.itemDialog.result() == QDialog.Rejected:
            return

        item = self.itemDialog.getItem()
