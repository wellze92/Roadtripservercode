package com.inno.innowebservices;


import java.util.Iterator;
import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;

import com.inno.innowebservices.jaxws.CreateUser;
import com.inno.innowebservices.jaxws.GetUser;


public class UserSoapHandler {

  private static final String NAMESPACE_URI = "http://roadtripserver.appspot.com";
  private static final QName CREATE_USER_QNAME = new QName(NAMESPACE_URI, "createUser");
  private static final QName GET_USER_QNAME = new QName(NAMESPACE_URI, "getUser");

  private MessageFactory messageFactory;
  private CreateUserAdapter greeterAdapter;

  public UserSoapHandler() throws SOAPException {
    messageFactory = MessageFactory.newInstance();
    greeterAdapter = new CreateUserAdapter();
  }

  public SOAPMessage handleSOAPRequest(SOAPMessage request) throws SOAPException {
    SOAPBody soapBody = request.getSOAPBody();
    Iterator iterator = soapBody.getChildElements();
    Object responsePojo = null;
    while (iterator.hasNext()) {
      Object next = iterator.next();
      if (next instanceof SOAPElement) {
        SOAPElement soapElement = (SOAPElement) next;
        QName qname = soapElement.getElementQName();
          if (CREATE_USER_QNAME.equals(qname)) {
            responsePojo = handleCreateUserRequest(soapElement);
            break;
          } else if (GET_USER_QNAME.equals(qname)) {
            responsePojo = handleGetUserRequest(soapElement);
            break;
          }
      }
    }
    SOAPMessage soapResponse = messageFactory.createMessage();
    soapBody = soapResponse.getSOAPBody();
    if (responsePojo != null) {
      JAXB.marshal(responsePojo, new SAAJResult(soapBody));
    } else {
      SOAPFault fault = soapBody.addFault();
      fault.setFaultString("Unrecognized SOAP request.");
    }
    return soapResponse;
  }

  private Object handleCreateUserRequest(SOAPElement soapElement) {
    CreateUser sayHelloRequest = JAXB.unmarshal(new DOMSource(soapElement), CreateUser.class);
    return greeterAdapter.createUser(sayHelloRequest);
  }

  private Object handleGetUserRequest(SOAPElement soapElement) {
    GetUser sayGoodbyeRequest =
        JAXB.unmarshal(new DOMSource(soapElement), GetUser.class);
    return greeterAdapter.geruser(sayGoodbyeRequest);
  }
}