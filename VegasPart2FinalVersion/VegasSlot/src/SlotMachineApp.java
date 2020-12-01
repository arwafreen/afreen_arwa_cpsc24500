/**
 * Arwa Afreen
 * 11/04/2020
 * SlotMachineApp class created to application from its main function.
 */

/**
 * @author arwae
 *
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.*;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class TilePanel extends JPanel implements MouseListener {
	// implements MouseListener 
	
	private static ArrayList<Tile> tiles; // was changed to static
	private Random rnd;
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	// On user click change tile to random tile
	public void mouseClicked(MouseEvent e) {
		int whichTile = e.getX()/(this.getWidth()/4);
		Tile tile = tiles.get(whichTile);
		tile.setRandomly(rnd);
		repaint();
	}
	
	// Create random set of tiles to display on panel
	public TilePanel() {
		tiles = new ArrayList<Tile>();
		Tile tile;
		rnd = new Random();
		for (int i = 0; i < 4; i++) {
			tile = new Tile();
			tile.setRandomly(rnd);
			tiles.add(tile);
		}
		
		// Add mouse listener to check for user clicks
		addMouseListener(this);
	}
	
	// Get method for Arraylist tile object
	public static ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	// Set method for Arraylist tile object
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	
	// Set method to reset tile to random tile
	public static void setTilesRandom(ArrayList<Tile> tilesRandom) {
		tiles = tilesRandom;
	}
	
	// Paint method draws shape and fills color based on the Tile object information
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int cellWidth = this.getWidth() / 4;
		int tileSize = 4*cellWidth/5;
		int shape;
		Color color;
		Tile tile;
		
		// Get tile information to draw each tile in the ArrayList
		for (int i = 0; i < tiles.size(); i++) {
			tile = tiles.get(i);
			
			// Get shape
			shape = tile.getShape();
			
			// Get color name and set color
			color = tile.getActualColor();
			g.setColor(color);
			
			// Draw and color shape accordingly
			if (shape == 0) {
				g.fillOval(i*cellWidth + cellWidth/10, cellWidth/10, tileSize, tileSize); 
			} else if (shape == 1) {
				g.fillRect(i*cellWidth + cellWidth/10, cellWidth/10, tileSize, tileSize);
			} 
		}
	}
}


// Create JFrame class
class SlotMachineFrame extends JFrame {
	private TilePanel panCenter;
	
	// Text Field to display user balance
	private JTextField txtBalance = new JTextField(5);
	private double balance;
	
	// Panel max, mid, min buttons
	private JButton btnMax = new JButton("Max");
	private JButton btnMid = new JButton("Mid");
	private JButton btnMin = new JButton("Min");
	
	private double betAmount;
	private JLabel lblDollar = new JLabel("$");
	
	// Center frame method
	public void centerFrame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();  // width and height
		int screenWidth = (int)dim.getWidth();
		int screenHeight = (int)dim.getHeight();
		int frameWidth = 800;
		int frameHeight = 400;
		int left = (screenWidth-frameWidth)/2;
		int top = (screenHeight - frameHeight)/2;
		setBounds(left,top,frameWidth,frameHeight);
	}
	
	// Set look panel
	public void setupLook() {
		
		// Set up frame title
		setTitle("Vegas Baby Vegas Slot Machine");
		
		// Center frame using center frame method
		centerFrame();
		
		// Add buttons, label, text field and buttons
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		panCenter = new TilePanel();
		
		// Run TileRandomizer at the start of game
		ArrayList<Tile> randomStart = TileRandomizer.Randomizer();
		TilePanel.setTilesRandom(randomStart);
		
		c.add(panCenter,BorderLayout.CENTER);
		
		JPanel panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout());
		
		// Balance text field
		double num = 5.00;
		txtBalance.setText(String.format("%.2f", num));
		txtBalance.setEditable(false);
		
		// Convert Balance to double
		balance = Double.parseDouble(txtBalance.getText());
		
		// Max button action listener
		btnMax.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Set bet amount
				betAmount = balance;
				
				// Run TileRandomizer
				ArrayList<Tile> randomMax = TileRandomizer.Randomizer();
				TilePanel.setTilesRandom(randomMax);
				
				// Force whole window to repaint, display new set of tiles
				repaint();
				
				// Get current tiles
				ArrayList<Tile> tilesMax = new ArrayList<Tile>();
				tilesMax = TilePanel.getTiles();
				
				// Store winType return from Checker function in win variable
				int win = TileChecker.Checker(tilesMax);
				
				if (balance > 0.1) {
					// Based on win value change wager
					if (win == 1) {
					    // Color match, 25 times what you wagered
						  betAmount = betAmount * 25;
						  balance = balance + betAmount;
					}
					
					if (win == 2) {
						// Shape and Color match, 100 times what you wagered
						  betAmount = betAmount * 100;
						  balance = balance + betAmount;
					}
					if (win == 0) {
						// Update balance lost if not win reported
						  balance = balance - betAmount;
					}
				} else {
					balance = 0;
				}
				
				// Reset balance to current value
				txtBalance.setText(String.format("%.2f", balance));
				
				// Based on balance amount disable buttons
				if (balance == 0) {
					btnMin.setEnabled(false);
					btnMax.setEnabled(false);
					btnMid.setEnabled(false);
				}
				
			}
		}		
		);
		panSouth.add(btnMax);
		
		// Mid button action listener
		btnMid.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Set bet amount
				betAmount = (balance/2);
				
				// Run TileRandomizer
				ArrayList<Tile> randomMid = TileRandomizer.Randomizer();
				TilePanel.setTilesRandom(randomMid);
				
				// Force whole window to repaint, display new set of tiles
				repaint();
				
				// Get current tiles
				ArrayList<Tile> tilesMid = new ArrayList<Tile>();
				tilesMid = TilePanel.getTiles();
				
				// Store winType return from Checker function in win variable
				int win = TileChecker.Checker(tilesMid);
				
				if (balance > 0.1) {
					// Based on win value change wager
					if (win == 1) {
					    // Color match, 10 times what you wagered
						  betAmount = betAmount * 10;
						  balance = balance + betAmount;
					}
					
					if (win == 2) {
						// Shape and Color match, 50 times what you wagered
						  betAmount = betAmount * 50;
						  balance = balance + betAmount;
					}
					if (win == 0) {
						// Update balance lost if not win reported
						  balance = balance - betAmount;
					}
				} else {
					balance = 0;
				}
				
				// Reset balance to current value
				txtBalance.setText(String.format("%.2f", balance));
				
				// Based on balance amount disable buttons
				if (balance == 0) {
					btnMin.setEnabled(false);
					btnMax.setEnabled(false);
					btnMid.setEnabled(false);
				}
			}
		}		
		);
		panSouth.add(btnMid); 
		
		// Min button action listener
		btnMin.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Set bet amount
				betAmount = (balance/10);
				
				// Run TileRandomizer
				ArrayList<Tile> randomMin = TileRandomizer.Randomizer();
				TilePanel.setTilesRandom(randomMin);
				
				// Force whole window to repaint, display new set of tiles
				repaint();
				
				// Get current tiles
				ArrayList<Tile> tilesMin = new ArrayList<Tile>();
				tilesMin = TilePanel.getTiles();
				
				// Store winType return from Checker function in win variable
				int win = TileChecker.Checker(tilesMin);
				
				if (balance > 0.1) {
					// Based on win value change wager
					if (win == 1) {
					    // Color match, 5 times what you wagered
						  betAmount = betAmount * 5;
						  balance = balance + betAmount;
					}
					
					if (win == 2) {
						// Shape and Color match, 10 times what you wagered
						  betAmount = betAmount * 10;
						  balance = balance + betAmount;
					}
					if (win == 0) {
						// Update balance lost if not win reported
						  balance = balance - betAmount;
					}
				} else {
					balance = 0;
				}
				
				// Reset balance to current value
				txtBalance.setText(String.format("%.2f", balance));
				
				// Based on balance amount disable buttons
				if (balance == 0) {
					btnMin.setEnabled(false);
					btnMax.setEnabled(false);
					btnMid.setEnabled(false);
				}
			}
		}		
		);
		panSouth.add(btnMin);
		panSouth.add(lblDollar);
		panSouth.add(txtBalance);
		c.add(panSouth,BorderLayout.SOUTH);
		setupMenu();
	}
	
	// Create menu bar on top of window with file and help tabs and drop down menus
	public void setupMenu() {
		
		// Create menu bar
		JMenuBar mbar = new JMenuBar();
		
		// Create File drop down menu
		JMenu mnuFile = new JMenu("File");
	
		// Load menu item
		JMenuItem mLoad = new JMenuItem("Load Tiles");
		mLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TileReader dr = new TileReader();
				JFileChooser jfc = new JFileChooser();
				ArrayList<Tile> tilesRead;
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					tilesRead = dr.read(jfc.getSelectedFile());
					if (tilesRead == null) {
						JOptionPane.showMessageDialog(null,"Could not read tiles from file.");
					} else {
						panCenter.setTiles(tilesRead);
						repaint();
					}
				}
			}
		});
		
		// Save menu item
		JMenuItem mSave = new JMenuItem("Save Tiles");
		mSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser();
			TileWriter dw = new TileWriter();
			if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) { // the user wants to go ahead
				if (dw.write(jfc.getSelectedFile(), panCenter.getTiles())) {
					JOptionPane.showMessageDialog(null,"Wrote tiles to file.");
				} else {
					JOptionPane.showMessageDialog(null,"Could not write tiles to file.");
				}
			}
		}
		});
		
		// Print menu item prints the content of tile panel
		JMenuItem mPrint = new JMenuItem("Print");
		mPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Tile> tiles1 = new ArrayList<Tile>();
				
				// Use getTiles function to get tile information
				tiles1 = TilePanel.getTiles();
				
				// Print the shape and color of each tile
				for (Tile tile : tiles1) {	
					System.out.println(tile.toStringFancy());
				}
				
			}
		});
		
		// Restart menu item
		JMenuItem mRestart = new JMenuItem("Restart");
		mRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Reset balance to 5
				txtBalance.setText("5.00");
				txtBalance.setEditable(false);
				balance = 5;
				
				// Re-activate max, mid, min buttons
				btnMin.setEnabled(true);
				btnMax.setEnabled(true);
				btnMid.setEnabled(true);
				
			}
		});
		
		// Exit menu item
		JMenuItem mExit = new JMenuItem("Exit");
		mExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// Add items to file menu
		mnuFile.add(mLoad);
		mnuFile.add(mSave);
		mnuFile.add(mRestart);
		mnuFile.add(mPrint);
		mnuFile.add(mExit);
		mbar.add(mnuFile);
		
		// Create Help drop down menu
		JMenu mnuHelp = new JMenu("Help");
		JMenuItem miAbout = new JMenuItem("About");
		
		// About button display git hub url
		miAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Arwa Afreen https://github.com/arwafreen/afreen_arwa_cpsc24500/tree/master/VegasSlot");
			}
		});
		mnuHelp.add(miAbout);
		mbar.add(mnuHelp);
		setJMenuBar(mbar);
	}
	
	// SlotMachineFrame constructor
	public SlotMachineFrame() {
		setupLook();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}


// Tile class
class Tile implements Serializable{
	
	// Create colors array storing all 5 options for colors
	private static final Color[] colors = {Color.YELLOW, Color.GREEN, Color.ORANGE, Color.RED, Color.BLUE};
	
	// Create colorNames array storing String value of color names
	private static final String[] colorNames = {"yellow","green","orange","red","blue"};
	
	// Create shapes array to store String value of shape types
	private static final String[] shapes = {"circle","square"};
	
	// Declare color and shape variable to numerically represent color and shape of Tile
	private int color; //0 - yellow, 1 - green, 2 - orange, 3 - red, 4 - blue
	private int shape; // 0 - circle, 1 - square
	
	// Default tile constructor
	public Tile() {
		color = 3;
		shape = 0;
	}
	
	// Parameter constructor requiring integer value to pass and set color and shape of Tile
	public Tile(int color, int shape) {
		setColor(color);
		setShape(shape);
	}
	
	// Get method returns the integer representing the color of Tile Object
	public int getColor() {
		return color;
	}
	
	// Set method sets the color of the Tile object based on the integer parameter
	public void setColor(int color) {
		if (color < 0) {
			this.color = 0;
		} else if (color > 4) {
			this.color = 4;
		} else {
			this.color = color;
		}
	}
	
	// Method to randomly change the color and shape of the Tile Object using random generated number
	public void setRandomly(Random rnd) {
		color = rnd.nextInt(colors.length);
		shape = rnd.nextInt(shapes.length);
	}
	
	// Get method returns the actual color object of Tile
	public Color getActualColor() {
		return colors[color];
	}
	
	// Get method returns the String value of the Tile's color name
	public String getColorName() {
		return colorNames[color];
	}
	
	// Get method returns the integer representing the shape of Tile Object
	public int getShape() {
		return shape; 
	}
	
	// Set method sets the shape of the Tile object based on the integer parameter
	public void setShape(int shape) {
		if (shape < 0) {
			this.shape = 0;
		} else if (shape > 1) {
			this.shape = 1;
		} else {
			this.shape = shape;
		}
	}
	
	// Get method returns Shape name as String value
	public String getShapeAsString() {
		return shapes[shape];
	}
	
	// toString method prints the color and shape as String values
	public String toStringFancy() {
		return String.format("%s %s", getColorName(), getShapeAsString());
	}
	
	// toString method prints the color and shape as Integer values
	@Override
	public String toString() {
		return String.format("%d %d",color,shape);
	}
	
	// Get method returns color and shape integer code
	public int getCode() {
		return color*10+shape;
	}
}

//TileReader class reads the tiles from a file in text, binary, or XML format
class TileReader {
	
	// Read from a .TXT file
	public ArrayList<Tile> readFromText(String fname) {
		// Create file object
		File f = new File(fname);
		return readFromText(f);
	}
	public ArrayList<Tile> readFromText(File f) {
		try {
			
			// Initialize ArrayList to store the read Tile objects
			ArrayList<Tile> tilesRead = new ArrayList<Tile>();
			
			// Create Scanner object to read Tile objects from file
			Scanner fsc = new Scanner(f);
			String line;
			String[] parts;
			
			// Save all data in file as integers representing color and shape
			int color, shape;
			
			Tile tile;
			
			// Only continue to read while lines exist in file
			while (fsc.hasNextLine()) {
				line = fsc.nextLine();
				
				// Split at space to separate color and shape field
				parts = line.split(" ");
				color = Integer.parseInt(parts[0]);
				shape = Integer.parseInt(parts[1]);
				tile = new Tile(color,shape);
				tilesRead.add(tile);
			}
			
			// Close scanner
			fsc.close();
			
			// Return the tiles read from file
			return tilesRead;
			
		} catch (Exception ex) {
			return null;  
						  
		}
	}
	
	// Read from .BIN file
	public ArrayList<Tile> readFromBinary(String fname) {
		File f = new File(fname);
		return readFromBinary(f);
	}
	public ArrayList<Tile> readFromBinary(File f) {
		try {
			
			// Initialize ArrayList to store the read Tile objects
			ArrayList<Tile> tilesRead;
			
			// Create ObjectInputStream object to read Tile objects
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			tilesRead = (ArrayList<Tile>)ois.readObject();
			
			// Close ObjectInputStream
			ois.close();
			
			// Return the tiles read from file
			return tilesRead;
			
		} catch (Exception ex) {
			return null;
		}
	}
	
	// Read from .XML file
	public ArrayList<Tile> readFromXML(String fname) {
		File f = new File(fname);
		return readFromXML(f);
	}
	public ArrayList<Tile> readFromXML(File f) {
		try {
			
			// Initialize ArrayList to store the read Tile objects
			ArrayList<Tile> tilesRead;
			
			// Create XMLDecoder object to read Tile objects
			XMLDecoder dec = new XMLDecoder(
					new BufferedInputStream(new FileInputStream(f)));
			tilesRead = (ArrayList<Tile>)dec.readObject();
			
			// Close XMLDecoder
			dec.close();
			
			// Return the tiles read from file
			return tilesRead;
		} catch (Exception ex) {
			return null;
		}
	}
	public ArrayList<Tile> read(String fname) {
		File f = new File(fname);
		return read(f);
	}
	
	// Check file extension to choose which reading method
	public ArrayList<Tile> read(File f) {
		try {
			String fname = f.getName().toUpperCase();
			if (fname.endsWith(".TXT")) {
				return readFromText(f);
			} else if (fname.endsWith(".BIN")) {
				return readFromBinary(f);
			} else if (fname.endsWith(".XML")) {
				return readFromXML(f);
			} else {
				return null;  
			}
		} catch (Exception ex) {
			return null;
		}
	}
}


// TileWriter class which writes the tiles to a file in text, binary, or XML format, based on the file's extension
class TileWriter {
	
	// Write to .TXT file
	public boolean writeToText(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return writeToText(f,tiles);  // delegation - lean on another function to do your task
	}

	public boolean writeToText(File f, ArrayList<Tile> tiles) {
		try {
			
			// Create PrintWriter object to write Tile objects
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			for (Tile tile : tiles) {
				
				// Tile toString() will return a string
				pw.println(tile);
			}
			
			// Close PrintWriter object
			pw.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	// Write to .BIN file
	public boolean writeToBinary(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return writeToBinary(f,tiles);
	}
	
	public boolean writeToBinary(File f, ArrayList<Tile> tiles) {
		try {
			
			// Create ObjectOutputStream object to write objects
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			
			// Write Tile objects
			oos.writeObject(tiles);
			
			// Close ObjectOutputStream object
			oos.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public boolean write(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return write(f,tiles);
	}
	
	// Write to .XML file
	public boolean writeToXML(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return writeToXML(f,tiles);
	}
	public boolean writeToXML(File f, ArrayList<Tile> tiles) {
		try {
			
			// Create XMLEncoder object to write Tile objects to .XML file
			XMLEncoder enc = new XMLEncoder(new 
					BufferedOutputStream(new FileOutputStream(f)));
			
			// Write tiles using encoder
			enc.writeObject(tiles);
			
			// Close XMLEncoder object
			enc.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	// Check file extension to choose which writing method
	public boolean write(File f, ArrayList<Tile> tiles) {
		try {
			String fname = f.getName().toUpperCase();
			if (fname.endsWith(".TXT")) {
				return writeToText(f,tiles);
			} else if (fname.endsWith(".BIN")) {
				return writeToBinary(f,tiles);
			} else if (fname.endsWith(".XML")) {
				return writeToXML(f,tiles);
			} else {
				return false; 
			}
		} catch (Exception ex) {
			return false;
		}
	}

}

// TileRandomizer class randomizes slots when the user clicks on either the max, mid, or min button
class TileRandomizer {

	public static ArrayList<Tile> Randomizer() {
		
		// Create random array
		ArrayList tilesRandom = new ArrayList<Tile>();
		
		// Initialize tile object
		Tile tile;
		
		// Generate random number for setRandomly function parameter
		Random rnd = new Random();
		
		// Create 4 random tiles
		for (int i = 0; i < 4; i++) {
			tile = new Tile();
			tile.setRandomly(rnd);
			tilesRandom.add(tile);
		}
		
		return tilesRandom;
	}
}

// TileChecker class checks if the tile combination on display qualifies for any points
class TileChecker {
	
	public static int Checker(ArrayList<Tile> currentTiles) {
		
		// Default value false of all shapes colors and shapes being the same
		boolean allShapes = false;
		boolean allColors = false;
		
		// winType 0 = no win, 1 = colors match, 2 = colors and shape match
		int winType = 0;
		
		// Save current shape and colors into array lists
		ArrayList<String> cc = new ArrayList<String>();
		ArrayList<String> cs = new ArrayList<String>();
		for (Tile tile : currentTiles) {
			cs.add(tile.getShapeAsString());
			cc.add(tile.getColorName());
		}
		
		// Check if all shapes match
		if (
				(cs.get(0).equalsIgnoreCase(cs.get(1)) && 
						(cs.get(1).equalsIgnoreCase(cs.get(2)) && 
								(cs.get(2).equalsIgnoreCase(cs.get(3)))))){
							allShapes = true;
						}
		// Check if all colors match
		if (
				(cc.get(0).equalsIgnoreCase(cc.get(1)) && 
						(cc.get(1).equalsIgnoreCase(cc.get(2)) && 
								(cc.get(2).equalsIgnoreCase(cc.get(3)))))){
							allColors = true;
						}
		// if all colors match of the tiles set winType to 1
		if (allColors == true) {
			winType = 1;
		}
		
		// if all colors and shapes match of the tiles set winType to 2
		if ((allColors == true) & (allShapes == true)) {
			winType = 2;
		}
	
		return winType;
	}
}

// SlotMachineApp class creates and display SlotMachineFrame
public class SlotMachineApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Create frame using SlotMachineFrame
		SlotMachineFrame frm = new SlotMachineFrame();
		frm.setVisible(true);
		
	}

}
