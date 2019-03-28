package ICT373_Ass2;

import java.io.Serializable;

/**  
* TITLE: Address
* @author Kane Nikander (31566009)
* DATE: 1/05/2018
* FILENAME: Address.java
* PURPOSE: A class that manages and validates address data.
*/ 
public class Address implements Serializable{
    
    private int streetNo;
    private String streetName;
    private String suburb;
    private int postcode;
    
    /** Default constructor. */
    public Address() {
        streetNo = 0;
        streetName = "";
        suburb = "";
        postcode = 0;
    }
    
    /**
     * Constructor.
     * @param no as streetNo
     * @param nam as streetName
     * @param sub as suburb
     * @param pc as postcode
    */
    public Address(int no, String nam, String sub, int pc) {
        streetNo = no;
        streetName = nam;
        suburb = sub;
        postcode = pc;
    }
    
    /**
     * Sets address values with string parameters.
     * @param no as streetNo
     * @param nam as streetName
     * @param sub as suburb
     * @param pc as postcode
    */
    public void SetAddress(String no, String nam, String sub, String pc) {
        SetStreetNo(no);
        SetStreetName(nam);
        SetSuburb(sub);
        SetPostcode(pc);
    }
    
    /**
     * Sets address.
     * @param no as streetNo
     * @param nam as streetName
     * @param sub as suburb
     * @param pc as postcode
    */
    public void SetAddress(int no, String nam, String sub, int pc) {
        streetNo = no;
        streetName = nam;
        suburb = sub;
        postcode = pc;
    }
        
    /**
     * Sets streetNo.
     * @param no as streetNo
     * @return boolean
    */
    public boolean SetStreetNo(String no) {
        if(ValidateInput(no,"[+]?\\d+(\\.\\d+)?")) {
            streetNo = Integer.parseInt(no);
            return true;
        }
        return false;
    }
    
    /**
     * Sets streetNo.
     * @param no as streetNo
    */
    public void SetStreetNo(int no) {
        streetNo = no;
    }
    
    /**
     * Sets streetName.
     * @param nam as streetName
     * @return boolean
    */
    public boolean SetStreetName(String nam) {
        if(ValidateInput(nam,"^(?!\\s*$).+")) {
            streetName = nam;
            return true;
        }
        return false;
    }
    
    /**
     * Sets suburb.
     * @param sub as suburb
     * @return boolean
    */
    public boolean SetSuburb(String sub) {
        if(ValidateInput(sub,"^(?!\\s*$).+")) {
            suburb = sub;
            return true;
        }
        return false;
    }
    
    /**
     * Sets postcode.
     * @param pc as postcode
     * @return boolean
    */
    public boolean SetPostcode(String pc) {
        if(ValidateInput(pc,"[+]?\\d+(\\.\\d+)?")) {
            postcode = Integer.parseInt(pc);
            return true;
        }
        return false;
    }
    
    /**
     * Sets postcode.
     * @param pc as postcode
    */
    public void SetPostcode(int pc) {
        postcode = pc;
    }
    
    /**
     * gets streetNo.
     * @return streetNo
    */
    public int GetStreetNo() {
        return streetNo;
    }
    
    /**
     * gets streetName.
     * @return streetName
    */
    public String GetStreetName() {
        return streetName;
    }
    
    /**
     * gets suburb.
     * @return suburb
    */
    public String GetSuburb() {
        return suburb;
    }
    
    /**
     * gets postcode.
     * @return postcode
    */
    public int GetPostcode() {
        return postcode;
    }
    
    /**
     * Determines if address is empty (ie. a placeholder).
     * @return boolean
    */
    public boolean IsEmpty() {
        if(streetNo == 0) {
            if(streetName.equals("")) {
                if(suburb.equals("")) {
                    if(postcode == 0) {
                        return true;
                    }
                }
            }
        }
        return false;  
    }
        
    /**
     * validate string against regular expression.
     * @return boolean
    */
    private boolean ValidateInput(String input,String criteria) {
        return (input.matches(criteria));
    }
    
}
