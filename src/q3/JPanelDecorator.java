package q3;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class JPanelDecorator extends JPanel{
	AquaPanel panel;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
	public JPanelDecorator(AquaPanel panel) {
		this.panel = panel;
		
		Object[][] data = new Object[panel.Animals.size() + 1][7];
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
        this.setSize(518,530);
        setLayout(null);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(25, 5, 452, 426);
        table.setFillsViewportHeight(true);
        this.add(scroll);
        
        textField = new JTextField();
        textField.setBounds(194, 451, 96, 19);
        add(textField);
        textField.setColumns(10);
     
        JButton btnNewButton = new JButton("Change Color");
        btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
        		if(textField.getText().equals("")) {
        			ImageIcon icon = new ImageIcon("src//q3//octopus.png");
        			JOptionPane.showMessageDialog(null, "Must choose ID","System Notice",JOptionPane.PLAIN_MESSAGE,icon);
        		}
        		else {
	        		try{
		        		JColorChooser colorChooser = new JColorChooser();
		        		Color color = JColorChooser.showDialog(null, "Select a color", Color.black);
		        		
		        		int id = Integer.parseInt(textField.getText());
		        		
		        		for (Swimmable swim : panel.Animals) {
							if(swim.getId() == id) {
								
								if(swim instanceof Fish) {
									((Fish)swim).PaintFish(color);
								}
								else if(swim instanceof Jellyfish) {
									((Jellyfish)swim).PaintFish(color);
								}
							}
						}
		        		
	        		}catch (NumberFormatException e1) {
	        			ImageIcon icon = new ImageIcon("src//q3//octopus.png");
	        			JOptionPane.showMessageDialog(null, "non Color choosed","System Notice",JOptionPane.PLAIN_MESSAGE,icon);
					}
	        			
	        	}
        	}
			
        });
        btnNewButton.setBounds(177, 483, 123, 21);
        add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("Enter id to change Color:");
        lblNewLabel.setBounds(25, 454, 159, 13);
        add(lblNewLabel);
        
	}

}
