import ssl

from lxml import etree
from requests import Session
from zeep import Transport, Client, Plugin
from requests.adapters import HTTPAdapter

from src.Config import Config


class ApiKeyHeaderHandler(Plugin):
    def __init__(self, api_key):
        self.api_key = api_key

    def egress(self, envelope, http_headers, operation, binding_options):
        ns = "http://webservices.ticketswebservice.rsi/"
        header = etree.Element("{%s}apiKey" % ns)
        header.text = self.api_key
        envelope[0].insert(0, header)
        return envelope, http_headers


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
        plugins = [ApiKeyHeaderHandler(Config.api_key)]
        client = Client(Config.server_ssl + path, transport=transport, plugins=plugins)

        return client

    @staticmethod
    def getClient(path):
        plugins = [ApiKeyHeaderHandler(Config.api_key)]
        client = Client(Config.server + path, plugins=plugins)

        return client
