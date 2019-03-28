package ICT373_Ass2;

import java.io.Serializable;
/**  
* TITLE: Person
* @author Kane Nikander (31566009)
* DATE: 1/05/2018
* FILENAME: Person.java
* PURPOSE: A class that manages the details of people.
*/ 
public class Person implements Serializable{
    
    private String firstName;
    private String surnameAtBirth;
    private String surnameMarriage;
    private char gender;
    private Address streetAddress;
    private String biography;
    private Relationship family;
    
    /** Default constructor. */
    public Person() {
        firstName = "";
        surnameAtBirth = "";
        surnameMarriage = "";
        gender = '\0';
        streetAddress = new Address();
        biography = "";
        //family = new Relationship();  //omitted to avoid perpetual constructor calls
    }
    
    /**
     * Constructor.
     * @param fn as firstName
     * @param snb as surnameAtBirth
     * @param snm as surnameMarriage
     * @param gen as gender
     * @param bio as streetAddress
     * @param sa as biography
     * @param fam as family
    */
    public Person(String fn, String snb, String snm, char gen, String bio, Address sa, Relationship fam) {
        firstName = fn;
        surnameAtBirth = snb;
        surnameMarriage = snm;
        gender = gen;
        streetAddress = sa;
        biography = bio; 
        fam.SetRootPerson(this);
        family = fam;
    }
    
    /**
     * Sets firstName and validates against 'empty' string.
     * @param fn 
     * @return boolean
    */
    public boolean SetFirstName(String fn) {
        if(ValidateInput(fn,"^(?!\\s*$).+")) {
            firstName = fn;
            return true;
        }
        return false;
    }
    
    /**
     * Sets surnameAtBirth and validates against 'empty' string.
     * @param snb
     * @return boolean
    */
    public boolean SetSurnameAtBirth(String snb) {
        if(ValidateInput(snb,"^(?!\\s*$).+")) {
            surnameAtBirth = snb;
            return true;
        }
        return false;
    }
    
    /**
     * Sets surnameMarriage and validates against 'empty' string.
     * @param snm
     * @return boolean
    */
    public boolean SetSurnameMarriage(String snm) {
        if(ValidateInput(snm,"^(?!\\s*$).+")) {
            surnameMarriage = snm;
            return true;
        }
        return false;
    }
    
    /**
     * Sets gender if validates for m,M,f,F values (and removes whitespace).
     * @param gen
     * @return boolean
    */
    public boolean SetGender(String gen) {
        if(ValidateInput(gen,"[m|M|f|F]")) {
            String tempGender = gen.replaceAll("\\W", ""); //remove whitespace
            gender = tempGender.charAt(0);
            return true;
        }
        return false;
    }
    
    /**
     * Sets gender if validates for m,M,f,F values.
     * @param gen
     * @return boolean
    */
    public boolean SetGender(char gen) {
        if(gen == 'm' || gen == 'M' || gen == 'f' || gen == 'F') {
            gender = gen;
            return true;
        }
        return false;
    }
    
    /**
     * Sets address with string parameters for validation in address class .
     * @param no
     * @param nam
     * @param sub
     * @param pc
    */
    public void SetStreetAddress(String no, String nam, String sub, String pc) {
        streetAddress.SetAddress(no,nam,sub,pc);
    }
    
    /**
     * Sets address.
     * @param no
     * @param nam
     * @param sub
     * @param pc
    */
    public void SetStreetAddress(int no, String nam, String sub, int pc) {
        streetAddress.SetAddress(no,nam,sub,pc);
    }
    
    /**
     * Sets biography.
     * @param bio
    */
    public void SetBiography(String bio) {
        biography = bio;
    }
    
    /**
     * Get the firstName.
     * @return firstName
    */
    public String GetFirstName() {
        return firstName;
    }
    
    /**
     * Get the surnameAtBirth.
     * @return surnameAtBirth
    */
    public String GetSurnameAtBirth() {
        return surnameAtBirth;
    }
    
    /**
     * Get the surnameMarriage.
     * @return surnameMarriage
    */
    public String GetSurnameMarriage() {
        return surnameMarriage;
    }
    
    /**
     * Get the gender.
     * @return gender
    */
    public char GetGender() {
        return gender;
    }
    
    /**
     * Get the streetAddress.
     * @return streetAddress
    */
    public Address GetStreetAddress() {
        return streetAddress;
    }
    
    /**
     * Get the biography.
     * @return biography
    */
    public String GetBiography() {
        return biography;
    }
    
    /**
     * Get the family and return new Relationship if null.
     * @return family (or new Relationship)
    */
    public Relationship GetFamily() {
        return ((family == null)?new Relationship():family); //return new if not already initialised
    }
        
    /**
     * Determines if person is empty (ie. a placeholder).
     * @return boolean
    */
    public boolean IsEmpty() {
        
        if(firstName.equals("")) {
            if(surnameAtBirth.equals("")) {
                if(surnameMarriage.equals("")) {
                    if(gender == '\0') {
                        if(streetAddress.IsEmpty()) {
                            if(biography.equals("")) {
                                return true;
                            }
                        }
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
