package q3;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPlanetDialog extends JDialog{
	private int Randx;
	private int Randy;
	private int max = 1200;
	private int min = 700;
	private int range = max - min + 1;
	AquaPanel p;
	
	public AddPlanetDialog(AquaPanel p) {
		this.p = p;
		setTitle("Add Planet Dialog");
		
		setSize(457, 350);
		
		
		
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("Select size:");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 188, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -181, SpringLayout.EAST, getContentPane());
		getContentPane().add(lblNewLabel);
		
		JSlider slider = new JSlider();
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -18, SpringLayout.NORTH, slider);
		springLayout.putConstraint(SpringLayout.NORTH, slider, 153, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, slider, -90, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, slider, 65, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, slider, 376, SpringLayout.WEST, getContentPane());
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(100);
		slider.setMajorTickSpacing(100);
		slider.setMinimum(20);
		slider.setMaximum(1100);
		getContentPane().add(slider);
		
		JLabel lblNewLabel_1 = new JLabel("Select kind of planet:");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 160, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -254, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -154, SpringLayout.EAST, getContentPane());
		getContentPane().add(lblNewLabel_1);
		
		String[] plant = { "Zostera", "Laminaria"};
		
		JComboBox<?> comboBox = new JComboBox<Object>(plant);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 6, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 170, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -29, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, -10, SpringLayout.EAST, lblNewLabel_1);
		getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Randx = (int) (Math.random() * range);
				Randy = (int) (Math.random() * range);
				
				if(comboBox.getSelectedIndex() == 0) {
					AbstractSeaFactory abstractseafactory =new PlantFactory(slider.getValue(),Randx, Randy);
					SeaCreature seacreature =abstractseafactory.produceSeaCreature("Zostera");
					p.AddPlanetToHashSet((Immobile)seacreature);
					dispose();
				}
				else if(comboBox.getSelectedIndex() == 1) {
					AbstractSeaFactory abstractseafactory =new PlantFactory(slider.getValue(),Randx, Randy);
					SeaCreature seacreature =abstractseafactory.produceSeaCreature("Laminaria");
					p.AddPlanetToHashSet((Immobile)seacreature);
					dispose();
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 26, SpringLayout.SOUTH, slider);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 188, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnNewButton);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
