package code.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.sun.glass.events.MouseEvent;

public class GUI {
	private JTree dirStructure;
	public void run() {
		
		//Create JFrame
		JFrame frame = new JFrame("LogiCAD");
		JLabel gridSpaceLabel = new JLabel();
		
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
		
		gates_and_io.addSeparator();
		
		button = new JButton("INPUT");
		gates_and_io.add(button);
		
		gates_and_io.addSeparator();
	
		button = new JButton("OUTPUT");
		gates_and_io.add(button);
	
		frame.getContentPane().add(gates_and_io, BorderLayout.NORTH);
		
		// End of JToolbar code
		
		// Add side-bar
		JPanel sideBar = new JPanel();
		sideBar.setPreferredSize(new Dimension(200, 768));
		
		// Expandable List
		         
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Example Project Directory");
		DefaultMutableTreeNode child = new DefaultMutableTreeNode("Example Child");
		top.add(child);
		dirStructure = new JTree(top);
		JScrollPane treeView = new JScrollPane(dirStructure);
		
		treeView.setPreferredSize(new Dimension(200,768));
		         
		
		frame.getContentPane().add(treeView, BorderLayout.WEST);
		//frame.getContentPane().add(sideBar, BorderLayout.WEST);
		// End add side-bar
		
		
		// Add example circuit button to Toolbar
		
		button = new JButton("DISPLAY EXAMPLE CIRCUIT");
		gates_and_io.addSeparator();
		gates_and_io.add(button);
		
		// End example circuit button
		
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
