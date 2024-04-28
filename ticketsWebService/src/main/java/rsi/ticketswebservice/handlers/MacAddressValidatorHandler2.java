package rsi.ticketswebservice.handlers;

import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class MacAddressValidatorHandler2 implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        //if mac address is not match, throw exception
        System.out.println("HANDLER TUTAJ ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ESSA");

        //continue other handler chain
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {

        System.out.println("Server : handleFault()......");
        return true;
    }

    @Override
    public void close(MessageContext context) {
        System.out.println("Server : close()......");
    }

    @Override
    public Set<QName> getHeaders() {
        System.out.println("Server : getHeaders()......");
        return null;
    }

}
