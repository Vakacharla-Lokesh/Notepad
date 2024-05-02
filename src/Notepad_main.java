import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;


public class Notepad_main extends JFrame implements KeyListener, ActionListener{
    JTextArea writing = new JTextArea();
    JMenuBar jmb = new JMenuBar();
    String name = "";
    int fontsize = 10;
    JLabel statusLabel = new JLabel();
    boolean status_lab = false;
    Notepad_main(){
        // SETTING LAYOUT FOR NOTEPAD GUI
        setLayout(new BorderLayout());

        // WRITABLE TEXT AREA FOR USERS
        writing.setSize(475, 475);;
        add(writing);

        // ADDING FILE MENU
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem renaMenuItem = new JMenuItem("Rename");
        JMenuItem savItem = new JMenuItem("Save");
        JMenuItem exMenuItem = new JMenuItem("Exit");
        
        // ADDING MENU ITEMS TO FILE MENU
        fileMenu.add(openItem);
        fileMenu.add(renaMenuItem);
        fileMenu.add(savItem);
        fileMenu.add(exMenuItem);

        // ADDING FILE MENU TO MENU BAR
        jmb.add(fileMenu);

        // ADDING ACTION LISTENERS TO MENU ITEMS
        openItem.addActionListener(this);
        renaMenuItem.addActionListener(this);
        savItem.addActionListener(this);
        exMenuItem.addActionListener(this);

        // ADDING SHORTCUT KEYS TO FILE MENU ITEMS
        fileMenu.setMnemonic(KeyEvent.VK_F); //ALT + F TO ACCESS FILE MENU
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK)); //CTRL + O FOR OPENING A FILE
        renaMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK)); //CTRL + F2 FOR RENAMING
        savItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK)); //CTRL + S FOR SAVING A FILE
        exMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK)); //CTRL + E FOR EXITING A FILE

        // ADDING EDIT MENU TO NOTEPAD
        JMenu editMenu = new JMenu("Edit");
        JMenuItem fontmi = new JMenuItem("Font");
        JMenuItem sizemi = new JMenuItem("Size");

        // ADDING STYLES SUBMENU TO EDIT MENU
        JMenu stylemi = new JMenu("Style");

        // CREATING STYLES MENUITEMS: NORMAL, BOLD, ITALIC FOR TEXT
        JRadioButtonMenuItem normalmi = new JRadioButtonMenuItem("Normal", true);
        JRadioButtonMenuItem boldmi = new JRadioButtonMenuItem("Bold");
        JRadioButtonMenuItem italicmi = new JRadioButtonMenuItem("Italic");
        JRadioButtonMenuItem bolditalicmi = new JRadioButtonMenuItem("Bold Italic");

        // ADDING A BUTTON GROUP TO ENSURE ONLY ONE FONT STYLE IS CHOSEN AT A TIME
        ButtonGroup fontstyle = new ButtonGroup();
        fontstyle.add(normalmi);
        fontstyle.add(boldmi);
        fontstyle.add(italicmi);
        fontstyle.add(bolditalicmi);

        // ADDING MENU ITEMS TO STYLES MENU
        stylemi.add(normalmi);
        stylemi.add(boldmi);
        stylemi.add(italicmi);
        stylemi.add(bolditalicmi);

        // ADDING ACTIONLISTENERS TO EACH FONT STYLE MENU ITEM IN THE FORM OF LAMBDA EXPRESSIONS
        normalmi.addActionListener((ae) -> {
            writing.setFont(new Font("Comic Sans", Font.PLAIN,12));
        });
        boldmi.addActionListener((ae) -> {
            writing.setFont(new Font("Comic Sans", Font.BOLD,12));
        });
        italicmi.addActionListener((ae) ->{
            writing.setFont(new Font("Comic Sans", Font.ITALIC,12));
        });
        bolditalicmi.addActionListener((ae) ->{
            writing.setFont(new Font("Comic Sans", Font.BOLD|Font.ITALIC, 16));
        });

        // ADDING SHORTCUT KEYS TO EDIT MENU ITEM
        editMenu.setMnemonic(KeyEvent.VK_E);

        // ADDING MENU ITEMS TO EDIT MENU
        editMenu.add(fontmi);
        editMenu.add(sizemi);
        editMenu.add(stylemi);
        // ADDING EDITMENU TO MENUBAR
        jmb.add(editMenu);

        // ADDING ACTIONLISTENER TO FONT SIZE AND FONT
        fontmi.addActionListener(this);
        sizemi.addActionListener(this);

        JMenu viewMenu = new JMenu("View");
        JMenu modeItem = new JMenu("Mode");
        JMenuItem statusItem = new JMenuItem("Status");

        // CREATING RADIOBUTTON MENU ITEMS FOR MODE
        JRadioButtonMenuItem lightItem = new JRadioButtonMenuItem("Light", true);
        lightItem.addActionListener(this);
        JRadioButtonMenuItem darkItem = new JRadioButtonMenuItem("Dark");
        darkItem.addActionListener(this);

        // CREATING A MODE RADIO BUTTON GROUP
        ButtonGroup modegrp = new ButtonGroup();
        modegrp.add(lightItem);
        modegrp.add(darkItem);

        // ADDING MODE MENU ITEMS TO MODE SUB MENU
        modeItem.add(lightItem);
        modeItem.add(darkItem);
        
        // STATUS ITEM ACTION LISTENER
        statusItem.addActionListener(this);

        // ADDING MENU ITEMS TO VIEW MENU
        viewMenu.add(modeItem);
        viewMenu.add(statusItem);
        
        // ADDING VIEW MENU TO MENU BAR
        jmb.add(viewMenu);

        // ADDING MENU BAR TO NOTEPAD
        setJMenuBar(jmb);

        // HOW DO I ADD A STATUS BAR THAT DISPLAYS WORD COUNT, FONT SIZE AND STYLE?
        // SETTINGS OF THE JFRAME
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("New" + name);
        setSize(500, 500);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comstr = e.getActionCommand();
        if(comstr.equals("Rename")){
            new MydialogBox(this, comstr);
        }

        if(comstr.equals("Save")){
            new MydialogBox(this, comstr);
        }
        
        if(comstr.equals("Size")){
            new MydialogBox(this, comstr, writing);
        }

        if(comstr.equals("Exit")){
            dispose();
        }

        if(comstr.equals("Light")){
            getContentPane().setBackground(null);
            writing.setBackground(null);
            writing.setForeground(Color.black);
            jmb.setBackground(null);
            jmb.setForeground(Color.black);
        }

        else if(comstr.equals("Dark")){
            getContentPane().setBackground(Color.darkGray);
            writing.setBackground(Color.darkGray);
            writing.setForeground(Color.white);
            jmb.setBackground(Color.gray);
            jmb.setForeground(Color.white);
        }

        if (comstr.equals("Save")) {
            JFileChooser save = new JFileChooser(); 
            int option = save.showSaveDialog(this); 

            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
                    out.write(this.writing.getText()); 
                    out.close(); 
                } catch (Exception ex) { 
                    System.out.println(ex.getMessage());
                }
            }
        }
        if (comstr.equals("Open")) {
            JFileChooser open = new JFileChooser(); 
            int option = open.showOpenDialog(this); 

            if (option == JFileChooser.APPROVE_OPTION) {
                writing.setText(""); 
                try {
                    Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
                    while (scan.hasNext()){
                        writing.append(scan.nextLine() + "\n"); 
                    }
                    scan.close();
                } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                
            }
        }

        if(comstr.equals("Status") && status_lab == false){
            status_lab = true;
            statusLabel.setText("Words: " + writing.getText().length() + " Font Size: " + this.fontsize);
            this.add(statusLabel, BorderLayout.SOUTH);
            // this.getMenuBar().getMenu(2).getItem(2).setLabel("Status: true");
        }
        else{
            status_lab = false;
            this.remove(statusLabel);
        }
    }
}


class MydialogBox extends Dialog{
    JLabel namelabel = new JLabel("Name: ");
    JTextField nameField = new JTextField();
    JButton okButton = new JButton("OK");
    JButton cancelButton = new JButton("Cancel");

    public MydialogBox(JFrame Parent, String title) {
        super(Parent, title, false);
        if(title.equals("Rename")){
            add(namelabel);
            add(nameField);
            add(okButton);
            okButton.addActionListener((ae) -> {
                Parent.setTitle(nameField.getText());
                dispose();
            });
            add(cancelButton);
            cancelButton.addActionListener((ae) -> dispose());
            setLayout(new GridLayout(2, 2, 40, 40));
            setSize(200, 150);
            setVisible(true); 
        }
    }
    
    public MydialogBox(JFrame parent, String title, JTextArea writable){
        super(parent, title, false);
        namelabel.setText("Size: ");
        add(namelabel);
        add(nameField);
        add(okButton);
        okButton.addActionListener((ae) -> {
            int size = Integer.parseInt(nameField.getText());
            writable.setFont(new Font("Comic Sans", Font.BOLD, size));
            dispose();
        });
        add(cancelButton);
        cancelButton.addActionListener((ae) -> dispose());
        setLayout(new GridLayout(2, 2, 40, 40));
        setSize(200, 150);
        setVisible(true);
    }
}