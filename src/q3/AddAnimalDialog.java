package q3;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;



public class AddAnimalDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private AquaPanel panel;
	private JTextField txtHh;
	private JTextField txtBetweenTo;
	private JTextField txtBetweenTo_1; 
	private Color clr;
	//private JLabel lbl;
	private String[] names = {"1","2","3","4", 
			"5","6","7","8", "9"};
	private JTextField textField;
	

	
	

	public AddAnimalDialog(AquaPanel p) {
		setTitle("Animal Dialog Panel");
		panel = p;
		getContentPane().setLayout(null);
		
		setSize(457, 350);
		
		JLabel lblNewLabel = new JLabel("Select an animal:");
		lblNewLabel.setBounds(10, 10, 124, 13);
		getContentPane().add(lblNewLabel);
		
		
		JRadioButton[] radioButtons = new JRadioButton[2];
		final ButtonGroup group = new ButtonGroup();
		
		radioButtons[0]  = new JRadioButton("Fish");
		radioButtons[0].setBounds(10, 29, 66, 21);
		getContentPane().add(radioButtons[0]);
		
		radioButtons[1] = new JRadioButton("Jellyfish");
		radioButtons[1].setBounds(90, 29, 186, 21);
		getContentPane().add(radioButtons[1]);
		
		group.add(radioButtons[0]);
		group.add(radioButtons[1]);
		
		JLabel lblNewLabel_1 = new JLabel("Select size:");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setBounds(10, 77, 81, 13);
		getContentPane().add(lblNewLabel_1);
		
		txtHh = new JTextField();
		txtHh.setText("Between 20 to 320");
		txtHh.setText("80");
		txtHh.setToolTipText("");
		txtHh.setBounds(10, 100, 96, 19);
		getContentPane().add(txtHh);
		txtHh.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Horizontal speed:");
		lblNewLabel_2.setBounds(10, 129, 124, 13);
		getContentPane().add(lblNewLabel_2);
		
		txtBetweenTo = new JTextField();
		//txtBetweenTo.setText("Between 1 to 10");
		txtBetweenTo.setText("8");
		txtBetweenTo.setBounds(10, 152, 96, 19);
		getContentPane().add(txtBetweenTo);
		txtBetweenTo.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Vertical speed:");
		lblNewLabel_3.setBounds(10, 181, 106, 13);
		getContentPane().add(lblNewLabel_3);
		
		txtBetweenTo_1 = new JTextField();
		//txtBetweenTo_1.setText("Between 1 to 10");
		txtBetweenTo_1.setText("7");
		txtBetweenTo_1.setBounds(10, 204, 96, 19);
		getContentPane().add(txtBetweenTo_1);
		txtBetweenTo_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Select Color:");
		lblNewLabel_4.setBounds(180, 77, 82, 13);
		getContentPane().add(lblNewLabel_4);
		
		clr = new Color(240,240,240);
	    setBackground(clr);
		JComboBox<String> cmb = new JComboBox<String>();
	    cmb.setBounds(176, 99, 86, 20);
	    for(int i=0;i<names.length;i++)
			cmb.addItem(names[i]);
	    
	    getContentPane().add(cmb);
	    
	    JButton btnNewButton = new JButton("Add");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
		    		int i = Integer.parseInt(txtHh.getText()); //get the size from textField
		    		int j = Integer.parseInt(txtBetweenTo.getText());//get the horSpeed from textField
		    		int k = Integer.parseInt(txtBetweenTo_1.getText());// get the verSpeed from textField
		    		int f = Integer.parseInt(textField.getText());//get the Food Frequency from textField
		    		String clr_name = (String) cmb.getItemAt(cmb.getSelectedIndex());
		    		int c = Integer.parseInt(clr_name);// get the Color
		    		
		    		if(i<20 || i>320)
						throw new Exception("size have to be between 20 to 320");
		    		if(j<1 || j>10)
						throw new Exception("horizontal speed have to be between 1 to 10");
					if(k<1 || k>10)
						throw new Exception("vertical speed have to be between 1 to 10");
					if(f<100 || f>200)
						throw new Exception("Food frequency have to be between 100 to 200");
		    		
		    		if(radioButtons[0].isSelected()) {
		    			AbstractSeaFactory abstractseafactory =new AnimalFactory(i,j, k, c,f);
		    			SeaCreature seacreature =abstractseafactory.produceSeaCreature("Fish");
		    			panel.AddAnimelToHashSet((Swimmable)seacreature);
		    			dispose();
		    		}
		    		else if(radioButtons[1].isSelected()) {
		    			AbstractSeaFactory abstractseafactory =new AnimalFactory(i,j, k, c,f);
		    			SeaCreature seacreature =abstractseafactory.produceSeaCreature("Jellyfish");
		    			panel.AddAnimelToHashSet((Swimmable)seacreature);
		    			dispose();
		    		}
		    		else
		    			throw new Exception("Must select fish or jellyfish before adding");
		    		
	    		}catch(Exception ex) {
	    			ImageIcon icon = new ImageIcon("src//q3//octopus.png");
	    			JOptionPane.showMessageDialog(null, ex.getMessage(),"System Notice",JOptionPane.PLAIN_MESSAGE,icon);
	    			}
	
	    	}
	    });
	    btnNewButton.setBounds(177, 266, 85, 21);
	    getContentPane().add(btnNewButton);
	    
	    JPanel panel = new JPanel();
	    panel.setBounds(301, 77, 106, 184);
	    getContentPane().add(panel);
	    GridBagLayout gbl_panel = new GridBagLayout();
	    gbl_panel.columnWidths = new int[]{0, 0};
	    gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	    gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
	    gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    panel.setLayout(gbl_panel);
	    
	    JLabel lblNewLabel_5 = new JLabel("1 to Black");
	    GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
	    gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
	    gbc_lblNewLabel_5.gridx = 0;
	    gbc_lblNewLabel_5.gridy = 0;
	    panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
	    
	    JLabel lblNewLabel_6 = new JLabel("2 to Red");
	    lblNewLabel_6.setForeground(Color.RED);
	    GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
	    gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 0);
	    gbc_lblNewLabel_6.gridx = 0;
	    gbc_lblNewLabel_6.gridy = 1;
	    panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
	    
	    JLabel lblNewLabel_7 = new JLabel("3 to Blue");
	    lblNewLabel_7.setForeground(Color.BLUE);
	    GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
	    gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 0);
	    gbc_lblNewLabel_7.gridx = 0;
	    gbc_lblNewLabel_7.gridy = 2;
	    panel.add(lblNewLabel_7, gbc_lblNewLabel_7);
	    
	    JLabel lblNewLabel_8 = new JLabel("4 for Green");
	    lblNewLabel_8.setForeground(Color.GREEN);
	    GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
	    gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 0);
	    gbc_lblNewLabel_8.gridx = 0;
	    gbc_lblNewLabel_8.gridy = 3;
	    panel.add(lblNewLabel_8, gbc_lblNewLabel_8);
	    
	    JLabel lblNewLabel_9 = new JLabel("5 to Cyan");
	    lblNewLabel_9.setForeground(Color.CYAN);
	    GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
	    gbc_lblNewLabel_9.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 0);
	    gbc_lblNewLabel_9.gridx = 0;
	    gbc_lblNewLabel_9.gridy = 4;
	    panel.add(lblNewLabel_9, gbc_lblNewLabel_9);
	    
	    JLabel lblNewLabel_10 = new JLabel("6 for Orange");
	    lblNewLabel_10.setForeground(Color.ORANGE);
	    GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
	    gbc_lblNewLabel_10.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 0);
	    gbc_lblNewLabel_10.gridx = 0;
	    gbc_lblNewLabel_10.gridy = 5;
	    panel.add(lblNewLabel_10, gbc_lblNewLabel_10);
	    
	    JLabel lblNewLabel_13 = new JLabel("7 to Yellow");
	    lblNewLabel_13.setForeground(Color.YELLOW);
	    GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
	    gbc_lblNewLabel_13.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 0);
	    gbc_lblNewLabel_13.gridx = 0;
	    gbc_lblNewLabel_13.gridy = 6;
	    panel.add(lblNewLabel_13, gbc_lblNewLabel_13);
	    
	    JLabel lblNewLabel_11 = new JLabel("8 to Mangeta");
	    lblNewLabel_11.setForeground(Color.MAGENTA);
	    GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
	    gbc_lblNewLabel_11.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 0);
	    gbc_lblNewLabel_11.gridx = 0;
	    gbc_lblNewLabel_11.gridy = 7;
	    panel.add(lblNewLabel_11, gbc_lblNewLabel_11);
	    
	    JLabel lblNewLabel_12 = new JLabel("9 to Pink");
	    lblNewLabel_12.setForeground(Color.PINK);
	    GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
	    gbc_lblNewLabel_12.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_12.gridx = 0;
	    gbc_lblNewLabel_12.gridy = 8;
	    panel.add(lblNewLabel_12, gbc_lblNewLabel_12);
	    
	    JLabel lblNewLabel_3_1 = new JLabel("Food frequency:");
	    lblNewLabel_3_1.setBounds(10, 234, 124, 13);
	    getContentPane().add(lblNewLabel_3_1);
	    
	    textField = new JTextField();
	    textField.setColumns(10);
	    textField.setBounds(10, 257, 96, 19);
	    getContentPane().add(textField);
   
	}
}
