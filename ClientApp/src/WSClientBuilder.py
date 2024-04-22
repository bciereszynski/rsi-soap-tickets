import ssl

from requests import Session
from zeep import Transport, Client
from requests.adapters import HTTPAdapter

from src.Config import Config


class SSLContextAdapter(HTTPAdapter):
    def init_poolmanager(self, *args, **kwargs):
        context = ssl.create_default_context()
        context.check_hostname = False
        context.load_verify_locations('cert/server.pem')
        kwargs['ssl_context'] = context
        return super().init_poolmanager(*args, **kwargs)


class WSClientBuilder:
    @staticmethod
    def getClientSSL(path):
        session = Session()
        session.mount('https://', SSLContextAdapter())
        transport = Transport(session=session)
        client = Client(Config.server_ssl + path, transport=transport)
        return client

    @staticmethod
    def getClient(path):
        client = Client(Config.server + path)
        return client
