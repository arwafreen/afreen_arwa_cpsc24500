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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

class TilePanel extends JPanel {
	
	Color shape1Color;
	Color shape2Color;
	Color shape3Color;
	Color shape4Color;
	
	String shape1Type;
	String shape2Type;
	String shape3Type;
	String shape4Type;
	
	ArrayList<Tile> tiles;
	
	
	// Create Array list of Tile Objects
	public TilePanel(){
		tiles = new ArrayList<Tile>();
		for (int i = 0; i < 4; i++) {
			// tiles.add(new Tile(someColor,someShape));
		    tiles.add(new Tile(Color.RED, "Square"));
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int shape1x = 25;
		int shape1y = 90;
		
		int shape2x = 215;
		int shape2y = 90;
		
		int shape3x = 405;
		int shape3y = 90;
		
		int shape4x = 595;
		int shape4y = 90;

		// Create circle
		g.setColor(Color.YELLOW);
		g.fillOval(shape1x, shape1y, 150, 150);
		
		// Create square
		g.setColor(Color.BLUE);
		g.fillRect(shape2x, shape2y, 150, 150);
		
		g.setColor(Color.RED);
		g.fillRect(shape3x, shape3y, 150, 150);
		
		g.setColor(Color.RED);
		g.fillRect(shape4x, shape4y, 150, 150);
		
	}
	
	public ArrayList getTiles(){
		// Get shape and color of current display
		repaint();
		return tiles;
	}
}
class SlotMachineFrame extends JFrame {
	private TilePanel panCenter;
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
	
	
	public void setupLook() {
		
		// Set up frame title
		setTitle("Vegas Baby Vegas Slot Machine");
		
		// Center frame using center frame method
		centerFrame();
		
		// Add buttons, label, text field and buttons
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		panCenter = new TilePanel();
		
		// panCenter.setText("Welcome to my program.");
		
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
				// DO STUFF
				repaint();
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
					JOptionPane.showMessageDialog(null,"Wrote dots to file.");
				} else {
					JOptionPane.showMessageDialog(null,"Could not write dots to file.");
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
				JOptionPane.showMessageDialog(null, "Arwa Afreen add URL of github project!");
			}
		});
		mnuHelp.add(miAbout);
		mbar.add(mnuHelp);
		setJMenuBar(mbar);
	}
	
	public SlotMachineFrame() {
		setupLook();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}


// Tile class
class Tile {
	Color shape1Color;
	Color shape2Color;
	Color shape3Color;
	Color shape4Color;
	
	String shape1Type;
	String shape2Type;
	String shape3Type;
	String shape4Type;
	
	// Constructor 1
	public Tile() {
		// DO STUFF
		
	}
	
	// Constructor 2
	public Tile(Color color, String shape) {
		// DO STUFF
		
	}
	
	// Function changes the color and shape randomly
	public void setRandomly () {
		
		//Creating Array for shapes
		String[] shapes ={"Square","Circle"};
		
		// Creating Array for colors
		Color[] colors = {Color.ORANGE, Color.RED, Color.YELLOW, Color.BLUE}; 
		
		
	}
	
	// toString function
	//@Override
	//public String toString() { 
	//	return String.format("%d %d %d %s",x,y,radius);
	//}
}

//TileReader class reads the tiles from a file in text, binary, or XML format 
class TileReader {
	// DO STUFF
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
				pw.println(tile);  // dot's toString() will return a string
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
