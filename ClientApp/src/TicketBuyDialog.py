from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import QDialog, QDialogButtonBox, QVBoxLayout


class TicketBuyDialog(QDialog):
    def __init__(self):
        super().__init__()
        self.lay = QVBoxLayout()
        self.setWindowModality(Qt.ApplicationModal)
        buttons = QDialogButtonBox(QDialogButtonBox.Cancel | QDialogButtonBox.Save, Qt.Horizontal)
        buttons.accepted.connect(self.accept)
        buttons.rejected.connect(self.reject)
        self.lay.addWidget(buttons)

        self.setLayout(self.lay)

    def getItem(self):
        pass

    def setValues(self, values):
        pass

    def resetValues(self):
        pass
