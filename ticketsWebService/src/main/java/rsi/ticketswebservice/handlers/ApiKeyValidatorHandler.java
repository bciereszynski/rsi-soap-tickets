package rsi.ticketswebservice.handlers;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Collections;
import java.util.Set;

public class ApiKeyValidatorHandler implements SOAPHandler<SOAPMessageContext> {

    private static final String API_KEY_HEADER = "apiKey";
    private static final String NAMESPACE_URI = "http://webservices.ticketswebservice.rsi/";
    private static final String VALID_API_KEY = "bc759280-b4b5-43bb-8b79-5cf089dffbf8";

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        System.out.println("----- Handling API KEY message -----");

        Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (!outboundProperty) {
            try {
                SOAPHeader header = context.getMessage().getSOAPPart().getEnvelope().getHeader();
                SOAPElement apiKeyElement = (SOAPElement) header.getChildElements(
                        new QName(NAMESPACE_URI, API_KEY_HEADER)).next();

                if (apiKeyElement == null || !VALID_API_KEY.equals(apiKeyElement.getValue())) {
                    throw new RuntimeException("Invalid API Key");
                }
            } catch (Exception e) {
                throw new RuntimeException("Error during API Key validation", e);
            }
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
        System.out.println("----- Closing API KEY handler -----");
    }

    @Override
    public Set<QName> getHeaders() {
        return Collections.singleton(new QName(NAMESPACE_URI, API_KEY_HEADER));
    }
}