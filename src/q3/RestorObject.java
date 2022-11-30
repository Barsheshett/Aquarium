package q3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class RestorObject extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AquaPanel panel;
	private CareTaker CT;
	private JTextField textField;
	private int idIndex;
	
	public RestorObject(AquaPanel panel, CareTaker care) {
		this.panel = panel;
		CT = care;
		getContentPane().setLayout(null);
		
		JComboBox <String>comboBox = new JComboBox<String>();
		comboBox.setBounds(189, 10, 115, 21);
        getContentPane().add(comboBox);
        comboBox.addItem("Swimmable");
        comboBox.addItem("Immobile");
        
		JPanel Tpanel1 = new JPanel();
		Tpanel1.setBounds(0, 30, 518, 445);
		JPanel TPanel=new JPanel();
		TPanel.setBounds(0, 30, 518, 445);
		
		
		comboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(comboBox.getSelectedItem().equals("Swimmable")) {
        			TPanel.setVisible(true);
        			Tpanel1.setVisible(false);
        		}else if(comboBox.getSelectedItem().equals("Immobile")) {
        			TPanel.setVisible(false);
        			Tpanel1.setVisible(true);
        		}
        	}
        });
		
		/**
		 * Swimmable Table
		 */
        Object[][] data = new Object[panel.Animals.size()][7];
        String[] components = {"Animal","Color","Size","Hor.speed","Ver.speed","Eat couner","ID"};
        int j = 0;
        int eatCountSum = 0;
        for(Swimmable i: panel.Animals) {
        	if(CT.getSwimMemento(i.getId()) != null) {
	            data[j][0] = i.getAnimalName();
	            data[j][1] = panel.RetColName(i.getColor());
	            data[j][2] = CT.getSwimMemento(i.getId()).getSize();
	            data[j][3] = CT.getSwimMemento(i.getId()).getHorSpeed();
	            data[j][4] = CT.getSwimMemento(i.getId()).getVerSpeed();
	            data[j][5] = i.getEatCount();
	            data[j][6] = CT.getSwimMemento(i.getId()).getID();
	            eatCountSum = eatCountSum + i.getEatCount();
	            j++;
        	}
        }

        JTable table = new JTable(data,components);
        table.setSize(300,150);
        TPanel.setLayout(null);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(25, 5, 452, 426);
        table.setFillsViewportHeight(true);
        TPanel.add(scroll);
        getContentPane().add(TPanel);
        
        
        /**
         * andSwimmable Table
         */
	
		
        /**
		 * Plant Table
		 */
        Object[][] data1= new Object[panel.Plants.size()][6];
        String[] components1 = {"Name","Size","X","Y","Color","ID"};
        int t = 0;
        for(SeaCreature k: panel.Plants) {
        	if(CT.getPlanMemento(((Immobile)k).getID()) != null) {
	        	data1[t][0] = ((Immobile)k).getName();
	            data1[t][1] = ((Immobile)k).getSize();
	            data1[t][2] = ((Immobile)k).getX();
	            data1[t][3] = ((Immobile)k).getY();
	            data1[t][4] = "Greean";
	            data1[t][5] = ((Immobile)k).getID();
	            t++;
        	}
        }
        getContentPane().setLayout(null);

        JTable table2 = new JTable(data1,components1);
        table2.setSize(300,150);
        Tpanel1.setLayout(null);
        JScrollPane scroll2 = new JScrollPane(table2);
        scroll2.setBounds(25, 5, 452, 426);
        table2.setFillsViewportHeight(true);
        Tpanel1.add(scroll2);
        getContentPane().add(Tpanel1);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 471, 468, 60);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JButton btnNewButton = new JButton("Restore");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(comboBox.getSelectedItem().equals("Swimmable")) {
        			idIndex = Integer.parseInt(textField.getText());
        			
        	        for(Swimmable i: panel.Animals) {
        	        	if(i.getId() == idIndex) {
        		            i.setState(CT.getSwimMemento(i.getId()).getSize() , 
        		            		CT.getSwimMemento(i.getId()).getX_front(),
        		            		CT.getSwimMemento(i.getId()).getY_front(),
        		            		CT.getSwimMemento(i.getId()).getHorSpeed(),
        		            		CT.getSwimMemento(i.getId()).getVerSpeed(),
        		            		CT.getSwimMemento(i.getId()).getCol(),
        		            		CT.getSwimMemento(i.getId()).getFoodFrequency());
        		            panel.repaint();
        	        	}
        	        }
        			
        		}else if(comboBox.getSelectedItem().equals("Immobile")) {
        			for(SeaCreature i: panel.Plants) {
        	        	if(((Immobile)i).getID() == idIndex) {
        	        		((Immobile)i).setState( CT.getPlanMemento(((Immobile)i).getID()).getSize(),
        	        				CT.getPlanMemento(((Immobile)i).getID()).getX_front(),
        	        				CT.getPlanMemento(((Immobile)i).getID()).getY_front(),
        	        				Color.green);
        		            panel.repaint();
        	        	}
        	        }
        		}
        		dispose();
        		ImageIcon icon = new ImageIcon("src//q3//sea-urchin.png");
 			   	JOptionPane.showMessageDialog(null, "Restore Memento succeed!","System Notice",JOptionPane.PLAIN_MESSAGE,icon);
        	}
        });
        btnNewButton.setBounds(321, 10, 85, 21);
        panel_1.add(btnNewButton);
        
        textField = new JTextField();
        textField.setBounds(215, 11, 96, 19);
        panel_1.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Insert ID to Restore Memento State:");
        lblNewLabel.setBounds(10, 14, 195, 13);
        panel_1.add(lblNewLabel);
		
	}

}
