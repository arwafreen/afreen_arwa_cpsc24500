/** 
 * Arwa Afreen
 * 10/20/2020
 * CPSC 24500
 * PumpkinMaker assignment creates a custom pumpkin frame that user can define width, height, position, eye shape
 *  mouth shape, and nose shape. 
 */
package pumpkinMaker;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * @author arwae
 *
 */

class Pumpkin extends JPanel{
	//@Override
	
	// Default Pumpkin has circular eyes, a square nose, and an oval mouth
	String noseShape = "";
	String eyeShape = "";
	String mouthShape = "";
	int userLeft = 200;
	int userTop = 100;
	int userWidth = 150;
	int userHeight = 100;
	
	// Reset methods to change according to user input
	public void leftSet(int left) {
		userLeft = left;
	}
	
	public void topSet(int top) {
		userTop = top;
	}
	
	public void widthSet(int width) {
		userWidth = width;
	}
	
	public void heightSet(int height) {
		userHeight = height;
	}

	
	// Set noseShape to user input and reset shape type
	public void noseType(String type) {
		noseShape = type;
		if(noseShape.equalsIgnoreCase("C") == true ) {
			noseShape = "circleNose";
        }
		if(noseShape.equalsIgnoreCase("S") == true) {
			noseShape = "squareNose";
        }
		if(noseShape.equalsIgnoreCase("T") == true) {
			noseShape = "triangleNose";
        }
	}
	
	// Set eyeShape to user input and reset shape type
	public void eyeType(String type) {
		eyeShape = type;
		if(eyeShape.equalsIgnoreCase("C") == true) {
			eyeShape = "circleEye";
        }
		if(eyeShape.equalsIgnoreCase("S") == true) {
			eyeShape = "squareEye";
        }
		if(eyeShape.equalsIgnoreCase("T") == true) {
			eyeShape = "triangleEye";
        }
	}
	
	// Set mouthShape to user input and reset shape type
	public void mouthType(String type) {
		mouthShape = type;
		if(mouthShape.equalsIgnoreCase("O") == true) {
			mouthShape = "ovalMouth";
        }
		if(mouthShape.equalsIgnoreCase("R") == true) {
			mouthShape = "rectMouth";
        }
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Create orange pumpkin
		g.setColor(Color.ORANGE);
		g.fillOval(userLeft, userTop, userWidth, userHeight);
		
		// Create white rectangular stem
		g.setColor(Color.WHITE);
		g.fillRect(265, 70, 20, 30);
		
		// Draw Eyes
		if (eyeShape.equals("circleEye") == true || eyeShape.equals("") == true) {
			// Create white circle eyes
			g.setColor(Color.WHITE);
			g.fillOval(235, 115, 20, 12);
			g.fillOval(295, 115, 20, 12);
		}
		if (eyeShape.equals("squareEye") == true) {
			// Create white square eyes
			g.setColor(Color.WHITE);
			g.fillRect(235, 115, 15, 15);
			g.fillRect(295, 115, 15, 15);
		}
		if (eyeShape.equals("triangleEye") == true) {
			// Create white triangle eyes

			// Coordinates for triangle eye 1 (245,130),(235,115),(255,115)
			int x[]={245,235,255};
			int y[]={130,115,115};
			g.setColor(Color.WHITE);
			g.fillPolygon(x,y,3);
			
			// Coordinates for triangle eye 2 (295,115),(315,115),(305,130)
			int a[]={295,315,305};
			int b[]={115,115,130};
			g.setColor(Color.WHITE);
			g.fillPolygon(a,b,3);
		}
		
		// Draw Nose
		if (noseShape.equals("squareNose")  == true|| noseShape.equals("") == true) {
			// Create white square nose
			g.setColor(Color.WHITE);
			g.fillRect(265, 140, 20, 12);
		}
		if (noseShape.equals("circleNose") == true) {
			// Create white circle nose
			g.setColor(Color.WHITE);
			g.fillOval(265, 140, 20, 12);
		}
		if (noseShape.equals("triangleNose") == true) {
			// Create white triangle nose
			//g.fillRect(265, 140, 20, 12);
			
			// Coordinates (263,140) (277,140) (270,152)
			int j[]={263,277,270};
			int k[]={140,140,152};
			g.setColor(Color.WHITE);
			g.fillPolygon(j,k,3);
		}
		
		// Draw Mouth
		if (mouthShape.equals("ovalMouth") == true || mouthShape.equals("") == true) {
			// Create white oval mouth
			g.setColor(Color.WHITE);
			g.fillOval(245, 170, 60, 12);
		}
		if (mouthShape.equals("rectMouth") == true) {
			// Create white rectangle mouth
			g.setColor(Color.WHITE);
			g.fillRect(245, 170, 60, 12);
		}
	}
	
}
class ButtonHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null,"Incorrect input");
	}
}

class PumpkinFrame extends JFrame implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null,"The frame itself generated this.");
	}
    public void setLook(String title, int left, int top, int width, int height) {
        setTitle(title);
        setBounds(left, top, width, height);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        
        // Create pumpkin object for fields, labels and button
        Pumpkin panSouth = new Pumpkin();
        
        // Create labels
        JLabel lblLeft = new JLabel("Left: ");
        JLabel lblTop = new JLabel("Top: ");
        JLabel lblWidth = new JLabel("Width: ");
        JLabel lblHeight = new JLabel("Height: ");
        JLabel lblEye = new JLabel("Eye (C S T): ");
        JLabel lblNose = new JLabel("Nose (C S T): ");
        JLabel lblMouth = new JLabel("Mouth (O R): ");
        
        // Create text fields for users to enter values
        JTextField txtLeft = new JTextField(2);
        JTextField txtTop = new JTextField(2);
        JTextField txtWidth = new JTextField(2);
        JTextField txtHeight = new JTextField(2);
        JTextField txtEye = new JTextField(2);
        JTextField txtNose = new JTextField(2);
        JTextField txtMouth = new JTextField(2);
        
        // Create draw button
        JButton btnDraw = new JButton("Draw");
        
        // Add text fields and labels to panSouth
        panSouth.add(lblLeft);
        panSouth.add(txtLeft);
        panSouth.add(lblTop);
        panSouth.add(txtTop);
        panSouth.add(lblWidth);
        panSouth.add(txtWidth);
        panSouth.add(lblHeight);
        panSouth.add(txtHeight);
        panSouth.add(lblEye);
        panSouth.add(txtEye);
        panSouth.add(lblNose);
        panSouth.add(txtNose);
        panSouth.add(lblMouth);
        panSouth.add(txtMouth);
        panSouth.add(btnDraw);
        
        // Create pumpkin object for J Panel
        Pumpkin panCenter = new Pumpkin();
        c.add(panCenter,BorderLayout.CENTER);
        c.add(panSouth,BorderLayout.SOUTH);
        
        
        // Create Action Listener to complete user defined input changes onto Pumpkin panel object
        btnDraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	
            	// Eye           	
            	String eye = txtEye.getText();
            	if (eye.equalsIgnoreCase("C") == true || eye.equalsIgnoreCase("S") == true || eye.equalsIgnoreCase("T") == true || eye.equals("") == true) {
            		panCenter.eyeType(eye);
            		repaint();
            	} else {
            		JOptionPane.showMessageDialog(null,"Invalid Input!");
            	}
            	
            	// Nose           	
            	String nose = txtNose.getText();
            	if (nose.equalsIgnoreCase("C") == true || nose.equalsIgnoreCase("S") == true || nose.equalsIgnoreCase("T") == true || nose.equals("")== true ) {
            		panCenter.noseType(nose);
            		repaint();
            	} else {
            		JOptionPane.showMessageDialog(null,"Invalid Input!");
            	}
            	
            	// Mouth           	
            	String mouth = txtMouth.getText();
            	if (mouth.equalsIgnoreCase("O") == true || mouth.equalsIgnoreCase("R") == true || nose.equals("")== true ) {
            		panCenter.mouthType(mouth);
            		repaint();
            	} else {
            		JOptionPane.showMessageDialog(null,"Invalid Input!");
            	}
            	
            	// Width
            	String widthText = txtWidth.getText();
                for (int i = 0; i < widthText.length(); i++)
                    if (Character.isDigit(widthText.charAt(i)) == false) {
                    	JOptionPane.showMessageDialog(null,"Invalid Input!");
                    }  else {
	                	int width = Integer.parseInt(txtWidth.getText());
	                	panCenter.widthSet(width);
	                	repaint();
	                }
            	
            	// Height
                String heightText = txtHeight.getText();
                for (int i = 0; i < heightText.length(); i++)
                    if (Character.isDigit(heightText.charAt(i)) == false) {
                    	JOptionPane.showMessageDialog(null,"Invalid Input!");
                    }  else {
                    	int height = Integer.parseInt(txtHeight.getText());
		            	panCenter.heightSet(height);
		            	repaint();
                    }
            	
            	// Left 
                String leftText = txtHeight.getText();
                for (int i = 0; i < leftText.length(); i++)
                    if (Character.isDigit(leftText.charAt(i)) == false) {
                    	JOptionPane.showMessageDialog(null,"Invalid Input!");
                    }  else {
		            	int left = Integer.parseInt(txtLeft.getText());
		            	panCenter.leftSet(left);
		            	repaint();
                    }
            	
            	// Top
                String topText = txtTop.getText();
                for (int i = 0; i < topText.length(); i++)
                    if (Character.isDigit(topText.charAt(i)) == false) {
                    	JOptionPane.showMessageDialog(null,"Invalid Input!");
                    }  else {
		            	int top = Integer.parseInt(txtTop.getText());
		            	panCenter.topSet(top);
		            	repaint();
                    }
            	
                repaint();
                
            }
        });

    }   
    
    // Method to create frame
    public PumpkinFrame(String title, int left, int top, int width, int height, int closeOp) {
        setLook(title,left,top,width,height);
        setDefaultCloseOperation(closeOp);
    }
}

// Create frame using PumpkinFrame method
class CustomFrame {
    public static void main(String[] args) {
        PumpkinFrame frm = new PumpkinFrame("Pumpkin Maker", 100, 100, 800, 480, JFrame.HIDE_ON_CLOSE);
        frm.setVisible(true);
    }
}


