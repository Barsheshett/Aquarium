package q3;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class SaveObject extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_2;
	AquaPanel panel;
	private CareTaker CT;
	
	public SaveObject(AquaPanel panel, CareTaker care) {
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
            data[j][0] = i.getAnimalName();
            data[j][1] = panel.RetColName(i.getColor());
            data[j][2] = i.getSize();
            data[j][3] = i.horSpeed;
            data[j][4] = i.verSpeed;
            data[j][5] = i.getEatCount();
            data[j][6] = i.getId();
            eatCountSum = eatCountSum + i.getEatCount();
            j++;
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
        	data1[t][0] = ((Immobile)k).getName();
            data1[t][1] = ((Immobile)k).getSize();
            data1[t][2] = ((Immobile)k).getX();
            data1[t][3] = ((Immobile)k).getY();
            data1[t][4] = "Greean";
            data1[t][5] = ((Immobile)k).getID();
            t++;
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
        panel_1.setBounds(10, 474, 475, 48);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JButton btnNewButton = new JButton("Save");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Memento memento = null;
        		int Selected = Integer.parseInt(textField_2.getText());
        		
        		if(comboBox.getSelectedItem().equals("Swimmable")) {
        			
        			for(Swimmable swim: panel.Animals) {
        	           if(swim.getId() == Selected) {
        	        	   memento = new Memento(swim);
        	        	   CT.addSwimMemento(swim.getId(), memento);
        	       
        	           }
        	        }
        			
        		}else if(comboBox.getSelectedItem().equals("Immobile")) {
        			
        			for(SeaCreature plant: panel.Plants) {
        				if(((Immobile)plant).getID() == Selected) {
         	        	   memento = new Memento((Immobile)plant);
         	        	  CT.addPlanMemento(((Immobile)plant).getID(), memento);
         	        	   
        				}
        			}
        		}
        		dispose();
        		ImageIcon icon = new ImageIcon("src//q3//sea-urchin.png");
 			   	JOptionPane.showMessageDialog(null, "Save Memento succeed!","System Notice",JOptionPane.PLAIN_MESSAGE,icon);
        	}
        });
        btnNewButton.setBounds(318, 6, 85, 21);
        panel_1.add(btnNewButton);
        
        textField_2 = new JTextField();
        textField_2.setBounds(209, 7, 96, 19);
        panel_1.add(textField_2);
        textField_2.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Insert ID to save Memento:");
        lblNewLabel.setBounds(21, 10, 189, 13);
        panel_1.add(lblNewLabel);
        
        
	}
}
