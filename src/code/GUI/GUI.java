package code.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class GUI {
	
	public void run() {
		
		//Create JFrame
		JFrame frame = new JFrame("LogiCAD");
		JLabel gridSpaceLabel = new JLabel("Grid Workspace");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension d = new Dimension(1366,768);
		
		frame.setPreferredSize(d);
		
		
		// Adds JToolbar and buttons to JFrame
		
		JToolBar gates_and_io = new JToolBar("Gates & I/O");
		gates_and_io.setRollover(true);
		
		JButton button = new JButton();
		try {
			Image img = ImageIO.read(this.getClass().getResource("/and_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(this.getClass().getResource("/or_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(this.getClass().getResource("/not_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(this.getClass().getResource("/xor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(this.getClass().getResource("/nand_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(this.getClass().getResource("/nor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(this.getClass().getResource("/xnor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton("INPUT");
		gates_and_io.add(button);
	
		button = new JButton("OUTPUT");
		gates_and_io.add(button);
	
		frame.getContentPane().add(gates_and_io, BorderLayout.NORTH);
		
		// End of JToolbar code
		
		frame.getContentPane().add(gridSpaceLabel, BorderLayout.CENTER);
		
		frame.getContentPane().setBackground(Color.GRAY);  //Clayton Test
		
		//Variables used for Menus
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		//Create the Main Menu Bar
		menuBar = new JMenuBar();

		//Create File Menu
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription(
		        "File Menu");
		
		//Add menu items to File Menu
		menuItem = new JMenuItem("Save");
		menu.add(menuItem);
		menuItem = new JMenuItem("Load");
		menu.add(menuItem);
		menuItem = new JMenuItem("Import Module");
		menu.add(menuItem);
		menuItem = new JMenuItem("Export Module");
		menu.add(menuItem);
		
		//Add File Menu to Main Menu
		menuBar.add(menu);

		//Create Edit Menu
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Edit Menu");
		menuBar.add(menu);
		
		//Create View Menu
		menu = new JMenu("View");
		menu.setMnemonic(KeyEvent.VK_V);
		menu.getAccessibleContext().setAccessibleDescription(
		        "View Menu");
		menuBar.add(menu);
		
		//Create Help Menu
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Help Menu");
		
		//Add About menu item
		menuItem = new JMenuItem("About");
		menuItem.setMnemonic(KeyEvent.VK_A);
		
		
		//Create an action listener for the about menu
		menuItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Welcome to LogiCAD!\r\n" + 
						"\r\n" + 
						"LogiCAD is a digital circuit Computer-Aided Design application.\r\n" + 
						"\r\n" + 
						"As you dive into our software you may notice several tools and menus at your disposal, so lets take a look at what we have to offor:\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"Top Menu Bar:\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"-File: Drop down menu of the following:\r\n" + 
						"\r\n" + 
						"	Save: User is able to save project files.\r\n" + 
						"	Load: User is able to load previouse project files.\r\n" + 
						"	Import Module: User is able to save circuit designs as modules that can be loaded and used in future projects.  \r\n" + 
						"	Export Module: A module is a completed circuit that is used after being saved as an element of another circuit.\r\n" + 
						"\r\n" + 
						"-Edit: Drop down menu of the following:\r\n" + 
						"\r\n" + 
						"-View: Drop down menu of the following:\r\n" + 
						"\r\n" + 
						"-Help: Drop down menu of the following:\r\n" + 
						"\r\n" + 
						"	About: Information about the software and button features.\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"Gate Menu Bar:\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"-AND Gate Button: An AND gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.\r\n" + 
						"\r\n" + 
						"-OR Gate Button: An OR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.\r\n" + 
						"\r\n" + 
						"-NOT Gate Button: A NOT gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given one input and will provide an output.\r\n" + 
						"\r\n" + 
						"-XOR Gate Button: An XOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.\r\n" + 
						"\r\n" + 
						"-NAND Gate Button: A NAND gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.\r\n" + 
						"\r\n" + 
						"-NOR Gate Button: A NOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.\r\n" + 
						"\r\n" + 
						"-XNOR Gate Button: A XNOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.\r\n" + 
						"\r\n" + 
						"-Input Gate Button: An Input wire will be presented to place on any logic gate or to attach to any other wires.\r\n" + 
						"\r\n" + 
						"-Output Gate Button: An Output wire will be presented to place on any logic gate or to attach to any other wires.\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"Left Side Panel:\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"-This space displays your current working directory after you specify the file path.\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"Grid Workspace:\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"-This is the area a user will be given to work in.");
			}

		});
		
		menu.add(menuItem);
		
		//Add Help Menu to Main Menu
		menuBar.add(menu);
			
		//Set Main Menu as JMenuBar
		frame.setJMenuBar(menuBar);
		
		//Pack contents of JFrame nicely
		frame.pack();
		
		//Make JFrame visible
		frame.setVisible(true);
			
	}
}
