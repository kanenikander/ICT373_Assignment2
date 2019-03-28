package ICT373_Ass2;

import java.util.*;
import java.io.Serializable;

/**  
* TITLE: Relationship
* @author Kane Nikander (31566009)
* DATE: 1/05/2018
* FILENAME: Relationship.java
* PURPOSE: A class that manages the relationships between Person objects.
*/ 
public class Relationship implements Serializable{
   
    private Person rootPerson; /** Holder of the relationship object. */
    private Person spouse; /** rootPerson's spouse. */
    private Person mother; /** rootPerson's mother. */
    private Person father; /** rootPerson's father. */
    private ArrayList<Person> children = new ArrayList<>(); /** rootPerson's children. */
    
    /** Default constructor. */
    public Relationship() {
        rootPerson = new Person();
        spouse = new Person();
        mother = new Person();
        father = new Person();
    }
    
    /**
     * Constructor.
     * @param rp as rootPerson
     * @param s as spouse
     * @param m as mother
     * @param f as father
    */
    public Relationship(Person rp, Person s, Person m, Person f) {
        rootPerson = rp;
        spouse = s;
        mother = m;
        father = f;
    }
    
    /**
     * Get the rootPerson.
     * @return rootPerson
    */
    public Person GetRootPerson() {
        return rootPerson;
    }
    
    /**
     * Get the spouse of rootPerson.
     * @return spouse
    */
    public Person GetSpouse() {
        return spouse;
    }
    
    /**
     * Get the mother of rootPerson.
     * @return mother
    */
    public Person GetMother() {
        return mother;
    }
    
    /**
     * Get the father of rootPerson.
     * @return father
    */
    public Person GetFather() {
        return father;
    }
    
    /**
     * Get children of rootPerson.
     * @return children
    */
    public ArrayList<Person> GetChildren() {
        return children;
    }
    
    /**
     * Adds person to children if they are not already added.
     * @param p child
     * @return boolean
    */
    public boolean AddChild(Person p) {
        //if child already exist, exists = true
        boolean exists = false;
        for(int i=0; i<children.size(); i++) {
            if(children.get(i) == p) {
                exists = true;
            }
        }
        //if not exists add child
        if(!exists) {
            children.add(p);
            UpdateRelations();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Adds spouse if they are the opposite gender and merges their children lists.
     * @param p spouse
     * @return boolean
    */
    public boolean UpdateSpouse(Person p) {
        if(Character.toLowerCase(rootPerson.GetGender()) != Character.toLowerCase(p.GetGender())) {
            SetSpouse(p);
            p.GetFamily().SetSpouse(rootPerson);
            p.GetFamily().SetChildren(children);
            UpdateRelations();
            return true;
        }
        return false;
    }
    
    /**
     * Adds parent by calling the method appropriate to their gender.
     * @param p parent
     * @return boolean
    */
    public boolean UpdateParent(Person p) {
        if(Character.toLowerCase(p.GetGender()) == 'm') {
            UpdateFather(p);
            return true;
        } else if(Character.toLowerCase(p.GetGender()) == 'f') {
            UpdateMother(p);
            return true;
        }
        return false;
    }
    
    /**
     * Adds father and updates rootPerson to their children list.
     * @param p father
     * @return boolean
    */
    public boolean UpdateFather(Person p) {
        if(Character.toLowerCase(p.GetGender()) == 'm') {
            SetFather(p);
            p.GetFamily().AddChild(rootPerson);
            UpdateRelations();
            return true;
        } else if(p.IsEmpty()) { //setting empty person
            SetFather(p);
            UpdateRelations();
            return true;
        }
        return false;
    }
    
    /**
     * Adds mother and updates rootPerson to their children list.
     * @param p mother
     * @return boolean
    */
    public boolean UpdateMother(Person p) {
        if(Character.toLowerCase(p.GetGender()) == 'f') {
            SetMother(p);
            p.GetFamily().AddChild(rootPerson);
            UpdateRelations();
            return true;
        } else if(p.IsEmpty()) { //setting empty person
            SetMother(p);
            UpdateRelations();
            return true;
        }
        return false;
    }
    
    /**
     * Updates relations of rootPerson to make sure that all links are consistent between entities.
    */
    public void UpdateRelations() {
        
        for(int i=0; i<children.size(); i++) {
            if(Character.toLowerCase(rootPerson.GetGender()) == 'm') {
                if(!spouse.IsEmpty()) {
                    children.get(i).GetFamily().SetMother(spouse);
                }
                children.get(i).GetFamily().SetFather(rootPerson);
            } else if(Character.toLowerCase(rootPerson.GetGender()) == 'f') {
                if(!spouse.IsEmpty()) {
                    children.get(i).GetFamily().SetFather(spouse);
                }
                children.get(i).GetFamily().SetMother(rootPerson);
            }
        }
        
    }
    
    /**
     * Sets the rootPerson.
     * @param p rootPerson
    */
    public void SetRootPerson(Person p) {
        rootPerson = p;
    }
    
    /**
     * Sets the children.
     * @param list children (ArrayList)
    */
    public void SetChildren(ArrayList<Person> list) {
        children = list;
    }
    
    /**
     * Sets the spouse.
     * @param p spouse
    */
    private void SetSpouse(Person p) {
        spouse = p;
    }
    
    /**
     * Sets the mother.
     * @param p mother
    */
    private void SetMother(Person p) {
        mother = p;
    }
    
    /**
     * Sets the father.
     * @param p father
    */
    private void SetFather(Person p) {
        father = p;
    }
    
}
