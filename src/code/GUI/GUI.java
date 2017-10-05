package code.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

//import java_practice.ScrollDemo2.DrawingPane;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
 

//import java_practice.ScrollDemo2.DrawingPane;


public class GUI{
	private JTree dirStructure;
	
    private ArrayList<Image> circuitElementImages = new ArrayList<Image>();
    private ArrayList<Integer> x_vals = new ArrayList<Integer>();
    private ArrayList<Integer> y_vals = new ArrayList<Integer>();
    private ArrayList<Image> elementTypes = new ArrayList<Image>();
    
	public void run() {
		
		//Test with multiple reviewers
		
		//Create JFrame
		JFrame frame = new JFrame("LogiCAD");

		// Adds LogiCAD logo as icon to upper left JFrame Window
		try {
			Image img = ImageIO.read(getClass().getResource("images/logicad_logo_red_40_40.png"));
			ImageIcon logo_icon = new ImageIcon(img);
			frame.setIconImage(logo_icon.getImage());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		JLabel gridSpaceLabel = new JLabel();
//		
//		Image circuit_image;
//		try {
//			circuit_image = ImageIO.read(getClass().getResourceAsStream("images/sample_circuit2.png"));
//			gridSpaceLabel.setIcon(new ImageIcon(circuit_image));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		gridSpaceLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		gridSpaceLabel.setVerticalAlignment(SwingConstants.CENTER);
//		
//		JScrollPane gridPane = new JScrollPane(gridSpaceLabel);
		
//////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////TEST WORKSPACE///////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////		
		 
		/* ScrollDemo2.java requires no other files. */
		class ScrollDemo2 extends JPanel
		                         implements MouseListener {
		    private Dimension area; //indicates area taken up by graphics
		    private Vector<Rectangle> circles; //coordinates used to draw graphics
		    private JPanel drawingPane;
		    private int x;
		    private int y;
		    private ArrayList<Image> circuitElementImages = new ArrayList<Image>();
		    private ArrayList<Integer> x_vals = new ArrayList<Integer>();
		    private ArrayList<Integer> y_vals = new ArrayList<Integer>();
		    private ArrayList<Image> elementTypes = new ArrayList<Image>();
		 
		    private final Color colors[] = {
		        Color.red, Color.blue, Color.green, Color.orange,
		        Color.cyan, Color.magenta, Color.darkGray, Color.yellow};
		    private final int color_n = colors.length;
		 
		    public ScrollDemo2() {
		        super(new BorderLayout());
		        
		        initializeImageContainer();
		 
		        area = new Dimension(0,0);
		        circles = new Vector<Rectangle>();
		 
		        //Set up the instructions.
//		        JLabel instructionsLeft = new JLabel(
//		                        "Click left mouse button to place a circle.");
//		        JLabel instructionsRight = new JLabel(
//		                        "Click right mouse button to clear drawing area.");
//		        JPanel instructionPanel = new JPanel(new GridLayout(0,1));
//		        instructionPanel.setFocusable(true);
//		        instructionPanel.add(instructionsLeft);
//		        instructionPanel.add(instructionsRight);
		 
		        //Set up the drawing area.
		        drawingPane = new DrawingPane();
		        drawingPane.setBackground(Color.white);
		        drawingPane.addMouseListener(this);
		 
		        //Put the drawing area in a scroll pane.
		        JScrollPane scroller = new JScrollPane(drawingPane);
		        scroller.setPreferredSize(new Dimension(200,200));
		 
		        //Lay out this demo.
		        //add(instructionPanel, BorderLayout.PAGE_START);
		        add(scroller, BorderLayout.CENTER);
		    }
		 
		    /** The component inside the scroll pane. */
		    class DrawingPane extends JPanel {
		        protected void paintComponent(Graphics g) {
		            super.paintComponent(g);
		            
		            for(int i = 0; i < circuitElementImages.size(); ++i) {
		            	g.drawImage(circuitElementImages.get(i), x_vals.get(i), y_vals.get(i), null);
		            }
		           
		            	 
//		            Rectangle rect;
//		            for (int i = 0; i < circles.size(); i++) {
//		                rect = circles.elementAt(i);
//		                g.setColor(colors[(i % color_n)]);
//		                g.fillOval(rect.x, rect.y, rect.width, rect.height);
//		            }
		            
		            
		        }
		    }
		    
		    public void initializeImageContainer() {
		    	Image img = null;
		    	for(int i = 0; i < 9; ++i) {
		    		switch (i) {
		    			case 0:
		    				
		    	            try {
		    	    			img = ImageIO.read(getClass().getResource("images/and_with_text.png"));
		    	    		} catch (IOException exp) {
		    	    			exp.printStackTrace();
		    	    		}
		    	            elementTypes.add(img);
		    				break;
		    			case 1:
		    				
		    	            try {
		    	    			img = ImageIO.read(getClass().getResource("images/and_with_text.png"));
		    	    		} catch (IOException exp) {
		    	    			exp.printStackTrace();
		    	    		}
		    	            elementTypes.add(img);
		        			break;
		    			case 2:
		    				
		    	            try {
		    	    			img = ImageIO.read(getClass().getResource("images/and_with_text.png"));
		    	    		} catch (IOException exp) {
		    	    			exp.printStackTrace();
		    	    		}
		    	            elementTypes.add(img);
		        			break;
		    			case 3:
		    				
		    	            try {
		    	    			img = ImageIO.read(getClass().getResource("images/and_with_text.png"));
		    	    		} catch (IOException exp) {
		    	    			exp.printStackTrace();
		    	    		}
		    	            elementTypes.add(img);
		        			break;
		    			case 4:
		    				try {
		    	    			img = ImageIO.read(getClass().getResource("images/and_with_text.png"));
		    	    		} catch (IOException exp) {
		    	    			exp.printStackTrace();
		    	    		}
		    				elementTypes.add(img);
		        			break;
		    			case 5:
		    				try {
		    	    			img = ImageIO.read(getClass().getResource("images/and_with_text.png"));
		    	    		} catch (IOException exp) {
		    	    			exp.printStackTrace();
		    	    		}
		    				elementTypes.add(img);
		        			break;
		    			case 6:
		    				try {
		    	    			img = ImageIO.read(getClass().getResource("images/and_with_text.png"));
		    	    		} catch (IOException exp) {
		    	    			exp.printStackTrace();
		    	    		}
		    				elementTypes.add(img);
		        			break;
		    			case 7:try {
			    			img = ImageIO.read(getClass().getResource("images/and_with_text.png"));
				    		} catch (IOException exp) {
				    			exp.printStackTrace();
				    		}
		    				elementTypes.add(img);
		        			break;
		    			case 8:
		    				try {
		    	    			img = ImageIO.read(getClass().getResource("images/and_with_text.png"));
		    	    		} catch (IOException exp) {
		    	    			exp.printStackTrace();
		    	    		}
		    				elementTypes.add(img);
		        			break;
		    		}
		    			
		    	}
		    	
		    }
		    
		  //Handle mouse events.
		    public void mouseReleased(MouseEvent e) {
		        final int W = 100;
		        final int H = 100;
		        boolean changed = false;
		        if (SwingUtilities.isRightMouseButton(e)) {
		            //This will clear the graphic objects.
		            circles.removeAllElements();
		            area.width=0;
		            area.height=0;
		            changed = true;
		        } else {
		        	
		            int x = e.getX() - W/2;
		            int y = e.getY() - H/2;
		            this.x = x;
		            this.y = y;
		            System.out.println(this.x);
		            System.out.println(this.y);
		            
		            circuitElementImages.add(elementTypes.get(0));
		            x_vals.add(new Integer(e.getX()));
		            y_vals.add(new Integer(e.getY()));
		            
		            if (x < 0) x = 0;
		            if (y < 0) y = 0;
		            Rectangle rect = new Rectangle(x, y, W, H);
		            circles.addElement(rect);
		            drawingPane.scrollRectToVisible(rect);
		 
		            int this_width = (x + W + 2);
		            if (this_width > area.width) {
		                area.width = this_width; changed=true;
		            }
		 
		            int this_height = (y + H + 2);
		            if (this_height > area.height) {
		                area.height = this_height; changed=true;
		            }
		        }
		        if (changed) {
		            //Update client's preferred size because
		            //the area taken up by the graphics has
		            //gotten larger or smaller (if cleared).
		            drawingPane.setPreferredSize(area);
		 
		            //Let the scroll pane know to update itself
		            //and its scrollbars.
		            drawingPane.revalidate();
		        }
		        drawingPane.repaint();
		    }
		    public void mouseClicked(MouseEvent e){}
		    public void mouseEntered(MouseEvent e){}
		    public void mouseExited(MouseEvent e){}
		    public void mousePressed(MouseEvent e){}
		   
		}
		JComponent newContentPane = new ScrollDemo2();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		
//////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////TEST WORKSPACE///////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension d = new Dimension(1366,768);
		
		frame.setPreferredSize(d);
		
		
		// Adds JToolbar and buttons to JFrame
		
		JToolBar gates_and_io = new JToolBar("Gates & I/O");
		gates_and_io.setRollover(true);
		
		JButton button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/and_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/or_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/not_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/xor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/nand_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/nor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/xnor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/input.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
		
		
	
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/output.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gates_and_io.add(button);
	
		frame.getContentPane().add(gates_and_io, BorderLayout.NORTH);
		
		// End of JToolbar code
		
		// Add side-bar
		// Expandable List     
		DefaultMutableTreeNode falsetop = new DefaultMutableTreeNode("");
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Project1");
		DefaultMutableTreeNode child = new DefaultMutableTreeNode("Example Circuit One");
		DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("32bit ALU");
		DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("Carry Look Ahead Adder");
		DefaultMutableTreeNode top2 = new DefaultMutableTreeNode("32bit CPU");
		falsetop.add(top);
		falsetop.add(top2);
		top.add(child);
		top2.add(child2);
		top2.add(child3);
		dirStructure = new JTree(falsetop);
		dirStructure.setRootVisible(false);
		JScrollPane treeView = new JScrollPane(dirStructure);
		treeView.setPreferredSize(new Dimension(200,768));
		dirStructure.expandRow(1);
		dirStructure.expandRow(2);
		
		         
		
		frame.getContentPane().add(treeView, BorderLayout.WEST);
		//frame.getContentPane().add(sideBar, BorderLayout.WEST);
		// End add side-bar
		
		
		// End example circuit button
		
		//frame.getContentPane().add(gridPane, BorderLayout.CENTER);
		
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
		menuItem = new JMenuItem("Set Project Directory");
		menu.add(menuItem);
		
		//Add File Menu to Main Menu
		menuBar.add(menu);

		//Create Edit Menu
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Edit Menu");
		
		menuItem = new JMenuItem("Options");
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		//Create View Menu
		menu = new JMenu("View");
		menu.setMnemonic(KeyEvent.VK_V);
		menu.getAccessibleContext().setAccessibleDescription(
		        "View Menu");
		
		menuItem = new JMenuItem("Toggle Side-Bar");
		menu.add(menuItem);
		
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
						JLabel abouttext = new JLabel();
						abouttext.setHorizontalTextPosition(SwingConstants.CENTER);
						abouttext.setVerticalTextPosition(SwingConstants.NORTH);
						abouttext.setPreferredSize(new Dimension(1366,768));
						abouttext.setVerticalAlignment(SwingConstants.NORTH);
						abouttext.setText("<html> <h3> Welcome to LogiCAD! </h3> <br>" +  
								"LogiCAD is a digital circuit Computer-Aided Design application. " + 
								"As you dive into our software you may notice several tools and menus at your disposal, so lets take a look at what we have to offer:<br>" +
								"<br>" + 
								"<br>" + 
								"Top Menu Bar:<br>" + 
								"<br>" + 
								"-File: Drop down menu of the following:<br>" + 
								"<br>" + 
								"&ensp;  &ensp;  Save:  User is able to save project files.<br>" + 
								"&ensp;  &ensp;  Load:  User is able to load previouse project files.<br>" + 
								"&ensp;  &ensp;  Import Module:  User is able to save circuit designs as modules that can be loaded and used in future projects.<br>" + 
								"&ensp;  &ensp;  Export Module:  A module is a completed circuit that is used after being saved as an element of another circuit.<br>" + 
								"<br>" + 
								"-Edit: Drop down menu of the following:<br>" + 
								"<br>" + 
								"-View: Drop down menu of the following:<br>" + 
								"<br>" + 
								"-Help: Drop down menu of the following:<br>" + 
								"<br>" + 
								"&ensp;  &ensp;  About:  Information about the software and button features.<br>" + 
								"<br>" + 
								"<br>" + 
								"<br>" + 
								"Gate Menu Bar:<br>" + 
								"<br>" + 
								"-AND Gate Button: An AND gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
								"\r\n" + 
								"-OR Gate Button: An OR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
								"\r\n" + 
								"-NOT Gate Button: A NOT gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given one input and will provide an output.<br>" + 
								"\r\n" + 
								"-XOR Gate Button: An XOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
								"\r\n" + 
								"-NAND Gate Button: A NAND gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
								"\r\n" + 
								"-NOR Gate Button: A NOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
								"\r\n" + 
								"-XNOR Gate Button: A XNOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
								"\r\n" + 
								"-Input Gate Button: An Input wire will be presented to place on any logic gate or to attach to any other wires.<br>" + 
								"\r\n" + 
								"-Output Gate Button: An Output wire will be presented to place on any logic gate or to attach to any other wires.<br>" + 
								"<br>" + 
								"<br>" + 
								"<br>" +
								"Left Side Panel:\r\n" + 
								"<br>" + 
								"<br>" + 
								"-This space displays your current working directory after you specify the file path.<br>" + 
								"<br>" + 
								"<br>" + 
								"Grid Workspace:<br>" + 
								"<br>" + 
								"-This is the area a user will be given to work in.<br>" +
								"<br>" +
								"<br>");
								
					
						
						JScrollPane aboutpane = new JScrollPane(abouttext);
						JPanel about = new JPanel();
						about.add(aboutpane);
						aboutpane.getViewport().add(abouttext);
						aboutpane.setPreferredSize(d);
						JOptionPane.showMessageDialog(null, aboutpane);
						
						
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
