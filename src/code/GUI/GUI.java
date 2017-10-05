// This code from an Oracle tutorial handles putting elements into the
// workspace.

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 

package code.GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;



import javax.swing.event.MouseInputAdapter;
import java.io.IOException;



public class GUI {
	
	private static final int INVALID = -1;
	private static final int AND_BUTTON = 0;
	private static final int OR_BUTTON = 1;
	private static final int NOT_BUTTON = 2;
	private static final int XOR_BUTTON = 3;
	private static final int NAND_BUTTON = 4;
	private static final int NOR_BUTTON = 5;
	private static final int XNOR_BUTTON = 6;
	private static final int INPUT_BUTTON = 7;
	private static final int OUTPUT_BUTTON = 8;
	
	private int circuitElementButtonClicked = INVALID;
	
	private JTree dirStructure;
	public void run() {
		//Create JFrame
		JFrame frame = new JFrame("LogiCAD");
		
		// Adds LogiCAD logo as icon to upper left JFrame Window and in task bar.
				try {
					Image img = ImageIO.read(getClass().getResource("images/logicad_logo_silver_40_40.png"));
					ImageIcon logo_icon = new ImageIcon(img);
					frame.setIconImage(logo_icon.getImage());
					
				} catch (IOException e) {
					e.printStackTrace();
				}
		
		// gridSpaceLabel is the workspace for the user
		
				//JLabel gridSpaceLabel = new JLabel();
		
		// For the prototype we put an example circuit in the workspace
		// to give the user an example of what he can create in the
		// MVP.
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
				
				
//#############################################################################
// ScrollDemo2 from Oracle Tutorial Code begin
				/* ScrollDemo2.java requires no other files. */
				class ScrollDemo2 extends JPanel
				                         implements MouseListener {
				    private Dimension area; //indicates area taken up by graphics
				    //private Vector<Rectangle> circles; //coordinates used to draw graphics
				    private JPanel drawingPane;
				    private int x;
				    private int y;
				    private ArrayList<Image> circuitElementImages = new ArrayList<Image>();
//				    private ArrayList<Integer> x_vals = new ArrayList<Integer>();
//				    private ArrayList<Integer> y_vals = new ArrayList<Integer>();
				    private ArrayList<Image> elementImageTypes = new ArrayList<Image>();
				    
				    private ArrayList<ImageCoordAndType> imageInfo = new ArrayList<ImageCoordAndType>();
				 
				    private final Color colors[] = {
				        Color.red, Color.blue, Color.green, Color.orange,
				        Color.cyan, Color.magenta, Color.darkGray, Color.yellow};
				    private final int color_n = colors.length;
				 
				    public ScrollDemo2() {
				        super(new BorderLayout());
				        
				        initializeImageContainer();
				 
				        area = new Dimension(0,0);
				        //circles = new Vector<Rectangle>();
				 
				        
				 
				        //Set up the drawing area.
				        drawingPane = new DrawingPane();
				        drawingPane.setBackground(Color.white);
				        drawingPane.addMouseListener(this);
				 
				        //Put the drawing area in a scroll pane.
				        JScrollPane scroller = new JScrollPane(drawingPane);
				        scroller.setPreferredSize(new Dimension(1366,768));
				 
				        //Lay out this demo.
				       
				        add(scroller, BorderLayout.CENTER);
				    }
				    
				    
				    class ImageCoordAndType{
				    	private int elementType;
				    	private int centerImageX;
				    	private int centerImageY;
				    	private int upperLeftImageX;
				    	private int upperLeftImageY;
				    	private int upperLeftBufferX;
				    	private int upperLeftBufferY;
				    	
				    	public ImageCoordAndType(int type, int upperLeftX, int upperLeftY) {
				    		elementType = type;
				    		upperLeftImageX = upperLeftX;
				    		upperLeftImageY = upperLeftY;
				    		centerImageX = upperLeftX + 50;
				    		centerImageY = upperLeftY + 25;
				    		upperLeftBufferX = upperLeftX - 20;
				    		upperLeftBufferY = upperLeftY - 10;
				    			
				    	}
				    	
				    	int getElementType() {return elementType;}
						int getUpperLeftImageX() {return upperLeftImageX;}
						int getUpperLeftImageY () {return upperLeftImageY;}
						int getCenterImageX() {return centerImageX;}
						int getCenterImageY() {return centerImageY;}
						int getUpperLeftBufferX() {return upperLeftBufferX;}
						int getUpperLeftBufferY() {return upperLeftBufferY;}
				    }
				    
				    
				    
				 
				    /** The component inside the scroll pane. */
				    class DrawingPane extends JPanel {
				        protected void paintComponent(Graphics g) {
				            super.paintComponent(g);
				            
				            for(int i = 0; i < circuitElementImages.size(); ++i) {
				            	g.drawImage(circuitElementImages.get(i), imageInfo.get(i).getUpperLeftImageX(), imageInfo.get(i).getUpperLeftImageY(), null);
				            }
				           
				            	 
//				            Rectangle rect;
//				            for (int i = 0; i < circles.size(); i++) {
//				                rect = circles.elementAt(i);
//				                g.setColor(colors[(i % color_n)]);
//				                g.fillOval(rect.x, rect.y, rect.width, rect.height);
//				            }
				            
				            
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
				    	            elementImageTypes.add(img);
				    				break;
				    			case 1:
				    				
				    	            try {
				    	    			img = ImageIO.read(getClass().getResource("images/or_with_text.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    	            elementImageTypes.add(img);
				        			break;
				    			case 2:
				    				
				    	            try {
				    	    			img = ImageIO.read(getClass().getResource("images/not_with_text.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    	            elementImageTypes.add(img);
				        			break;
				    			case 3:
				    				
				    	            try {
				    	    			img = ImageIO.read(getClass().getResource("images/xor_with_text.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    	            elementImageTypes.add(img);
				        			break;
				    			case 4:
				    				try {
				    	    			img = ImageIO.read(getClass().getResource("images/nand_with_text.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    				elementImageTypes.add(img);
				        			break;
				    			case 5:
				    				try {
				    	    			img = ImageIO.read(getClass().getResource("images/nor_with_text.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    				elementImageTypes.add(img);
				        			break;
				    			case 6:
				    				try {
				    	    			img = ImageIO.read(getClass().getResource("images/xnor_with_text.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    				elementImageTypes.add(img);
				        			break;
				    			case 7:try {
					    			img = ImageIO.read(getClass().getResource("images/input.png"));
						    		} catch (IOException exp) {
						    			exp.printStackTrace();
						    		}
				    				elementImageTypes.add(img);
				        			break;
				    			case 8:
				    				try {
				    	    			img = ImageIO.read(getClass().getResource("images/output.png"));
				    	    		} catch (IOException exp) {
				    	    			exp.printStackTrace();
				    	    		}
				    				elementImageTypes.add(img);
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
//				            circles.removeAllElements();
//				            area.width=0;
//				            area.height=0;
				            changed = true;
				        } else {
				        	
				            //int x = e.getX() - W/2;
				            //int y = e.getY() - H/2;
				    
				      //no overlapping gates  	
				        	for (ImageCoordAndType temp : imageInfo) {
				        		int clicklowx = e.getX()-50;
				        		int clickhighx = e.getX()+50;
				        		int clicklowy = e.getY()-25;
				        		int clickhighy = e.getY()+25;
				        		int lowy = temp.getUpperLeftImageY();
				        		int lowx = temp.getUpperLeftImageX();
				        		int highy=lowy+50;
				        		int highx=lowx+100;
				        		if ((((clicklowx<=highx) && (clicklowx>=lowx)) && ((clicklowy<=highy) && (clicklowy>=lowy)))) {
				        			circuitElementButtonClicked = -1;
				        		}
				        		if ((((clicklowx<=highx) && (clicklowx>=lowx)) && ((clickhighy<=highy) && (clickhighy>=lowy)))) {
				        			circuitElementButtonClicked = -1;
				        		}
				        		if ((((clickhighx<=highx) && (clickhighx>=lowx)) && ((clicklowy<=highy) && (clicklowy>=lowy)))) {
				        			circuitElementButtonClicked = -1;
				        		}
				        		if ((((clickhighx<=highx) && (clickhighx>=lowx)) && ((clickhighy<=highy) && (clickhighy>=lowy)))) {
				        			circuitElementButtonClicked = -1;
				        		}
				        	}
				        	
				        	
				        	
				        	
				        	
				      //end no overlap  	
				            this.x = e.getX() - 50;
				            this.y = e.getY() - 25;
				            if (x < 0) x = 20;
				            if (y < 0) y = 10;
				            System.out.println(this.x);
				            System.out.println(this.y);
				            
				            if(circuitElementButtonClicked!=INVALID) {
					            circuitElementImages.add(elementImageTypes.get(circuitElementButtonClicked));
					            ImageCoordAndType imageCoordType = new ImageCoordAndType(circuitElementButtonClicked, x, y);
					            imageInfo.add(imageCoordType);
				            }
				            
//				            Rectangle rect = new Rectangle(x, y, W, H);
//				            circles.addElement(rect);
//				            drawingPane.scrollRectToVisible(rect);
				// 
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
		        newContentPane.setOpaque(true); //content panes must be opaque
		        frame.setContentPane(newContentPane);
		 
				    
				 
				    
				 
				   
				


				
				
				
				
				
				
				
				
				
				
				
// ScrollDemo2 from Oracle Tutorial Code end
//##############################################################################

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set launch window size
		Dimension d = new Dimension(1366,768);
		
		frame.setPreferredSize(d);
		
		
		// Adds JToolbar and buttons to JFrame
		
		JToolBar gates_and_io = new JToolBar("Gates & I/O");
		gates_and_io.setRollover(true);
		
		JButton button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/And_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/and_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = AND_BUTTON;
				System.out.println("I clicked the AND Button " + circuitElementButtonClicked);
			}
		});
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Or_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/or_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = OR_BUTTON;
				System.out.println("I clicked the OR Button " + circuitElementButtonClicked);
			}
		});
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Not_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/not_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = NOT_BUTTON;
				System.out.println("I clicked the NOT Button");
			}
		});
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Xor_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/xor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = XOR_BUTTON;
				System.out.println("I clicked the XOR Button");
			}
		});
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Nand_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/nand_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = NAND_BUTTON;
				System.out.println("I clicked the NAND Button");
			}
		});
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Nor_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/nor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = NOR_BUTTON;
				System.out.println("I clicked the NOR Button");
			}
		});
		gates_and_io.add(button);
		
		button = new JButton();
		button.setToolTipText("<html><img src=" + getClass().getResource("images/Xnor_Gate_Truth_Table.jpg") + "</html>");
		try {
			Image img = ImageIO.read(getClass().getResource("images/xnor_with_text.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = XNOR_BUTTON;
				System.out.println("I clicked the XNOR Button");
			}
		});
		gates_and_io.add(button);
		
		
		
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/input.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = INPUT_BUTTON;
				System.out.println("I clicked the INPUT Button");
			}
		});
		gates_and_io.add(button);
		
		
	
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("images/output.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				circuitElementButtonClicked = OUTPUT_BUTTON;
				System.out.println("I clicked the OUTPUT Button");
			}
		});
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
		
		// Add gridPane to JFrame -- gridPane holds the JLabel gridSpaceLabel
		//frame.getContentPane().add(gridPane, BorderLayout.CENTER);
		
		frame.getContentPane().setBackground(Color.GRAY);
		
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
				//abouttext.setPreferredSize(new Dimension(1300,768));
				abouttext.setVerticalAlignment(SwingConstants.NORTH);
				abouttext.setText("<html> <h3><font color = 'red'> Welcome to LogiCAD! </font></h3> <br>" +  
						"LogiCAD is a digital circuit Computer-Aided Design application. " + 
						"As you dive into our software you may notice several tools and menus at your disposal, so lets take a look at what we have to offer:<br>" +
						"<br>" + 
						"<br>" + 
						"Top Menu Bar:<br>" + 
						"<br>" + 
						"-File: Drop down menu of the following:<br>" + 
						"<br>" +								
						"&ensp;  &ensp;  Save:&ensp;  User is able to save project files.<br>" + 
						"&ensp;  &ensp;  Load:&ensp;  User is able to load previouse project files.<br>" + 
						"&ensp;  &ensp;  Import Module:&ensp;  User is able to save circuit designs as modules that can be loaded and used in future projects.<br>" + 
						"&ensp;  &ensp;  Export Module:&ensp;  A module is a completed circuit that is used after being saved as an element of another circuit.<br>" + 
						"&ensp;  &ensp;  Set Project Path:&ensp;  User is able to set the project path to be able to use the file explorer pannel.<br>" + 
						"<br>" + 
						"-Edit: Drop down menu of the following:<br>" + 
						"<br>" +
						"&ensp;  &ensp;  Options:&ensp;  User is able to set various preferences.<br>" + 
						"<br>" +
						"-View: Drop down menu of the following:<br>" + 
						"<br>" + 
						"&ensp;  &ensp;  Toggle Side-Bar:&ensp;  User is able to set whether they want to see the file explorer or not.<br>" + 
						"<br>" +
						"-Help: Drop down menu of the following:<br>" + 
						"<br>" + 
						"&ensp;  &ensp;  About:&ensp;  Information about the software and button features.<br>" + 
						"<br>" + 
						"<br>" + 
						"<br>" + 
						"Gate Menu Bar:<br>" + 
						"<br>" + 
						"-AND Gate Button:&ensp;  An AND gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-OR Gate Button:&ensp;  An OR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-NOT Gate Button:&ensp;  A NOT gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given one input and will provide an output.<br>" + 
						"\r\n" + 
						"-XOR Gate Button:&ensp;  An XOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-NAND Gate Button:&ensp;  A NAND gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-NOR Gate Button:&ensp;  A NOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-XNOR Gate Button:&ensp;  A XNOR gate will appear on the grid workspace for a user to be able to work with. This logic gate must be given two inputs and will provide an output.<br>" + 
						"\r\n" + 
						"-Input Gate Button:&ensp;  An Input wire will be presented to place on any logic gate or to attach to any other wires.<br>" + 
						"\r\n" + 
						"-Output Gate Button:&ensp;  An Output wire will be presented to place on any logic gate or to attach to any other wires.<br>" +
						"<br>" + 
						"-This gate menu bar is able to be moved around. By clicking the very left of the bar, a user can drag the menu bar all around and place it wherever one wishes.<br>" +
						" If a user decides to even take the menu bar outside the window they may do so. When they click the exit button the bar will go back to where it last was.<br>" +
						"<br>" + 
						"<br>" + 
						"<br>" +
						"Left Side Panel:" + 
						"<br>" + 
						"<br>" + 
						"-This space displays your current working directory after you specify the file path.<br>" + 
						"<br>" + 
						"<br>" + 
						"Grid Workspace:<br>" + 
						"<br>" + 
						"-This is the area a user will be given to work in.<br>" +
						"<br>" +
						"<br>" +
						"<br>" +
						"<br>" +
						"<br>" +
						"<br>" +
						"<br>" +
						"<br>" +
						"<br>" );
								
					
						
						JScrollPane aboutpane = new JScrollPane(abouttext);
						JPanel about = new JPanel();
						about.add(aboutpane);
						aboutpane.getViewport().add(abouttext);
						aboutpane.setPreferredSize(new Dimension(1300,768));
						JOptionPane.showMessageDialog(null,aboutpane, "About", 1, null);
						
						
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
