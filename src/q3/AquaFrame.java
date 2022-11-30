package q3;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
/**
 * The main Frame of the program with main() function.
 * 
 * @authors Eden Barsheshet ID: 203531918 Toli Kot ID: 324413756
 * 
 * @see Fish, MultiColorFish, UnusualFish
 */

public class AquaFrame {

	public static void main(String[] args) {
		JFrame myframe = new JFrame("my Aquarium");
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AquaPanel mypanel = new AquaPanel(myframe);
		myframe.getContentPane().add(mypanel);
		myframe.setSize(1200, 700);
		CareTaker CT = new CareTaker(); // Save all memenetos
		
		JMenuBar menuBar = new JMenuBar();
		myframe.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Background");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("image");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mypanel.flagImage = true;
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Blue");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mypanel.flagImage = false;
				mypanel.setBackground(Color.blue);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("None");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mypanel.flagImage = false;
				Color clr = new Color(240,240,240);
				mypanel.setBackground(clr);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Help");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Help");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Home Work 3\n GUI @ Threads");

			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		myframe.setVisible(true);
		
		
		JMenu mnNewMenu_3 = new JMenu("Memento");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Save Object State");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveObject MementoSaveObject = new SaveObject(mypanel, CT);
				MementoSaveObject.setSize(520,580);
				MementoSaveObject.setLayout(null);
				MementoSaveObject.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Restore Object State");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RestorObject MementoRestorObject = new RestorObject(mypanel, CT);
				MementoRestorObject.setSize(520,580);
				MementoRestorObject.setLayout(null);
				MementoRestorObject.setVisible(true);

			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_5);
		mnNewMenu_3.add(mntmNewMenuItem_6);
		myframe.setVisible(true);
		
	}

}
