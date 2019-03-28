package ICT373_Ass2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;

/**  
* TITLE: Interface
* @author Kane Nikander (31566009)
* DATE: 1/05/2018
* FILENAME: Interface.java
* PURPOSE: A class that manages the GUI elements and user-side interactions with family tree data.
*/ 
public class Interface extends JFrame {
    
    private final FamilyTree FT;
    private Person currentPerson;
    
    private final FamilyTreePanel FTP;
    private final PersonDetailsPanel PDP;
    
    private boolean editMode;
    private JMenuItem menuItemViewMode;
    private JMenuItem menuItemEditMode;
    
    private String filePath;
    private final String emptyText = "";
    
    public class ExitEvent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    public class NewFileEvent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            FT.SetRootPerson(new Person(emptyText,emptyText,emptyText,'m',emptyText, new Address(0,emptyText,emptyText,0), new Relationship()));
            RefreshFrame();
        }
    }
    
    public class OpenFileEvent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            filePath = "";
            try {
                FileBrowserOpen application = new FileBrowserOpen();
            } catch(IOException er) {
                //error
            }
            FT.OpenFile(filePath);
            RefreshFrame();
        }
    }
    
    public class SaveFileEvent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CommitChanges();
            filePath = "";
            try {
                FileBrowserSave application = new FileBrowserSave();
            } catch(IOException er) {
                //error
            }
            FT.SaveFile(filePath);
        }
    }
    
    public class ViewModeEvent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            editMode = false;
            PDP.ChangeMode();
            menuItemViewMode.setEnabled(false);
            menuItemEditMode.setEnabled(true);
        }
    }
    
    public class EditModeEvent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            editMode = true;
            PDP.ChangeMode();
            menuItemViewMode.setEnabled(true);
            menuItemEditMode.setEnabled(false);
        }
    } 
    
    /** Default constructor. */
    public Interface(String frameTitle){
        
        super(frameTitle);
        
        //VARIABLE INITILISATION
        FT = new FamilyTree();
        FT.GetRootPerson().GetFamily(); //relation check
        currentPerson = FT.GetRootPerson();
        editMode = false;
        
        //WINDOW LAYOUT
        JFrame frame = (JFrame) SwingUtilities.getRoot(this);
        GridLayout layout = new GridLayout(0,2);
        layout.setHgap(5);
        frame.setLayout(layout);
        getContentPane().setBackground(Color.GRAY);
        
        //MENU
        JMenuBar menuBar = MenuBar();
        menuBar.setBackground(Color.LIGHT_GRAY);
        frame.setJMenuBar(menuBar);
        
        //PERSON DETAILS PANEL
        PDP = new PersonDetailsPanel();
        JPanel personDetailsPanel = PDP;
        PDP.ChangeMode();
        
        //FAMILY TREE PANEL
        FTP = new FamilyTreePanel();
        JPanel familyTreePanel = FTP;
 
        frame.add(familyTreePanel);
        frame.add(personDetailsPanel);
        
        //EXIT
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
    }
    
    /**
     * Creates menu bar for main frame.
     * @return menuBar
    */
    private JMenuBar MenuBar() {
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuMode = new JMenu("Mode");
        
        JMenuItem menuItemNewFile = new JMenuItem("New");
        menuItemNewFile.addActionListener(new NewFileEvent());
        JMenuItem menuItemOpenFile = new JMenuItem("Open");
        menuItemOpenFile.addActionListener(new OpenFileEvent());
        JMenuItem menuItemSaveFile = new JMenuItem("Save");
        menuItemSaveFile.addActionListener(new SaveFileEvent());
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(new ExitEvent());
        
        menuItemViewMode = new JMenuItem("View Mode");
        menuItemViewMode.addActionListener(new ViewModeEvent());
        menuItemEditMode = new JMenuItem("Edit Mode");
        menuItemEditMode.addActionListener(new EditModeEvent());
        menuItemViewMode.setEnabled(false);
        menuItemEditMode.setEnabled(true);
        
        menuBar.add(menuFile);
        menuFile.add(menuItemNewFile);
        menuFile.add(menuItemOpenFile);
        menuFile.add(menuItemSaveFile);
        menuFile.add(menuItemExit);
        
        menuBar.add(menuMode);
        menuMode.add(menuItemViewMode);
        menuMode.add(menuItemEditMode);
        
        return menuBar;
        
    }
    
    public class FileBrowserOpen extends JFrame {
        
        private final JTextArea outputArea; 
        
        /** Default constructor. */
        public FileBrowserOpen() throws IOException {
            super("File Browser");
            outputArea = new JTextArea();
            add(new JScrollPane(outputArea));
            analyzePath();
        }
        
        public void analyzePath() throws IOException {
            Path path = getFileOrDirectoryPath();   
        }
        
        private Path getFileOrDirectoryPath() {
            
            // configure dialog allowing selection of a file or directory
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Family Tree File","famtree");
            fileChooser.setFileFilter(filter);
            fileChooser.setCurrentDirectory(new File("."));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int result = fileChooser.showOpenDialog(this);
            
            // if user clicked Cancel button on dialog, return
            if (result == JFileChooser.CANCEL_OPTION) {
                dispose();
            }
                
            filePath = fileChooser.getSelectedFile().toString();
            
            // return Path representing the selected file
            return fileChooser.getSelectedFile().toPath();
            
        } 
    }
    
    public class FileBrowserSave extends JFrame {
        
        private final JTextArea outputArea; 
        
        /** Default constructor. */
        public FileBrowserSave() throws IOException {
            super("File Browser");
            outputArea = new JTextArea();
            add(new JScrollPane(outputArea));
            analyzePath();
        }
        
        public void analyzePath() throws IOException {
            Path path = getFileOrDirectoryPath();   
        }
        
        private Path getFileOrDirectoryPath() {
            
            // configure dialog allowing selection of a file or directory
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Family Tree File","famtree");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(filter);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.setCurrentDirectory(new File("."));
            int result = fileChooser.showSaveDialog(this);
            
            // if user clicked Cancel button on dialog, return
            if (result == JFileChooser.CANCEL_OPTION) {
                dispose();
            }
                
            filePath = fileChooser.getSelectedFile().toString();
            
            // return Path representing the selected file
            return fileChooser.getSelectedFile().toPath();
            
        } 
    }

    public class PersonDetailsPanel extends JPanel {

        private final ArrayList<JTextField> memberTextList = new ArrayList<>();
        private final JComboBox dropDownGender;
        private final JTextArea textBiography;
        private final JTextArea textChildren;
        
        private final JButton addButtonFather;
        private final JButton addButtonMother;
        private final JButton addButtonSpouse;
        private final JButton addButtonChildren;
        
        private final JButton deleteButtonFather;
        private final JButton deleteButtonMother;
        private final JButton deleteButtonSpouse;
        
        private final JButton deleteCurrentPerson;
        
        public class DeletePersonEvent implements ActionListener {
            public void actionPerformed(ActionEvent e) {   
                DeletePerson(currentPerson);
            }
        }
        
        public class DeleteFatherEvent implements ActionListener {
            public void actionPerformed(ActionEvent e) {   
                DeletePerson(currentPerson.GetFamily().GetFather());
            }
        }
        
        public class DeleteMotherEvent implements ActionListener {
            public void actionPerformed(ActionEvent e) {   
                DeletePerson(currentPerson.GetFamily().GetMother());
            }
        }
        
        public class DeleteSpouseEvent implements ActionListener {
            public void actionPerformed(ActionEvent e) {   
                DeletePerson(currentPerson.GetFamily().GetSpouse());
            }
        }
        
        public class AddFatherEvent implements ActionListener {
            public void actionPerformed(ActionEvent e) {   
                if(currentPerson.GetFamily().GetFather().IsEmpty()) {
                    Person tempPerson = new Person(emptyText,emptyText,emptyText,'m',emptyText, new Address(0,emptyText,emptyText,0), new Relationship());
                    currentPerson.GetFamily().UpdateFather(tempPerson);
                    if(currentPerson.GetFamily().GetMother() != null && !currentPerson.GetFamily().GetMother().IsEmpty()) {
                        currentPerson.GetFamily().GetMother().GetFamily().UpdateSpouse(tempPerson);
                    }
                    RefreshFrame(tempPerson);
                }
            }
        }
        
        public class AddMotherEvent implements ActionListener {
            public void actionPerformed(ActionEvent e) {   
                if(currentPerson.GetFamily().GetMother().IsEmpty()) {
                    Person tempPerson = new Person(emptyText,emptyText,emptyText,'f',emptyText, new Address(0,emptyText,emptyText,0), new Relationship());
                    currentPerson.GetFamily().UpdateMother(tempPerson);
                    if(currentPerson.GetFamily().GetFather() != null && !currentPerson.GetFamily().GetFather().IsEmpty()) {
                        currentPerson.GetFamily().GetFather().GetFamily().UpdateSpouse(tempPerson);
                    }
                    RefreshFrame(tempPerson);
                }
            }
        }
        
        public class AddSpouseEvent implements ActionListener {
            public void actionPerformed(ActionEvent e) {   
                char tempGender = (Character.toLowerCase(currentPerson.GetGender()) == 'm'?'f':'m');
                
                if(currentPerson.GetFamily().GetSpouse().IsEmpty()) {
                    Person tempPerson = new Person(emptyText,emptyText,emptyText,tempGender,emptyText, new Address(0,emptyText,emptyText,0), new Relationship());
                    currentPerson.GetFamily().UpdateSpouse(tempPerson);
                    tempPerson.GetFamily().SetChildren(currentPerson.GetFamily().GetChildren());
                    RefreshFrame(tempPerson);
                }
            }
        }
        
        public class AddChildEvent implements ActionListener {
            public void actionPerformed(ActionEvent e) {   
                Person tempPerson = new Person(emptyText,emptyText,emptyText,'m',emptyText, new Address(0,emptyText,emptyText,0), new Relationship());
                currentPerson.GetFamily().AddChild(tempPerson);
                tempPerson.GetFamily().UpdateParent(currentPerson);
                RefreshFrame(tempPerson);
            }
        }
        
        /** Default constructor. */
        public PersonDetailsPanel() {

            Border border = BorderFactory.createLineBorder(Color.BLACK,3);
            TitledBorder titleBorder = BorderFactory.createTitledBorder(border, "Person Details");
            setBorder(titleBorder);
            setBackground(Color.WHITE);
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            
            //Set up scroll panel
            JPanel scrollPanel = new JPanel();
            scrollPanel.setLayout(new GridBagLayout());
            JScrollPane scrollBar = new JScrollPane(scrollPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollBar.getVerticalScrollBar().setUnitIncrement(10);
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            add(scrollBar,c);
            
            //Set up labels and text fields
            c = new GridBagConstraints();
            
            //PADDING
            c.gridx = 0;
            c.gridy = 0;
            scrollPanel.add(new JLabel(" "),c);
            
            //PERSON
            JLabel personPanelLabel = new JLabel("Person :");
            personPanelLabel.setFont(new Font("Arial",Font.BOLD,16));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy++;
            scrollPanel.add(personPanelLabel,c);

            //blank
            c.gridx = 0;
            c.gridy++;
            scrollPanel.add(new JLabel(" "),c);

            //first name
            c.gridx = 0;
            c.gridy++;
            c.insets = new Insets(0,0,0,10);
            scrollPanel.add(new JLabel("First Name :"),c);

            JTextField textFirstName = new JTextField();
            textFirstName.setColumns(10);
            c.gridx = 1;
            c.insets = new Insets(0,0,0,0);
            scrollPanel.add(textFirstName,c);
            memberTextList.add(textFirstName);

            //surname (birth)
            c.gridx = 0;
            c.gridy++;
            c.insets = new Insets(0,0,0,10);
            scrollPanel.add(new JLabel("Surname (birth) :"),c);

            JTextField textSurnameBirth = new JTextField();
            textSurnameBirth.setColumns(10);
            c.gridx = 1;
            c.insets = new Insets(0,0,0,0);
            scrollPanel.add(textSurnameBirth,c);
            memberTextList.add(textSurnameBirth);

            //surname (marriage)
            c.gridx = 0;
            c.gridy++;
            c.insets = new Insets(0,0,0,10);
            scrollPanel.add(new JLabel("Surname (marriage) :"),c);

            JTextField textSurnameMarriage = new JTextField();
            textSurnameMarriage.setColumns(10);
            c.gridx = 1;
            c.insets = new Insets(0,0,0,0);
            scrollPanel.add(textSurnameMarriage,c);
            memberTextList.add(textSurnameMarriage);

            //gender
            c.gridx = 0;
            c.gridy++;
            c.insets = new Insets(0,0,0,10);
            scrollPanel.add(new JLabel("Gender :"),c);

            String[] genderOptions = {"Male","Female"};
            dropDownGender = new JComboBox(genderOptions);
            c.gridx = 1;
            c.insets = new Insets(0,0,0,0);
            scrollPanel.add(dropDownGender,c);

            //biography
            c.gridx = 0;
            c.gridy++;
            c.insets = new Insets(0,0,0,10);
            scrollPanel.add(new JLabel("Biography :"),c);
            
            textBiography = new JTextArea();
            textBiography.setBorder(textFirstName.getBorder()); //adds border like text fields
            JScrollPane scrollPane = new JScrollPane(textBiography,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,  //AS_NEEDED - consider revision
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            textBiography.setRows(3);
            textBiography.setLineWrap(true);
            c.gridx = 1;
            c.insets = new Insets(0,0,0,0);
            scrollPanel.add(scrollPane,c);


            //blank
            c.gridx = 0;
            c.gridy++;
            scrollPanel.add(new JLabel(" "),c);

            //STREET ADDRESS
            JLabel streetAddressPanelLabel = new JLabel("Address :");
            streetAddressPanelLabel.setFont(new Font("Arial",Font.BOLD,16));
            c.gridx = 0;
            c.gridy++;
            scrollPanel.add(streetAddressPanelLabel,c);

            //blank
            c.gridx = 0;
            c.gridy++;
            scrollPanel.add(new JLabel(" "),c);

            //street no
            c.gridx = 0;
            c.gridy++;
            c.insets = new Insets(0,0,0,10);
            scrollPanel.add(new JLabel("Street No :"),c);

            JTextField textStreetNo = new JTextField();
            textStreetNo.setColumns(10);
            c.gridx = 1;
            c.insets = new Insets(0,0,0,0);
            scrollPanel.add(textStreetNo,c);
            memberTextList.add(textStreetNo);

            //street name
            c.gridx = 0;
            c.gridy++;
            c.insets = new Insets(0,0,0,10);
            scrollPanel.add(new JLabel("Street Name :"),c);

            JTextField textStreetName = new JTextField();
            textStreetName.setColumns(10);
            c.gridx = 1;
            c.insets = new Insets(0,0,0,0);
            scrollPanel.add(textStreetName,c);
            memberTextList.add(textStreetName);

            //suburb
            c.gridx = 0;
            c.gridy++;
            c.insets = new Insets(0,0,0,10);
            scrollPanel.add(new JLabel("Suburb :"),c);

            JTextField textSuburb = new JTextField();
            textSuburb.setColumns(10);
            c.gridx = 1;
            c.insets = new Insets(0,0,0,0);
            scrollPanel.add(textSuburb,c);
            memberTextList.add(textSuburb);

            //postcode
            c.gridx = 0;
            c.gridy++;
            c.insets = new Insets(0,0,0,10);
            scrollPanel.add(new JLabel("Postcode :"),c);

            JTextField textPostcode = new JTextField();
            textPostcode.setColumns(10);
            c.gridx = 1;
            c.insets = new Insets(0,0,0,0);
            scrollPanel.add(textPostcode,c);
            memberTextList.add(textPostcode);


            //blank
            c.gridx = 0;
            c.gridy++;
            scrollPanel.add(new JLabel(" "),c);

            //FAMILY
            String emptyFamField = "...";

            JLabel familyPanelLabel = new JLabel("Family :");
            familyPanelLabel.setFont(new Font("Arial",Font.BOLD,16));
            c.gridx = 0;
            c.gridy++;
            scrollPanel.add(familyPanelLabel,c);

            //blank
            c.gridx = 0;
            c.gridy++;
            scrollPanel.add(new JLabel(" "),c);

            //Father
            c.gridx = 0;
            c.gridy++;
            scrollPanel.add(new JLabel("Father :"),c);

            JTextField textFather = new JTextField(emptyFamField);
            textFather.setPreferredSize(new Dimension(180,20));
            textFather.setEditable(false);
            c.gridx = 1;
            scrollPanel.add(textFather,c);
            memberTextList.add(textFather);

            addButtonFather = new JButton("Add");
            addButtonFather.setBorder(null);
            addButtonFather.addActionListener(new AddFatherEvent());
       
            c.gridx = 2;
            c.insets = new Insets(0,(10),0,0);
            scrollPanel.add(addButtonFather,c);
            
            deleteButtonFather = new JButton("Delete");
            deleteButtonFather.setBorder(null);
            deleteButtonFather.addActionListener(new DeleteFatherEvent());
       
            c.gridx = 3;
            scrollPanel.add(deleteButtonFather,c);
            
            //Mother
            c.insets = new Insets(0,0,0,0);
            c.gridx = 0;
            c.gridy++;
            scrollPanel.add(new JLabel("Mother :"),c);

            JTextField textMother = new JTextField(emptyFamField);
            textMother.setPreferredSize(new Dimension(180,20));
            textMother.setEditable(false);
            c.gridx = 1;
            scrollPanel.add(textMother,c);
            memberTextList.add(textMother);

            addButtonMother = new JButton("Add");
            addButtonMother.setBorder(null);
            addButtonMother.addActionListener(new AddMotherEvent());
       
            c.gridx = 2;
            c.insets = new Insets(0,(10),0,0);
            scrollPanel.add(addButtonMother,c);
            
            deleteButtonMother = new JButton("Delete");
            deleteButtonMother.setBorder(null);
            deleteButtonMother.addActionListener(new DeleteMotherEvent());
       
            c.gridx = 3;
            scrollPanel.add(deleteButtonMother,c);
            
            //Spouse
            c.insets = new Insets(0,0,0,0);
            c.gridx = 0;
            c.gridy++;
            scrollPanel.add(new JLabel("Spouse :"),c);

            JTextField textSpouse = new JTextField(emptyFamField);
            textSpouse.setPreferredSize(new Dimension(180,20));
            textSpouse.setEditable(false);
            c.gridx = 1;
            scrollPanel.add(textSpouse,c);
            memberTextList.add(textSpouse);
            
            addButtonSpouse = new JButton("Add");
            addButtonSpouse.setBorder(null);
            addButtonSpouse.addActionListener(new AddSpouseEvent());
       
            c.gridx = 2;
            c.insets = new Insets(0,(10),0,0);
            scrollPanel.add(addButtonSpouse,c);
            
            deleteButtonSpouse = new JButton("Delete");
            deleteButtonSpouse.setBorder(null);
            deleteButtonSpouse.addActionListener(new DeleteSpouseEvent());
       
            c.gridx = 3;
            scrollPanel.add(deleteButtonSpouse,c);

            //Children
            c.insets = new Insets(0,0,0,0);
            c.gridx = 0;
            c.gridy++;
            scrollPanel.add(new JLabel("Children :"),c);

            textChildren = new JTextArea(emptyFamField);
            textChildren.setLineWrap(true);
            textChildren.setBorder(textFirstName.getBorder()); //adds border like text fields
            textChildren.setBackground(textFather.getBackground()); //to get non-enable background colour
            textChildren.setEditable(false);
            c.gridx = 1;
            scrollPanel.add(textChildren,c);

            addButtonChildren = new JButton("Add");
            addButtonChildren.setBorder(null);
            addButtonChildren.addActionListener(new AddChildEvent());
       
            c.gridx = 2;
            c.insets = new Insets(0,(10),0,0);
            scrollPanel.add(addButtonChildren,c);
            
            //Delete current person
            
            deleteCurrentPerson = new JButton("Delete Current Person");
            deleteCurrentPerson.addActionListener(new DeletePersonEvent());
       
            c.insets = new Insets(10,0,0,0);
            c.gridx = 0;
            c.gridy++;
            c.gridwidth = 4; //stretches 4 columns
            scrollPanel.add(deleteCurrentPerson,c);
            
            //padding
            c.gridx = 0;
            c.gridy++;
            scrollPanel.add(new JLabel(" "),c);
            
        }

        /**
         * Enables/disables buttons and text fields based on current mode.
        */
        private void ChangeMode() {
            dropDownGender.setEnabled(editMode);
            textBiography.setEnabled(editMode);
            for(int i=0; i<memberTextList.size()-3; i++) {
                memberTextList.get(i).setEditable(editMode);
            }
            addButtonFather.setEnabled(editMode);
            addButtonMother.setEnabled(editMode);
            addButtonSpouse.setEnabled(editMode);
            addButtonChildren.setEnabled(editMode);
            deleteButtonFather.setEnabled(editMode);
            deleteButtonMother.setEnabled(editMode);
            deleteButtonSpouse.setEnabled(editMode);
            deleteCurrentPerson.setEnabled(editMode);
        }
        
        /**
         * Removes person and refreshes panels.
         * @param p 
        */
        private void DeletePerson(Person p) {
            FT.RemovePerson(p);
            FTP.PrintFamilyTree();

            currentPerson = FT.GetRootPerson();
            PDP.PrintMember(currentPerson);
                
            UpdateBorders(Color.BLACK);
        }
        
        /**
         * Prints family member information in text fields.
         * @param p 
        */
        private void PrintMember(Person p) {

            if(Character.toLowerCase(p.GetGender()) == 'm') {
                dropDownGender.setSelectedItem("Male");
            } else if(Character.toLowerCase(p.GetGender()) == 'f') {
                dropDownGender.setSelectedItem("Female");
            }
            
            memberTextList.get(0).setText(p.GetFirstName());
            memberTextList.get(1).setText(p.GetSurnameAtBirth());
            memberTextList.get(2).setText(p.GetSurnameMarriage());
            //gender drop down box placement
            textBiography.setText(p.GetBiography());
            memberTextList.get(3).setText(String.valueOf(p.GetStreetAddress().GetStreetNo()));
            memberTextList.get(4).setText(p.GetStreetAddress().GetStreetName());
            memberTextList.get(5).setText(p.GetStreetAddress().GetSuburb());
            memberTextList.get(6).setText(String.valueOf(p.GetStreetAddress().GetPostcode()));
            
            Person tempFather =  p.GetFamily().GetFather();
            Person tempMother =  p.GetFamily().GetMother();
            Person tempSpouse =  p.GetFamily().GetSpouse();
            ArrayList<Person> tempChildren = p.GetFamily().GetChildren();

            String textFather = "...";
            String textMother = "...";
            String textSpouse = "...";
            String textChildren = "";

            if(!tempFather.IsEmpty()) {
                textFather = tempFather.GetFirstName() + " " + tempFather.GetSurnameMarriage();
            }
            if(!tempMother.IsEmpty()) {
                textMother = tempMother.GetFirstName() + " " + tempMother.GetSurnameMarriage();
            }
            if(!tempSpouse.IsEmpty()) {
                textSpouse = tempSpouse.GetFirstName() + " " + tempSpouse.GetSurnameMarriage();
            }

            for(int i=0; i<tempChildren.size(); i++) {
                if(!tempChildren.get(i).IsEmpty()) {
                    if(i!=0) { //if not first item add newline
                        textChildren += "\n";
                    }
                    textChildren += tempChildren.get(i).GetFirstName() + " " + tempChildren.get(i).GetSurnameMarriage();
                }  
            }
            if(tempChildren.isEmpty()) {
                textChildren = "...";
            }

            memberTextList.get(7).setText(textFather);
            memberTextList.get(8).setText(textMother);
            memberTextList.get(9).setText(textSpouse);
            this.textChildren.setText(textChildren);

        }
        
    }
    
    public class FamilyTreePanel extends JPanel{
        
        private JPanel scrollPanel;
        
        public class memberButtonEvent implements ActionListener {
            Person p; //current person
            Color c; //current person button colour

            public memberButtonEvent(Person p,Color c) {
                this.p = p;
                this.c = c;
            }

            public void actionPerformed(ActionEvent e) {
                CommitChanges();
                FTP.PrintFamilyTree();
                currentPerson = p;
                PDP.PrintMember(p);
                UpdateBorders(c); //change border color to current button color
            }
        }

        /** Default constructor. */
        FamilyTreePanel() {
            //set layout
            TitledBorder titleBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,3), "Family Tree");
            setBorder(titleBorder);
            setBackground(Color.WHITE);
            setLayout(new GridBagLayout());
            CreateScrollPanel();
        }
        
        /**
         * Sets up the scroll panel for Family Tree Panel to be used with button diagram.
        */
        private void CreateScrollPanel() {
            
            scrollPanel = new JPanel();
            scrollPanel.setLayout(new GridBagLayout());
            JScrollPane scrollBar = new JScrollPane(scrollPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollBar.getVerticalScrollBar().setUnitIncrement(10);
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            add(scrollBar,c);
            
        }
        
        /**
         * For printing family tree. This method starts at the root person then traverses through
         * all attached nodes.
        */
        public void PrintFamilyTree() {

            removeAll();
            CreateScrollPanel();

            Person p = FT.GetRootPerson();

            if(!p.IsEmpty()) {

                if(!p.GetFamily().GetFather().IsEmpty()) {
                    PrintFamilyMember(p.GetFamily().GetFather(),"Father",1);
                }
                if(!p.GetFamily().GetMother().IsEmpty()) {
                    PrintFamilyMember(p.GetFamily().GetMother(),"Mother",1);
                }

                PrintFamilyMember(p,"Self",2);  

                if(!p.GetFamily().GetSpouse().IsEmpty()) {
                    PrintFamilyMember(p.GetFamily().GetSpouse(),"Spouse",2);
                }
                
                for(int i=0; i<p.GetFamily().GetChildren().size(); i++) {
                    PrintFamilyMembers(p.GetFamily().GetChildren().get(i),3);
                }

            }

            revalidate();
            repaint();
            
        }

        /**
         * For printing down the family tree hierarchy. 
         * @param p current person being printed
         * @param depth depth in family tree 
        */
        private void PrintFamilyMembers(Person p, int depth) {

            String text = "";
            if(!p.IsEmpty()) {

                PrintFamilyMember(p,"Child",depth);

                if(!p.GetFamily().GetSpouse().IsEmpty()) {
                    
                    if(!p.GetFamily().GetSpouse().GetFamily().GetFather().IsEmpty()) {
                        PrintFamilyMember(p.GetFamily().GetSpouse(),"🡓"+"Spouse",depth);
                        PrintFamilyMember("\u2937",depth); //prints blank
                        PrintFamilyMembers(p.GetFamily().GetSpouse().GetFamily().GetFather(),p.GetFamily().GetSpouse(),depth+5);
                        //sets spacing of char relative to child if exists
                        if(p.GetFamily().GetChildren().isEmpty()) {
                            PrintFamilyMember("🡓",depth); //prints blank
                        } else {
                            PrintFamilyMember("🡓",depth+1); //prints blank
                        } 
                    } else if(!p.GetFamily().GetSpouse().GetFamily().GetMother().IsEmpty()) {
                        PrintFamilyMember(p.GetFamily().GetSpouse(),"🡓"+"Spouse",depth);
                        PrintFamilyMember("\u2937",depth); //prints blank
                        PrintFamilyMembers(p.GetFamily().GetSpouse().GetFamily().GetMother(),p.GetFamily().GetSpouse(),depth+5);
                        //sets spacing of char relative to child if exists
                        if(p.GetFamily().GetChildren().isEmpty()) {
                            PrintFamilyMember("🡓",depth); //prints blank
                        } else {
                            PrintFamilyMember("🡓",depth+1); //prints blank
                        } 
                    } else {
                        PrintFamilyMember(p.GetFamily().GetSpouse(),"Spouse",depth); //original
                    }
                    
                }
                
                for(int i=0; i<p.GetFamily().GetChildren().size(); i++) {
                    PrintFamilyMembers(p.GetFamily().GetChildren().get(i),depth+1);
                }

            }

        }

        /**
         * For printing up the family tree hierarchy. 
         * @param p current person being printed
         * @param c current persons child from previous print (will not be reprinted)
         * @param depth depth in family tree 
        */
        private void PrintFamilyMembers(Person p, Person c, int depth) { //person and child
            
            if(!p.GetFamily().GetFather().IsEmpty()) {
                PrintFamilyMember(p,"🡓"+((Character.toLowerCase(p.GetGender())=='m')?"Father":"Mother"),depth);
                PrintFamilyMember("\u2937",depth); //prints blank
                PrintFamilyMembers(p.GetFamily().GetFather(),p,depth+5);
                //sets spacing of char relative to child if exists
                if(p.GetFamily().GetSpouse().IsEmpty()) {
                    if(p.GetFamily().GetChildren().size() == 1){
                        PrintFamilyMember("🡓",depth); //prints blank
                    } else {
                        PrintFamilyMember("🡓",depth+1); //prints blank
                    } 
                } else {
                    PrintFamilyMember("🡓",depth); //prints blank
                }
            } else if(!p.GetFamily().GetMother().IsEmpty()) {
                PrintFamilyMember(p,"🡓"+((Character.toLowerCase(p.GetGender())=='m')?"Father":"Mother"),depth);
                PrintFamilyMember("\u2937",depth); //prints blank
                PrintFamilyMembers(p.GetFamily().GetMother(),p,depth+5);
                //sets spacing of char relative to child if exists
                if(p.GetFamily().GetSpouse().IsEmpty()) {
                    if(p.GetFamily().GetChildren().size() == 1){
                        PrintFamilyMember("🡓",depth); //prints blank
                    } else {
                        PrintFamilyMember("🡓",depth+1); //prints blank
                    } 
                } else {
                    PrintFamilyMember("🡓",depth); //prints blank
                } 
            } else {
                PrintFamilyMember(p,((Character.toLowerCase(p.GetGender())=='m')?"Father":"Mother"),depth);
            }
            
            if(!p.GetFamily().GetSpouse().IsEmpty()) {
                    
                if(!p.GetFamily().GetSpouse().GetFamily().GetFather().IsEmpty()) {
                    PrintFamilyMember(p.GetFamily().GetSpouse(),"🡓"+((Character.toLowerCase(p.GetFamily().GetSpouse().GetGender())=='m')?"Father":"Mother"),depth);
                    PrintFamilyMember("\u2937",depth); //prints blank
                    PrintFamilyMembers(p.GetFamily().GetSpouse().GetFamily().GetFather(),p.GetFamily().GetSpouse(),depth+5);
                    //sets spacing of char relative to child if exists
                    if(p.GetFamily().GetChildren().isEmpty()) {
                        PrintFamilyMember("🡓",depth); //prints blank
                    } else {
                        PrintFamilyMember("🡓",depth+1); //prints blank
                    } 
                } else if(!p.GetFamily().GetSpouse().GetFamily().GetMother().IsEmpty()) {
                    PrintFamilyMember(p.GetFamily().GetSpouse(),"🡓"+((Character.toLowerCase(p.GetFamily().GetSpouse().GetGender())=='m')?"Father":"Mother"),depth);
                    PrintFamilyMember("\u2937",depth); //prints blank
                    PrintFamilyMembers(p.GetFamily().GetSpouse().GetFamily().GetMother(),p.GetFamily().GetSpouse(),depth+5);
                    //sets spacing of char relative to child if exists
                    if(p.GetFamily().GetChildren().isEmpty()) {
                        PrintFamilyMember("🡓",depth); //prints blank
                    } else {
                        PrintFamilyMember("🡓",depth+1); //prints blank
                    } 
                } else {
                    PrintFamilyMember(p.GetFamily().GetSpouse(),((Character.toLowerCase(p.GetFamily().GetSpouse().GetGender())=='m')?"Father":"Mother"),depth); //original
                }
                    
            }
                
            for(int i=0; i<p.GetFamily().GetChildren().size(); i++) {
                if(p.GetFamily().GetChildren().get(i) != c) {
                    PrintFamilyMembers(p.GetFamily().GetChildren().get(i),depth+1);
                }
            }
            
        }
        
        /**
         * Prints for empty instance of family member. Is called when family tree traverses 
         * up hierarchy. Includes formatting and addition of strings for aesthetic purposes.
         * @param text text for formatting
         * @param depth depth in family tree 
        */
        private void PrintFamilyMember(String text,int depth) {
            JLabel hierarchyBreak = new JLabel(text,JLabel.CENTER);
            hierarchyBreak.setBackground(Color.DARK_GRAY);
            hierarchyBreak.setPreferredSize(new Dimension(180,10));
            
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.anchor = GridBagConstraints.LINE_START;
            c.insets = new Insets(5,(20*depth),0,0); 
            scrollPanel.add(hierarchyBreak,c);
        }
        
        /**
         * Prints a family members button, including setting up events and formatting indentation 
         * depending on its depth in the family tree. Other formatting for text and color are 
         * also set.
         * @param p family member
         * @param p family member's relevant title
         * @param p depth in family tree
        */
        private void PrintFamilyMember(Person p, String title, int depth) {
            
            String tempGender = "\u26a7";
            if(Character.toLowerCase(p.GetGender()) == 'm') {
                tempGender = "\u2642";
            } else if(Character.toLowerCase(p.GetGender()) == 'f') {
                tempGender = "\u2640";
            }
            String labelText = " " + tempGender + "\uFF5c" + title + ": " + p.GetFirstName() + " " + p.GetSurnameMarriage();            
            
            Color randColor = GetColourFromSeed(depth);
            Border border = BorderFactory.createLineBorder(randColor);
            
            JButton memberButton = new JButton(labelText);
            memberButton.setPreferredSize(new Dimension(180,20)); //sets consitent button size
            memberButton.setBorder(border); 
            memberButton.setHorizontalAlignment(SwingConstants.LEFT); //sets text to left
            memberButton.addActionListener(new memberButtonEvent(p,randColor));
            
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.anchor = GridBagConstraints.LINE_START; //centers components
            c.insets = new Insets(5,(20*depth),0,0); //sets left indentation relative to depth in family tree

            scrollPanel.add(memberButton,c);

        }

    }
    
    /**
     * Updates person's data to that in text fields including any changes that may have been made.
    */
    private void CommitChanges() {
        
        if(currentPerson.GetFamily().GetSpouse().IsEmpty()) {
            currentPerson.SetGender((PDP.dropDownGender.getSelectedItem().toString().equals("Male")?'m':'f')); //set m or f based on drop down value
            
            //change mother/father role to children
            ArrayList<Person> children = currentPerson.GetFamily().GetChildren();
            if(Character.toLowerCase(currentPerson.GetGender()) == 'm') {
                for(int i=0; i<children.size(); i++) {
                    children.get(i).GetFamily().UpdateMother(new Person());
                    children.get(i).GetFamily().UpdateFather(currentPerson);
                }    
            } else if(Character.toLowerCase(currentPerson.GetGender()) == 'f') {
                for(int i=0; i<children.size(); i++) {
                    children.get(i).GetFamily().UpdateFather(new Person());
                    children.get(i).GetFamily().UpdateMother(currentPerson);
                }
            }
            
        }
        
        currentPerson.SetFirstName(PDP.memberTextList.get(0).getText());
        currentPerson.SetSurnameAtBirth(PDP.memberTextList.get(1).getText());
        currentPerson.SetSurnameMarriage(PDP.memberTextList.get(2).getText());
        currentPerson.SetBiography(PDP.textBiography.getText());
        
        currentPerson.SetStreetAddress(PDP.memberTextList.get(3).getText(),PDP.memberTextList.get(4).getText(),PDP.memberTextList.get(5).getText(),PDP.memberTextList.get(6).getText());
    }
    
    /**
     * Reprints frames with current family tree data.
    */
    private void RefreshFrame() {
        currentPerson = FT.GetRootPerson();
        FTP.PrintFamilyTree();
        PDP.PrintMember(currentPerson);
        UpdateBorders(Color.BLACK);
    }
    
    /**
     * Reprints frames with current family tree data.
     * @param p person to set to current person
    */
    private void RefreshFrame(Person p) {
        currentPerson = p;
        FTP.PrintFamilyTree();
        PDP.PrintMember(currentPerson);
        UpdateBorders(Color.BLACK);
    }
    
    /**
     * Updates panel borders with new colour.
     * @param c colour to add to borders
    */
    private void UpdateBorders(Color c) {
        CompoundBorder compoundBorder = new CompoundBorder(BorderFactory.createLineBorder(c),BorderFactory.createLineBorder(Color.BLACK,3));
        TitledBorder titleBorder = BorderFactory.createTitledBorder(compoundBorder, "Family Tree");
        FTP.setBorder(titleBorder);
        titleBorder = BorderFactory.createTitledBorder(compoundBorder, "Person Details");
        PDP.setBorder(titleBorder);
    }
    
    /**
     * Produces a consistent, seemingly random color based on number passed in.
     * @param num as number (as seed for color)
     * @return Color
    */
    private Color GetColourFromSeed(int num) {

        int red = 0;
        int green = 0;
        int blue = 0;   
            
        for(int i=0; i<(num % 6); i++) {   
            if((num % 6) == 0) {
                if(blue < 2) {
                    blue++;
                } else {
                    if(green < 2) {
                        green++;
                    } else {   
                        if(red < 2) {
                            red++;
                        }
                    }
                } 
            } else if((num % 5) == 0) {
                if(green < 2) {
                    green++;
                } else {
                    if(red < 2) {
                        red++;
                    } else {   
                        if(blue < 2) {
                            blue++;
                        }
                    }
                } 
            } else if((num % 4) == 0) {
                if(red < 2) {
                    red++;
                } else {
                    if(blue < 2) {
                        blue++;
                    } else {   
                        if(green < 2) {
                            green++;
                        }
                    }
                } 
            } else if((num % 3) == 0) {
                if(green < 2) {
                    green++;
                } else {
                    if(blue < 2) {
                        blue++;
                    } else {   
                        if(red < 2) {
                            red++;
                        }
                    }
                } 
            } else if((num % 2) == 0) {
                if(blue < 2) {
                    blue++;
                } else {
                    if(red < 2) {
                        red++;
                    } else {   
                        if(green < 2) {
                            green++;
                        }
                    }
                }
            } else {
                if(red < 2) {
                    red++;
                }
                if(green < 2) {
                    green++;
                }
                if(blue < 2) {
                    blue++;
                }
                if(red < 2) {
                    red++;
                } else {
                    if(green < 2) {
                        green++;
                    } else {   
                        if(blue < 2) {
                            blue++;
                        }
                    }
                }
            }             
        }
                         
        red *= 128;
        green *= 128;
        blue *= 128;
            
        if(red == 256) {
            red--;
        }
        if(green == 256) {
            green--;
        }
        if(blue == 256) {
            blue--;
        }      
        
        return new Color(red,green,blue);

    }
}
