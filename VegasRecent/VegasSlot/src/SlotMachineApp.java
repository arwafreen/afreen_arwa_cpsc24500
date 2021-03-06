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

class TilePanel extends JPanel implements MouseListener, MouseMotionListener {
	// implements MouseListener, MouseMotionListener 
	
	String shape1Color = "";
	String shape2Color = "";
	String shape3Color = "";
	String shape4Color = "";
	
	String shapeColor;
	
	String shape1Type = "";
	String shape2Type = "";
	String shape3Type = "";
	String shape4Type = "";
	
	String shapeType;
	
	ArrayList<Tile> tiles;
	private String [] shapesArr;
	private String [] colorsArr;
	
	
	// Create Array list of Tile Objects
	public TilePanel(){
		tiles = new ArrayList<Tile>();
		/*
		for (int i = 0; i < 4; i++) {
			 tiles.add(new Tile());
		}
		*/
		addMouseListener(this);
		addMouseMotionListener(this);
		tiles.add(new Tile(shape1Color, shape1Type));
		tiles.add(new Tile(shape2Color, shape2Type));
		tiles.add(new Tile(shape3Color, shape3Type));
		tiles.add(new Tile(shape4Color, shape4Type));	
	}
	
	public TilePanel(Tile tilesRandom) {
		tiles = new ArrayList<Tile>();
		
		tiles.add(tilesRandom);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Coordinates for each shape
		int shape1x = 25;
		int shape1y = 90;
		
		int shape2x = 215;
		int shape2y = 90;
		
		int shape3x = 405;
		int shape3y = 90;
		
		int shape4x = 595;
		int shape4y = 90;
		
		Color color1;
		Color color2;
		Color color3;
		Color color4;
		
		for (int i = 0; i < 4; i++) {
			for (Tile tile: tiles) {
				shapeColor = tile.getShapeColor();
				shapeType = tile.getShapeType();
				
					if (i == 0) {
						// Set Color Shape 1
						shape1Color = shapeColor;
						if (shape1Color == "") {
							color1 = Color.YELLOW;
							shape1Color = "YELLOW"; // set variable
						} else {
							color1 = setColors(shape1Color);
						}
						// Draw Shape 1
						shape1Type = shapeType;
						if (shape1Type == "Circle" || shape1Type == "") {
							// Draw circle
							g.setColor(color1);
							g.fillOval(shape1x, shape1y, 150, 150);
							shape1Type = "Circle"; // set variable
						} else {
							// Draw square
							g.setColor(color1);
							g.fillRect(shape1x, shape1y, 150, 150);
						}
					}
					
					if (i == 1) {
						// Set Color Shape 2
						shape2Color = shapeColor;
						if (shape2Color == "") {
							color2 = Color.BLUE;
							shape2Color = "BLUE"; // set variable
						} else {
							color2 = setColors(shape2Color);
						}
						// Draw Shape 2
						shape2Type = shapeType;
						if (shape2Type == "Square" || shape2Type == "") {
							// Draw square
							g.setColor(color2);
							g.fillRect(shape2x, shape2y, 150, 150);
							shape2Type = "Square"; // set variable
						} else {
							// Draw circle
							g.setColor(color2);
							g.fillOval(shape2x, shape2y, 150, 150);
						}
					}
					
					if (i == 2) {
						// Set Color Shape 3
						shape3Color = shapeColor;
						if (shape3Color == "") {
							color3 = Color.RED;
							shape3Color = "RED"; // set variable
						} else {
							color3 = setColors(shape3Color);
						}
						// Draw Shape 3
						shape3Type = shapeType;
						if (shape3Type == "Square" || shape3Type == "") {
							// Draw square
							g.setColor(color3);
							g.fillRect(shape3x, shape3y, 150, 150);
							shape3Type = "Square"; // set variable
						} else {
							// Draw circle
							g.setColor(color3);
							g.fillOval(shape3x, shape3y, 150, 150);
						}

					}
					
					if (i == 3) {
						// Set Color Shape 4
						shape4Color = shapeColor;
						if (shape4Color == "") {
							color4 = Color.RED;
							shape4Color = "RED"; // set variable
						} else {
							color4 = setColors(shape4Color);
						}
						// Draw Shape 4
						shape4Type = shapeType;
						if (shape4Type == "Square" || shape4Type == "") {
							g.setColor(color4);
							g.fillRect(shape4x, shape4y, 150, 150);
							shape4Type = "YELLOW"; // set variable
						} else {
							// Draw circle
							g.setColor(color4);
							g.fillOval(shape4x, shape4y, 150, 150);
						}
					}
			}
		}
	}
	
	// Method to set color based on String shapeColor
	public Color setColors(String colorName){
		Color c = Color.YELLOW;
		if (colorName.toUpperCase() == "YELLOW") {
			c = Color.YELLOW;
		}
		if (colorName.toUpperCase() == "RED") {
			c = Color.RED;
		}
		if (colorName.toUpperCase() == "BLUE") {
			c = Color.BLUE;
		}
		if (colorName.toUpperCase() == "GREEN") {
			c = Color.GREEN;
		}
		return c;
	}
	
	// Get Tile objects displayed on panel
	public ArrayList<Tile> getTiles(){
		// Get shape and color of current display
		/*
		Tile tile1 = new Tile(shape1Color, shape1Type);
		Tile tile2 = new Tile(shape2Color, shape2Type);
		Tile tile3 = new Tile(shape3Color, shape3Type);
		Tile tile4 = new Tile(shape4Color, shape4Type);
		ArrayList<Tile> tileArr = new ArrayList<Tile>();
		tileArr.add(tile1);
		tileArr.add(tile2);
		tileArr.add(tile3);
		tileArr.add(tile4);
		return tileArr;
		*/
		return tiles;
	}
	
	//public 
	
	// Set Tile objects based on file
	public void setTiles(ArrayList<Tile> tiles) { 
		this.tiles = tiles;
	}
	
	// DO I NEED THIS
	public void replaceTile(Tile t) {
		
	}
	
    public void mouseClicked(MouseEvent e) {
    	int xCoordinate = e.getX();
    	int yCoordinate = e.getY();
    	
    	if ((90 < yCoordinate) && (yCoordinate < 240)) {
		    	// If user clicked on first tile
		    	if ((25 < xCoordinate) && (xCoordinate < 175) ) {
		    		System.out.println("Tile1");
		    		Tile tileRandom1 = new Tile("BLUE", "Circle");
		    		//tileRandom1.setRandomly();
		    		tiles.remove(0);
		    		tiles.set(0, tileRandom1);
		    		repaint();
		    	}
		    	// If user clicked on second tile
		    	if ((215 < xCoordinate) && (xCoordinate < 365)) {
		    		System.out.println("Tile2");
		    		Tile tileRandom2 = new Tile("RED", "Circle");
		    		//tileRandom2.setRandomly();
		    		tiles.set(1, tileRandom2);
		    		repaint();
		    	}
		    	
		    	// If user clicked on third tile
		    	if ((405 < xCoordinate) && (xCoordinate < 555)) {
		    		System.out.println("Tile3");
		    		Tile tileRandom3 = new Tile("BLUE", "Square");
		    		//tileRandom3.setRandomly();
		    		tiles.set(2, tileRandom3);
		    		repaint();
		    	}
		    	
		    	// If user clicked on fourth tile
		    	if ((595 < xCoordinate) && (xCoordinate < 745)) {
		    		System.out.println("Tile4");
		    		Tile tileRandom4 = new Tile("GREEN", "Circle");
		    		//tileRandom4.setRandomly();
		    		tiles.set(3, tileRandom4);
		    		repaint();
		    	}
    	
    	}
	    	//if (xCoordinate == 0)
	    	/*
	    	 * Dot dotty = new Dot(e.getX(),e.getY(),dotSize); 
			   dots.add(dotty);
			   repaint();
	    	 */
	    }

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}

// Create JFrame class
class SlotMachineFrame extends JFrame {
	private TilePanel panCenter;
	
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
		
		c.add(panCenter,BorderLayout.CENTER);
		
		JPanel panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout());
		
		JButton btnMax = new JButton("Max");
		btnMax.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();  // forces the whole window to repaint.
			}
		}		
		);
		panSouth.add(btnMax);
		
		JButton btnMid = new JButton("Mid");
		btnMid.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();  // forces the whole window to repaint.
			}
		}		
		);
		panSouth.add(btnMid); 
		
		JButton btnMin = new JButton("Min");
		btnMin.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();  // forces the whole window to repaint.
			}
		}		
		);
		panSouth.add(btnMin);
		
		JLabel lbl1 = new JLabel("$");
		panSouth.add(lbl1);
		
		JTextField txt1 = new JTextField(5);
		panSouth.add(txt1);
		
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
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					ArrayList<Tile> tilesRead = dr.read(jfc.getSelectedFile());
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
		// Print menu item
		JMenuItem mPrint = new JMenuItem("Print");
		mPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// DO STUFF
				repaint();
			}
		});
		// Restart menu item
		JMenuItem mRestart = new JMenuItem("Restart");
		mRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// DO STUFF
				repaint();
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
	String shapeColor;
    String shapeType;
	
	// Constructor 1
	public Tile() {
		shapeColor = "YELLOW";
		//shapeType = "Circle";
		shapeType = "Square";
	}
	
	// Constructor 2
	public Tile(String color, String shape) {
		setColor(color);
		setShape(shape);	
	}
	
	// Set methods
	public void setShape(String s) {
		this.shapeType = s;
	}
	public void setColor(String c) {
		this.shapeColor = c;
	}
	
	// Get methods
	public String getShapeColor(){
		return shapeColor;
	}
	public String getShapeType(){
		return shapeType;
	}
	
	// Function changes the color and shape randomly
	public Tile setRandomly () {
		
		//Creating Array for shapes
		String[] shapes ={"Square","Circle"};
		int rndmShapeIndex = new Random().nextInt(shapes.length);
		String rndmShape = shapes[rndmShapeIndex];
		
		// Creating Array for colors
		Color[] colors = {Color.ORANGE, Color.RED, Color.YELLOW, Color.BLUE}; 
		int rndmColorIndex = new Random().nextInt(colors.length);
		String rndmColor = shapes[rndmColorIndex];
		
		// Create Tile object with random color and random shape
		Tile randomTile = new Tile (rndmColor, rndmShape);
		
		//return randomArr;
		return randomTile;
		
	}
	
	// toString function
	@Override
	public String toString() { 
		return String.format("%s %s", shapeColor, shapeType);
	}
}

//TileReader class reads the tiles from a file in text, binary, or XML format 
class TileReader {
	
	// Read from a .TXT file
	public ArrayList<Tile> readFromText(String fname) {
		File f = new File(fname);
		return readFromText(f);
	}	
	public ArrayList<Tile> readFromText(File f) {
		try {
			ArrayList<Tile> result = new ArrayList<Tile>();
			Scanner fsc = new Scanner(f);
			String line;
			String[] parts;
			
			// Save all data in file as String later convert color string to Color type
			String readColor;
			String readShape;
			
			Tile tiles;
			while (fsc.hasNextLine()) {
				line = fsc.nextLine().trim();  // get rid of whitespace at the end
				if (line.length() > 0) {  // prevent processing a blank line
					parts = line.split(" ");
					
					readColor = parts[0];
					readShape = parts[1];
							
					tiles = new Tile(readColor, readShape);

					result.add(tiles);
				}
			}
			fsc.close();
			return result; 
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;  // object equivalent to false 
		}
	}
	
	// Read from .BIN file
	public ArrayList<Tile> readFromBinary(String fname) {
		File f = new File(fname);
		return readFromBinary(f);
	}
	public ArrayList<Tile> readFromBinary(File f) {
		try {
			ArrayList<Tile> tilesRead;
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			tilesRead = (ArrayList<Tile>)ois.readObject();
			ois.close();
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
			ArrayList<Tile> tilesRead;
			XMLDecoder dec = new XMLDecoder(
					new BufferedInputStream(new FileInputStream(f)));
			tilesRead = (ArrayList<Tile>)dec.readObject();
			dec.close();
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
		String fname = f.getName().toUpperCase();
		if (fname.endsWith(".TXT")) {
			return readFromText(f);
		}
		if (fname.endsWith(".BIN")) {
			return readFromBinary(f);
		}
		if (fname.endsWith(".XML")) {
			return readFromXML(f);
		}
		return null;  // unrecognized file type.
	}
}

// TileWriter class which writes the tiles to a file in text, binary, or XML format, based on the file's extension
class TileWriter {
	
	// Text write
	public boolean writeToText(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return writeToText(f,tiles);   // delegation - I am passing the responsibility
			// to the other writeToText function that takes in a File and an ArrayList
			// rather than a String and an array list.
	}
	public boolean writeToText(File f, ArrayList<Tile> tiles) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			for (Tile tile : tiles) {
				pw.println(tile);  // Tile toString() will return a string
			}
			pw.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// Binary write
	public boolean writeToBinary(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return writeToBinary(f,tiles);
	}
	public boolean writeToBinary(File f, ArrayList<Tile> tiles) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(tiles);
			oos.close();
			return true; // success
		} catch (Exception ex) {
			return false;
		}
	}
	
	// XML write
	public boolean writeToXML(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return writeToXML(f,tiles);
	}
	public boolean writeToXML(File f, ArrayList<Tile> tiles) {
		try {
			XMLEncoder enc = new XMLEncoder(new 
					BufferedOutputStream(new FileOutputStream(f)));
			enc.writeObject(tiles);
			enc.close();			
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	// Choose method to write based on file name extension
	public boolean write(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return write(f,tiles);
	}
	public boolean write(File f, ArrayList<Tile> tiles) {
		String fname = f.getName().toUpperCase();
		if (fname.endsWith(".TXT")) {
			return writeToText(f,tiles);
		}
		if (fname.endsWith(".BIN")) {
			return writeToBinary(f,tiles);
		}
		if (fname.endsWith(".XML")) {
			return writeToXML(f,tiles);
		}
		return false;  // invalid or unrecognized file type
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
