package ICT373_Ass2;

import java.util.*;
import java.io.*;
import javax.swing.*;

/**  
* TITLE: Family Tree
* @author Kane Nikander (31566009)
* DATE: 1/05/2018
* FILENAME: FamilyTree.java
* PURPOSE: A class that manages an instance of a family tree.
*/ 
public class FamilyTree {
    
    private Person rootPerson;
    
    /** Default constructor. */
    public FamilyTree() {
        rootPerson = new Person();
    }
    
    /**
     * Constructor.
     * @param p as rootPerson
    */
    public FamilyTree(Person p) {
        rootPerson = p;
    }
    
    /**
     * Constructor.
     * @param r as rootPerson's Relationship
    */
    public FamilyTree(Relationship r) {
        rootPerson = r.GetRootPerson();
    }
    
    /**
     * Sets rootPerson.
     * @param p as rootPerson
    */
    public void SetRootPerson(Person p) {
        rootPerson = p;
    }
    
    /**
     * gets rootPerson.
     * @return rootPerson
    */
    public Person GetRootPerson() {
        return rootPerson;
    }
    
    /**
     * Removes a person from the family tree. Validates that they are not the root person and 
     * updates all their relations data to ensure they are no longer linked. This method doesn't
     * delete a person so much as disassociates them. Linked nodes will also get deleted if they
     * linked to the family tree through current person.
     * @param p as rootPerson
    */
    public void RemovePerson(Person p){
        
        p.GetFamily();
        if(p != rootPerson) {
        
            if(!p.GetFamily().GetFather().GetFamily().GetChildren().isEmpty()) {
                RemoveChild(p.GetFamily().GetFather(),p);
            }
            if(!p.GetFamily().GetMother().GetFamily().GetChildren().isEmpty()) {
                RemoveChild(p.GetFamily().GetMother(),p);
            }
            if(!p.GetFamily().GetSpouse().IsEmpty()) {
                p.GetFamily().GetSpouse().GetFamily().UpdateSpouse(new Person());
            }
            for(int i=0; i<p.GetFamily().GetChildren().size(); i++) {
                if(Character.toLowerCase(p.GetGender()) == 'm') {
                    p.GetFamily().GetChildren().get(i).GetFamily().UpdateFather(new Person());
                } else if(Character.toLowerCase(p.GetGender()) == 'f') {
                    p.GetFamily().GetChildren().get(i).GetFamily().UpdateMother(new Person());
                }
                               
            }
  
        }
        
    }
    
    /**
     * Removes a child.
     * @param p as parent
     * @param c as child
    */
    private void RemoveChild(Person p, Person c) {
        for(int i=0; i<p.GetFamily().GetChildren().size(); i++) {
            if(p.GetFamily().GetChildren().get(i) == c) {
                p.GetFamily().GetChildren().remove(i);
            }
        }
    }
    
    /**
     * Saves a family tree in file as .famtree format.
     * @param fileName as filename or path and filename of intended destination.
    */
    public void SaveFile(String fileName) {
        
        fileName += ".famtree";
        
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            
            outputStream.writeObject(rootPerson);
                    
            outputStream.close();
                    
        } catch(FileNotFoundException e) {
            //do nothing
        } catch(IOException e) {
            //do nothing
        } catch(Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Unable to save " + fileName + ". Please try again", "Save File Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /**
     * Opens a family tree file of .famtree format.
     * @param fileName as filename or path and filename of file to be opened.
    */
    public void OpenFile(String fileName) {
        
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
                        
            Object obj = inputStream.readObject();
            
            rootPerson = (Person)obj;
            
            inputStream.close();
                    
        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(new JFrame(), "File was not found at " + fileName + ". Please try again.", "File Not Found", JOptionPane.ERROR_MESSAGE);
        } catch(EOFException e) {
            //do nothing
        } catch(IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Unable to open " + fileName + ". Please open a file with the .famtree extension.", "Incompatible File Error", JOptionPane.ERROR_MESSAGE);
        } catch(Exception e) {
            //do nothing
        }
        
    }
    
}
