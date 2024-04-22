from requests import Session
from zeep import Transport, Client

from src.Config import Config


class WSClientBuilder:
    @staticmethod
    def getClient(path):
        session = Session()
        session.verify = False
        transport = Transport(session=session)
        client = Client(Config.server + path, transport=transport)
        return client