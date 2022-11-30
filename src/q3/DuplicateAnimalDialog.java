package q3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class DuplicateAnimalDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AquaPanel panel;
	private JTextField textField;
	public Dimension dm;
	public static Swimmable dup;
	private String[] names = {"Black","Red","Blue","Green", 
			"Cyen","Orange","Yellow","Mangeta", "Pink"};
	
	public DuplicateAnimalDialog(AquaPanel p) {
		setTitle("Duplicate Animal Dialog Panel");
		panel = p;
		JTableCreat();
		
		
       
	}
	
	public void JTableCreat(){ 
		JPanel TPanel=new JPanel();
		TPanel.setLocation(0, 0);
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
        getContentPane().setLayout(null);

        JTable table = new JTable(data,components);
        table.setSize(300,150);
        TPanel.setSize(518,530);
        TPanel.setLayout(null);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(25, 5, 452, 426);
        table.setFillsViewportHeight(true);
        TPanel.add(scroll);
        getContentPane().add(TPanel); 
        
        JButton btnNewButton = new JButton("Duplicate");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        		int id = Integer.parseInt(textField.getText());
        		for (Swimmable swim : panel.Animals) {
					if(swim.getId() == id) {
						panel.AddAnimelToHashSet((Swimmable)swim.clone());
						dup = (Swimmable)swim.clone();
					}
				}
        		dispose();
        		}catch (NumberFormatException e2) {
        			setVisible(false);
    			}
        	}
        });
        btnNewButton.setBounds(140, 489, 98, 21);
        TPanel.add(btnNewButton);
        
        textField = new JTextField();
        textField.setBounds(266, 457, 96, 19);
        TPanel.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Insert animal ID for duplicate:");
        lblNewLabel.setToolTipText("");
        lblNewLabel.setBounds(95, 461, 194, 13);
        TPanel.add(lblNewLabel);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		JDialog dialog = new JDialog();
        		JPanel Pandialog = new JPanel();
        		dialog.setTitle("Prototype design Pattern window ");
        		try {
        		dialog.setSize(350,350);
        		dialog.setVisible(true);
        		dispose();
        		dialog.setLayout(null);
        		Pandialog.setSize(350,350);
        		Pandialog.setLayout(null);
        		dialog.add(Pandialog);
        		int id = Integer.parseInt(textField.getText());
        		for (Swimmable swim : panel.Animals) {
					if(swim.getId() == id) {
						dup = (Swimmable)swim.clone();
					}
				}
        		
        		dm = dialog.getSize();
        		JLabel lblNewLabel_1 = new JLabel("Update size:");
        		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
        		lblNewLabel_1.setBounds(10, 77, 100, 13);
        		Pandialog.add(lblNewLabel_1);
        		
        		JTextField sizetxt = new JTextField();
        		sizetxt.setText(String.valueOf(dup.getSize()));
        		sizetxt.setToolTipText("");
        		sizetxt.setBounds(10, 100, 96, 19);
        		Pandialog.add(sizetxt);
        		sizetxt.setColumns(10);
        		
        		JLabel lblNewLabel_2 = new JLabel("Update Horizontal speed:");
        		lblNewLabel_2.setBounds(10, 129, 150, 13);
        		Pandialog.add(lblNewLabel_2);
        		
        		JTextField horspeedtxt = new JTextField();
        		horspeedtxt.setText(String.valueOf(dup.getHorSpeed()));
        		horspeedtxt.setBounds(10, 152, 96, 19);
        		Pandialog.add(horspeedtxt);
        		horspeedtxt.setColumns(10);
        		
        		JLabel lblNewLabel_3 = new JLabel("Update Vertical speed:");
        		lblNewLabel_3.setBounds(10, 181, 150, 13);
        		Pandialog.add(lblNewLabel_3);
        		
        		JTextField verspeedtxt = new JTextField();
        		verspeedtxt.setText(String.valueOf(dup.getVerSpeed()));
        		verspeedtxt.setBounds(10, 204, 96, 19);
        		Pandialog.add(verspeedtxt);
        		verspeedtxt.setColumns(10);
        		
        		JLabel lblNewLabel_4 = new JLabel("Update Color:");
        		lblNewLabel_4.setBounds(180, 77, 106, 13);
        		Pandialog.add(lblNewLabel_4);
        		
        		
        		Color clr = new Color(240,240,240);
        	    setBackground(clr);
        		JComboBox<String> cmb = new JComboBox<String>();
        	    cmb.setBounds(180, 77+29, 86, 20);
        	    for(int i=0;i<names.length;i++)
        			cmb.addItem(names[i]);
        	    cmb.setSelectedItem(dup.getColor().toString());
        	    Pandialog.add(cmb);
        	   
        	    
        	    
        	    JLabel title = new JLabel("Update Window: ");
        	    title.setBounds(10, 20, 300, 50);
        	    title.setFont(title.getFont().deriveFont(30f));
        	    Pandialog.add(title);
        	    
        	    JButton btnNewButton = new JButton("Add");
                btnNewButton.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		try {
                		int size = Integer.parseInt(sizetxt.getText());
                	    int horspeed = Integer.parseInt(horspeedtxt.getText());
                	    int verspeed = Integer.parseInt(verspeedtxt.getText());
                	    int color = getColor(cmb.getSelectedItem().toString());
                	
                		if(size<20 || size>320)
    						throw new Exception("size have to be between 20 to 320");
    		    		if(horspeed<1 || horspeed>10)
    						throw new Exception("horizontal speed have to be between 1 to 10");
    					if(verspeed<1 || verspeed>10)
    						throw new Exception("vertical speed have to be between 1 to 10");
                		
                		if(dup.getClass().equals(Fish.class))
                			panel.AddAnimelToHashSet(new Fish(size,0,0,horspeed,verspeed,color,100));
                		else if(dup.getClass().equals(Jellyfish.class))
                			panel.AddAnimelToHashSet(new Jellyfish(size,0,0,horspeed,verspeed,color,100));
                		dispose();
                		}catch (Exception e3) {
							// TODO: handle exception
						}
                	}
                });
                btnNewButton.setBounds(100, 250, 98, 21);
                Pandialog.add(btnNewButton);
        		
        	}catch (NumberFormatException e1) {
        		dialog.dispose();
			}catch (NullPointerException e2) {
				dialog.setVisible(false);
    			ImageIcon icon = new ImageIcon("src//q3//octopus.png");
    			JOptionPane.showMessageDialog(null, "Worng ID","System Notice",JOptionPane.PLAIN_MESSAGE,icon);
			}
        	}
        });
   
        
        btnUpdate.setBounds(247, 489, 85, 21);
        TPanel.add(btnUpdate);
        this.setVisible(true);
        this.setBounds(10, 10, 10, 10);
        this.setSize(525,560);
		
	}
	

	 public Integer getColor(String str) {
		  
		  Hashtable<String, Integer> my_dict = new Hashtable<String, Integer>();
		  my_dict.put("Black", 1); 
		  my_dict.put("Red", 2); 
		  my_dict.put("Blue", 3);
		  my_dict.put("Green", 4); 
		  my_dict.put("Cyen", 5); 
		  my_dict.put("Orange", 6);
		  my_dict.put("Yellow", 7); 
		  my_dict.put("Mangeta", 8); 
		  my_dict.put("Pink", 9);
		  
		  return my_dict.get(str); 
		  
	}
	 
}
