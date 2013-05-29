
package com.inno.innowebservices.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getUserResponse", namespace = "http://innowebservices.inno.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserResponse", namespace = "http://innowebservices.inno.com/")
public class GetUserResponse {

    @XmlElement(name = "return", namespace = "")
    private com.inno.innowebservices.UserPojo _return;

    /**
     * 
     * @return
     *     returns UserPojo
     */
    public com.inno.innowebservices.UserPojo getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.inno.innowebservices.UserPojo _return) {
        this._return = _return;
    }

}
