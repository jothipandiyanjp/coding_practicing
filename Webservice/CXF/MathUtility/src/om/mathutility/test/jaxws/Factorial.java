
package om.mathutility.test.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.18
 * Wed Dec 30 11:02:50 IST 2015
 * Generated source version: 2.7.18
 */

@XmlRootElement(name = "factorial", namespace = "http://test.mathutility.om/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "factorial", namespace = "http://test.mathutility.om/")

public class Factorial {

    @XmlElement(name = "arg0")
    private int arg0;

    public int getArg0() {
        return this.arg0;
    }

    public void setArg0(int newArg0)  {
        this.arg0 = newArg0;
    }

}

