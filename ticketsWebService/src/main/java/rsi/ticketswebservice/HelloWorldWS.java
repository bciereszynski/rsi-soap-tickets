package rsi.ticketswebservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
@WebService
public class HelloWorldWS{
    @WebMethod
    public String getHelloWorldAsString(String name) {
        return "Witaj świecie JAX-WS: " + name;
    }
}